package com.epam.oss.controller;

import com.epam.oss.contracts.Employee;
import com.epam.oss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping(value = "/fetch", params = {"number"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CompletableFuture<List<Employee>> fetchEmployees(@RequestParam("number") int number) {
        return service.hiredEmployees(number);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return service.getEmployeeList();
    }
}
