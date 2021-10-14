package com.epam.oss.util;

import com.epam.oss.contracts.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {

    public static List<Employee> createEmployees(final int number) {
        final List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Employee employee = Employee.builder()
                    .name("Name" + i)
                    .id(i)
                    .build();
            employees.add(employee);
        }

        return employees;
    }
}
