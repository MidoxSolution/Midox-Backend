package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);

    Employee editEmployee(Employee employee);

    Employee getEmployee(Integer employeeId);

    List<Employee> getEmployeesByCriteria(String empName, Integer id);

    //List<Employee> searchEmployees(String employeeName, String designation, String);
}