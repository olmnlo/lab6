package org.example.employeemanagement.Controller;

import ch.qos.logback.core.model.conditional.ElseModel;
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        for (int i = 0; i<employees.size(); i++){
            if (employees.get(i).getId().equals(id)){
                employees.remove(i);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee deleted successfully"));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("employee not found"));
    }

    @GetMapping("/show/{position}")
    public ResponseEntity<?> getByPosition(@PathVariable String position){
        ArrayList<Employee> byPosition;
        if (position.equals("supervisor")){
            byPosition = findByPosition(position);
            if (byPosition.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("there is no "+position+" here"));
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(byPosition);
            }
        }else if (position.equals("coordinator")){
           byPosition = findByPosition(position);
            if (byPosition.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("there is no "+position+" here"));
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(byPosition);
            }
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("you must write correct position: \"supervisor\" or \"coordinator\""));
        }
    }
    public ArrayList<Employee> findByPosition(String position){
        ArrayList<Employee> byPosition = new ArrayList<>();
        for (Employee e : employees){
            if (e.getPosition().equals(position)){
                byPosition.add(e);
            }
        }
        return byPosition;
    }


    @GetMapping("/show/age/min/{minAge}/max/{maxAge}")
    public ResponseEntity<?> getByAgeRange(@PathVariable int minAge,@PathVariable int maxAge){
        if (minAge > maxAge){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("minAge must be less than or equal to maxAge"));
        }else if(minAge < 25 || maxAge < 25){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Age must be 25 or more"));
        }else {
            ArrayList<Employee> byAge = new ArrayList<>();
            for (Employee e : employees){
                if (e.getAge()>=minAge && e.getAge()<=maxAge){
                    byAge.add(e);
                }
            }
            if (byAge.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("there is no employee in this range"));
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(byAge);
            }
        }
    }


    @PutMapping("/annual-leave/{id}")
    public ResponseEntity<?> applyForAnnualLeave(@PathVariable String id){
        for (Employee e : employees){
            if (e.getId().equals(id)){
                if (e.getOnLeave().equals("false")){
                    if(e.getAnnualLeave() >= 1){
                        e.setOnLeave("true");
                        e.setAnnualLeave(e.getAnnualLeave()-1);
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee is updated enjoy in you vacation"));
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee is on leave now"));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee not found"));
    }

    @GetMapping("/show/zero-annual")
    public ResponseEntity<?> retrieveAllNoAnnual(){
        ArrayList<Employee> notHaveAnnual = new ArrayList<>();
        for (Employee e : employees){
            if (e.getAnnualLeave() == 0){
                notHaveAnnual.add(e);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(notHaveAnnual);
    }


    @PutMapping("/promote/requester/{idOfRequester}/employee/{idOfCoordinator}")
    public ResponseEntity<?> promoteEmployee(@PathVariable String idOfCoordinator,@PathVariable String idOfRequester){
        int supervisor = findById(idOfRequester);
        int coordinator = findById(idOfCoordinator);
        if(supervisor == -1 || coordinator == -1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id is not exist"));
        }
        if(supervisor == coordinator){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("you cannot promote yourself"));
        }
        if (!employees.get(supervisor).getPosition().equals("supervisor")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("you are not supervisor"));
        }
        if (employees.get(coordinator).getAge() >= 30 && employees.get(coordinator).getOnLeave().equals("false") && !employees.get(coordinator).getPosition().equals("supervisor")){
            employees.get(coordinator).setPosition("supervisor");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee position updated"));
        }else if (employees.get(coordinator).getAge() >= 30){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee do not satisfied age 30"));
        } else if (!employees.get(coordinator).getOnLeave().equals("false")) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee do not satisfied leave"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("employee is already supervisor"));
        }
    }

    public int findById(String id){
        for (int i = 0; i<employees.size(); i++){
            if(employees.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

}
