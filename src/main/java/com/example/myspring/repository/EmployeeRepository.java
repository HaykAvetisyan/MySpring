package com.example.myspring.repository;
import com.example.myspring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Transactional
    void deleteEmployeeByCompanyId(int id);
}
