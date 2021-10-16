package com.epam.oss.util;

import com.epam.oss.contracts.Salary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalaryUtil {

    public static List<Salary> createSalaries(final int number) {
        final List<Salary> salaries = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Salary salary = Salary.builder()
                    .salary(BigDecimal.valueOf(100L * i))
                    .id(i)
                    .employeeId(i)
                    .build();
            salaries.add(salary);
        }

        return salaries;
    }
}
