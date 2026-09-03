package com.springtutorial.Repository;

import com.springtutorial.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryEmp extends JpaRepository<Employee,Integer> {

    Optional<Employee> getByEmail(String email);


}
