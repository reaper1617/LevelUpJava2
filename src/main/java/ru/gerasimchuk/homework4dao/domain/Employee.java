package ru.gerasimchuk.homework4dao.domain;

public class Employee {

    private int id;
    private String login;
    private String name;
    private String last_name;
    private String email;
    private int departmentId;

    public Employee(int id, String login, String name, String last_name, String email, int departmentId) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.departmentId = departmentId;
    }



    @Override
    public String toString() {
        return "id =" + id + " login = " + login + " name = " + name + " last name = " + last_name + " email = " + email + " departmentId = " + departmentId;
    }
}
