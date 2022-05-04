package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Employee> employees = new ArrayList<>();
    private static Double BONUS_RATE = 1.10;

    static {
        List<String> projects = new ArrayList<>(2);
        projects.add(0, "Proj2");
        projects.add(1, "Proj2");

        employees.add(
                new Employee("Abraham", "Lugonzo", 5_000.00, projects)
        );

        employees.add(
                new Employee("John", "II", 89_000.00, projects)
        );
    }


    public static void main(String[] args) {
        // write your code here

        //foreach
        employees.stream()
                .forEach(emp -> {
                    System.out.println(emp);
                });

        // map
       List<Employee> increasedSalary = employees.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * BONUS_RATE,
                        employee.getProjects()
                )).collect(Collectors.toList());

        System.out.println( increasedSalary);


        //filter
        List<Employee> filterEmployee = employees.stream()
                .filter(employee -> employee.getSalary() > 5_000)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * BONUS_RATE,
                        employee.getProjects())
                ).collect(Collectors.toList());

        System.out.println(filterEmployee);

        //flatmap
        String projs =employees.stream()
                .map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));

        System.out.println(projs);

        //shorscircuit
       List<Employee> shortCircuit = employees
                .stream()
                .skip(1L)
                .limit(1L)
                .collect(Collectors.toList());

        System.out.println(shortCircuit);

        //min/max
        Employee maxSalary = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("max salaried employee >>" + maxSalary);

        //reduce
       Double totsalary =  employees.stream()
                .map(employee -> employee.getSalary())
               .reduce(0.0,Double::sum) ;

        System.out.println(totsalary);
    }

}