package ru.gerasimchuk.homework4;

import ru.gerasimchuk.homework4.dao.EmployeeDao;
import ru.gerasimchuk.homework4.domain.Employee;

import java.sql.Connection;

public class JDBCMain {
    public static void main(String[] args) {


        Configuration.init(System.getProperty("user.dir") + "\\src\\main\\java\\ru\\gerasimchuk\\homework4\\.properties");
        System.out.println(Configuration.getConfiguration());
        Connection connection = Configuration.getConnection();
        EmployeeDao daoImpl = Configuration.getDaoImplementation();
        Employee employee = daoImpl.findById(1);
        System.out.println(employee);
//        EmployeeDaoImplementation impl = new EmployeeDaoImplementation(Configuration.getConnection());
//
//        Employee employee = impl.create(10,"login", "name", "lname", "email", 4 );
//        System.out.println(employee);
//        Employee employee1 = impl.update(10,"wewte", "wrt", "srgs", "ssstgwst", 3);
//        System.out.println(employee1);
//
//        Collection<Employee> collection = impl.findAll();
//        for(Employee e: collection){
//            System.out.println(e);
//        }
//
//        Employee employee2 = impl.findById(3);
//        System.out.println(employee2);

        Configuration.closeConnection();
    }
}
