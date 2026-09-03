package com.springtutorial.service;

import com.springtutorial.Repository.RepositoryEmp;
import com.springtutorial.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {
    @Autowired
    private RepositoryEmp repositoryEmp;

    public Employee savedata(Employee emp)
    {
        return repositoryEmp.save(emp);
    }

    public List<Employee> getAllEmployees()
    {
        return repositoryEmp.findAll();
    }

    public void deleteEmployee(int id)
    {
        repositoryEmp.deleteById(id);
    }

    public boolean exists(int id)
    {
        return repositoryEmp.existsById(id);
    }
    public Optional<Employee> getByEmail(String email)
    {
        return repositoryEmp.getByEmail(email);
    }
//    public Employee get(int id)
//    {
//        return repositoryEmp.get(id);
//    }
    public Optional<Employee> getById(int id)
    {
        return repositoryEmp.findById(id);
    }
}
