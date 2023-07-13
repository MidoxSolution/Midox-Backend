package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Employee;
import com.midox.MIDOX.inventory.repository.EmployeeRepository;
import com.midox.MIDOX.inventory.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepo;

    @Override
    public Boolean addEmployee(Employee employee) {
        return null;
    }
}
