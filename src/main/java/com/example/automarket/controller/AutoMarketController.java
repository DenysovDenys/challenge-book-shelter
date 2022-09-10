package com.example.automarket.controller;

import com.example.automarket.entity.Car;
import com.example.automarket.entity.Company;
import com.example.automarket.repository.CarRepository;
import com.example.automarket.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class AutoMarketController {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/companies")
    public String allCompanies(@RequestParam(required = false, defaultValue = "") String filterByName, Model model) {
        Iterable<Company> companies;

        if (filterByName != null && !filterByName.isEmpty()) {
            companies = companyRepository.findCompanyByCompanyName(filterByName);
        } else {
            companies = companyRepository.findAll();
        }
        model.addAttribute("companies", companies);
        model.addAttribute("filterByName", filterByName);

        return "companies";
    }

    @GetMapping("/companies/cars/{companyName}")
    public String allCars(@PathVariable String companyName,
                          @RequestParam(required = false, defaultValue = "") String filterByModel, Model model) {
        Iterable<Car> cars;

        if (filterByModel != null && !filterByModel.isEmpty()) {
            cars = carRepository.findCarByCompanyCompanyNameAndModel(companyName, filterByModel);
        } else {
            cars = carRepository.findCarByCompanyCompanyName(companyName);
        }

        model.addAttribute("cars", cars);
        model.addAttribute("filterByModel", filterByModel);
        model.addAttribute("companyName", companyName);

        return "cars";
    }

    @PostMapping("/companies")
    public String addCompany(@RequestParam String companyName, Map<String, Object> model) {
        Iterable<Company> companyFromDb = companyRepository.findCompanyByCompanyName(companyName);

        if (companyFromDb != null) {
            model.put("message", "Company already exists!");
            model.put("companies", companyFromDb);
            model.put("filterByName", companyName);
        } else {
            Company company = new Company(companyName);
            companyRepository.save(company);

            model.put("companies", companyRepository.findById(company.getCompanyId()));
            model.put("filterByName", "");
        }

        return "companies";
    }

    @PostMapping("/companies/cars")
    public String addCar(@RequestParam String companyName, @RequestParam String modelOfCar,
                         @RequestParam LocalDate manufacture, Map<String, Object> model) {
        Iterable<Car> carFromDb =
                carRepository.findCarByCompanyCompanyNameAndModelAndManufacture(companyName, modelOfCar, manufacture);

        if (carFromDb != null) {
            model.put("message", "Car of this model and year of manufacture already exists!");
            model.put("cars", carFromDb);
        } else {
            Car car = new Car(companyRepository.findCompanyByCompanyName(companyName).get(0), modelOfCar, manufacture);
            carRepository.save(car);

            model.put("cars", carRepository.findById(car.getCarId()));
        }

        model.put("filterByModel", modelOfCar);
        model.put("companyName", companyName);

        return "cars";
    }
}
