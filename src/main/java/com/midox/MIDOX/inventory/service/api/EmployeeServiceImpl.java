package com.midox.MIDOX.inventory.service.api;

import com.midox.MIDOX.inventory.entity.Employee;
import com.midox.MIDOX.inventory.repository.EmployeeRepository;
import com.midox.MIDOX.inventory.service.spi.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee addEmployee(Employee employee) {
        employee.setDefaultValues();
        return employeeRepo.saveAndFlush(employee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Employee> getEmployee(Integer employeeId) {
        Optional emp =  employeeRepo.findById(employeeId);
        return emp;
    }
}
