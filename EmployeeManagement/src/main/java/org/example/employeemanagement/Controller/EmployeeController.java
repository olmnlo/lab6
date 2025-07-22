package org.example.employeemanagement.Controller;

import org.example.employeemanagement.Model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();


    @GetMapping("/show")
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }


}
