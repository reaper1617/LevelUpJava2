package ru.gerasimchuk.homework4dao.dao;

import ru.gerasimchuk.homework4dao.domain.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class EmployeeDaoImplementation implements EmployeeDao {

    private Connection connection;


    public EmployeeDaoImplementation(Connection connection){
        this.connection = connection;
    }

    @Override
    public Employee create(int id, String login, String name, String last_name, String email, int departmentId) {
        try (Statement statement = connection.createStatement()){
            String sqlQuery = "insert into employee value (" +  id + ",\"" +  login + "\",\"" + name + "\",\"" + last_name + "\",\"" + email + "\"," + departmentId + ")";
            System.out.println("query = " + sqlQuery);
            statement.executeUpdate(sqlQuery);
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()){
                int resId = resultSet.getInt("id");
                if (resId == id) {
                    String resLogin = resultSet.getString("login");
                    String resName = resultSet.getString("name");
                    String resLastName = resultSet.getString("last_name");
                    String resEmail = resultSet.getString("email");
                    int resDepartmentId = resultSet.getInt("department_id");
                    return new Employee(resId, resLogin, resName, resLastName, resEmail, resDepartmentId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Employee update(int id, String login, String name, String last_name, String email, int departmentId) {
        System.out.println("Used EmployeeDaoImplementation!");
        String sqlQuery = "update employee set login = \"" +
                login + "\", name = \"" + name + "\", last_name = \"" + last_name +
                "\", email = \"" + email + "\", department_id = " + departmentId + " where id = " + id ;
        System.out.println("aqlQuery = " + sqlQuery);
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()){
                int resId = resultSet.getInt("id");
                if (resId == id) {
                    String resLogin = resultSet.getString("login");
                    String resName = resultSet.getString("name");
                    String resLastName = resultSet.getString("last_name");
                    String resEmail = resultSet.getString("email");
                    int resDepartmentId = resultSet.getInt("department_id");
                    return new Employee(resId, resLogin, resName, resLastName, resEmail, resDepartmentId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        System.out.println("Used EmployeeDaoImplementation!");
        String sqlQuery = "delete from employee where id = " + id;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlQuery);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        System.out.println("Used EmployeeDaoImplementation!");
        Collection<Employee> collection = new LinkedList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()){
                int resId = resultSet.getInt("id");
                String resLogin = resultSet.getString("login");
                String resName = resultSet.getString("name");
                String resLastName = resultSet.getString("last_name");
                String resEmail = resultSet.getString("email");
                int resDepartmentId = resultSet.getInt("department_id");
                collection.add(new Employee(resId, resLogin, resName, resLastName, resEmail, resDepartmentId));
                }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return collection;
    }

    @Override
    public Employee findById(int id) {
        System.out.println("Used EmployeeDaoImplementation!");
        String sqlQuery = "select * from employee where id = " + id;
        try(Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(sqlQuery);
            result.next();
            int resId = result.getInt("id");
            String resLogin = result.getString("login");
            String resName = result.getString("name");
            String resLastName = result.getString("last_name");
            String resEmail = result.getString("email");
            int resDepartmentId = result.getInt("department_id");
            return new Employee(resId, resLogin, resName, resLastName, resEmail, resDepartmentId);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
