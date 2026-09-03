package com.springtutorial.controller;

import com.springtutorial.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springtutorial.service.ServiceLayer;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class RestControllerEmp {

    @Autowired
    private ServiceLayer serviceLayer;

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee emp)
    {   Optional<Employee> existingEmp= serviceLayer.getByEmail(emp.getEmail());

        if(existingEmp.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Employee id  already existed");
        }
        Employee savedEmp = serviceLayer.savedata(emp);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Created..");

    }

    @GetMapping("/employee")
    public List<Employee> getEmployees()
    {
        return serviceLayer.getAllEmployees();
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee emp)
    {
        return serviceLayer.savedata(emp);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id)
    {   if(!serviceLayer.exists(id))
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Employee Not Found");

       }
        serviceLayer.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted Successfully");
    }
//    @GetMapping("employee/{id}")
//     public ResponseEntity<Employee>  getUserByid(@PathVariable int id)
//    {
//        if(!serviceLayer.exists(id))
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .build();
//
//        }
//        return ResponseEntity.ok(serviceLayer.get(id));
//
//    }
@GetMapping("/employee/{id}")
public ResponseEntity<Employee> getUserByid(@PathVariable int id)
{
    Optional<Employee> emp = serviceLayer.getById(id);

    if(emp.isPresent())
    {
        return ResponseEntity.ok(emp.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .build();
}

}
