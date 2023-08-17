package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Employee;
import com.midox.MIDOX.inventory.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);

    Optional<Employee> getEmployee(Integer employeeId);

    List<Employee> getEmployeesByName(String empName);

    //List<Employee> searchEmployees(String employeeName, String designation, String);
}