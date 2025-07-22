package org.example.employeemanagement.Controller;

import jakarta.validation.Valid;
import org.example.employeemanagement.Api.ApiResponse;
import org.example.employeemanagement.Model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();


    @GetMapping("/show")
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getFieldError().getDefaultMessage());
        }else {
            employee.setOnLeave("false");
            employees.add(employee);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee add successfully"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee, Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getFieldError().getDefaultMessage());
        }else {
            for (int i = 0; i<employees.size(); i++){
                if (employees.get(i).getId().equals(id)){
                    employees.set(i, employee);
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee updated successfully"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("employee not found"));
    }


}
