package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Employee;
import com.midox.MIDOX.inventory.models.GenericSearchCriteria;
import com.midox.MIDOX.inventory.service.spi.IEmployeeService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Message> addEmployee(@RequestBody Employee employee) {
        ResponseEntity<Message> response = null;
        try {
            employeeService.addEmployee(employee);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.EMPLOYEE_ADDED_SUCCESSFULLY), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.EMPLOYEE_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable Integer employeeId) {
        ResponseEntity<?> response = null;
        try {
            Optional<Employee> employee = employeeService.getEmployee(employeeId);
            if(employee.isPresent()){
                response = new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
            } else{
                response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.EMPLOYEE_NOT_FOUND), HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.EMPLOYEE_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get-employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestBody GenericSearchCriteria searchCriteria) {
        ResponseEntity<List<Employee>> response = null;
        try {
            List<Employee> employees = employeeService.getEmployeesByCriteria(searchCriteria.getName(), searchCriteria.getId());
            response = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<List<Employee>>(new ArrayList<>(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

}
