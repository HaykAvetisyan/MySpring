package com.example.myspring.repository;

import com.example.myspring.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CompanyRepository extends JpaRepository<Company,Integer> {

}
