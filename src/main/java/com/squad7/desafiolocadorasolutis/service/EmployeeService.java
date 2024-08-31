package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.model.Employee;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findByRegistration(String registration);
    Employee ensureEmployeeExistsByRegistration(String registration);
}
