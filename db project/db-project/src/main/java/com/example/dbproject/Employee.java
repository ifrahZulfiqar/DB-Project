package com.example.dbproject;

public class Employee {
    String id, name;
    EmployeeLevels employeeLevels;

    public Employee(String id, String name, EmployeeLevels employeeLevels) {
        this.id = id;
        this.name = name;
        this.employeeLevels = employeeLevels;
    }

    public Employee() {}
}
