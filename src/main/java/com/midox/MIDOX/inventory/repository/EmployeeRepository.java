package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // TODO Special character, escape charater support to be implemented
    String findAllByNameQuery = "select * from employee where emp_name ILIKE %:emp_name%";
    //String findAllByNameQuery = "select * from employee where emp_name ILIKE %?%#{escape([0])} escape ?#{escapeCharacter()}";

    @Query(value = findAllByNameQuery, nativeQuery = true)
    List<Employee> findEmployeesByName(@Param("emp_name") String empName);
   /* @Query(value = findAllByNameQuery)
    List<Employee> findEmployeesByName(String empName);*/
}
