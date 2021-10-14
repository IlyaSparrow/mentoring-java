package com.epam.oss.service;

import com.epam.oss.contracts.Employee;
import com.epam.oss.contracts.Salary;
import com.epam.oss.util.EmployeeUtil;
import com.epam.oss.util.SalaryUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.epam.oss.util.EmployeeUtil.createEmployees;
import static com.epam.oss.util.SalaryUtil.createSalaries;

@Service
public class EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private final List<Salary> salaryList = new ArrayList<>();

    public CompletableFuture<List<Employee>> hiredEmployees(final int number) {
        CompletableFuture<List<Employee>> fetchedEmployees = new CompletableFuture<>();
        CompletableFuture<List<Salary>> salaryFuture = CompletableFuture.supplyAsync(() -> createSalaries(number));
        CompletableFuture<List<Employee>> employeesFuture =
                CompletableFuture.supplyAsync(() -> createEmployees(number));

        employeesFuture.thenApply((Function<List<Employee>, Object>) employeeList::addAll);
        salaryFuture.thenApply((Function<List<Salary>, Object>) salaryList::addAll);

        CompletableFuture.allOf(employeesFuture, salaryFuture)
                .thenRunAsync(() -> fetchedEmployees.complete(fetchSalary()));

        return fetchedEmployees;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    private List<Employee> fetchSalary() {
        employeeList.forEach(employee -> {
            employee.setSalary(getSalary(employee.getId()));
        });

        return employeeList;
    }

    public BigDecimal getSalary(final Long employeeId) {
        return salaryList.stream()
                .filter(salary -> employeeId.equals(salary.getEmployeeId()))
                .findFirst()
                .map(Salary::getSalary)
                .orElse(BigDecimal.ZERO);
    }
}
