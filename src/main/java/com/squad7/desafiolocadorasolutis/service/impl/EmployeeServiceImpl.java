package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.EmployeeNotFoundException;
import com.squad7.desafiolocadorasolutis.model.Employee;
import com.squad7.desafiolocadorasolutis.repository.EmployeeRepository;
import com.squad7.desafiolocadorasolutis.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findByRegistration(String registration) {
        log.info("Employee found: registration = {}",registration);
        return employeeRepository.findByRegistration(registration);
    }

    @Override
    public Employee ensureEmployeeExistsByRegistration(String registration) {
        log.info("Starting search for employee with id: {}", registration);
        return findByRegistration(registration)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with registration " + registration + "not found"));
    }

}
