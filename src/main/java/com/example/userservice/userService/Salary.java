package com.example.userservice.userService;

import com.example.userservice.model.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Salary {
    static Double getSalary(List<Application> applications) {
        double sum = 0.0;
        for (Application app: applications) {
            if(app.getEndDate()==null) {
                long startDays = app.getStartDate().lengthOfMonth()-app.getStartDate().getDayOfMonth();
                long months = ChronoUnit.MONTHS.between(app.getStartDate(), LocalDate.now().minusMonths(1));
                sum+=months*app.getSalary()+app.getSalary()/app.getStartDate().lengthOfMonth()*startDays;
            }else{
                long month = ChronoUnit.MONTHS.between(app.getStartDate(),app.getEndDate().minusMonths(1));
                long startDays = app.getStartDate().lengthOfMonth()-app.getStartDate().getDayOfMonth();
                long endDays = app.getEndDate().lengthOfMonth() - app.getEndDate().getDayOfMonth();
                sum += month*app.getSalary() + app.getSalary() / app.getStartDate().lengthOfMonth() * startDays +
                        app.getSalary()/app.getEndDate().lengthOfMonth()*endDays;
            }
        }
        return sum;
    }
}
