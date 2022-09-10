package com.example.automarket.repository;

import com.example.automarket.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository  extends CrudRepository<Car, Integer> {

    List<Car> findCarByCompanyCompanyNameAndModel(String companyName, String model);
    List<Car> findCarByCompanyCompanyNameAndModelAndManufacture(String companyName, String model, LocalDate manufacture);

    List<Car> findCarByCompanyCompanyName(String companyName);
}
