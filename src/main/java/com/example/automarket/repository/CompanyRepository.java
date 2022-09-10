package com.example.automarket.repository;

import com.example.automarket.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findCompanyByCompanyName (String companyName);
}
