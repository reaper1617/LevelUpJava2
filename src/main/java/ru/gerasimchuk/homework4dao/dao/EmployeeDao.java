package ru.gerasimchuk.homework4dao.dao;

import ru.gerasimchuk.homework4dao.domain.Employee;

import java.util.Collection;

public interface EmployeeDao {

    Employee create(int id, String login, String name, String last_name, String email, int departmentId);

    Employee update(int id, String login, String name, String last_name, String email, int departmentId);

    void delete(int id);

    Collection<Employee> findAll();

    Employee findById(int id);


}
