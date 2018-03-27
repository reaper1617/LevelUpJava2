package ru.gerasimchuk.homework4;

import ru.gerasimchuk.homework4.dao.EmployeeDao;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    // keys
    private static final String jdbcDatabaseNameKey = "jdbc.database.name";
    private static final String jdbcHostKey = "jdbc.host";
    private static final String jdbcPortKey = "jdbc.port";
    private static final String userNameKey = "jdbc.username";
    private static final String passwordKey = "jdbc.password";
    private static final String daoImplementationKey = "dao.implementation";

    // keys values
    private static String jdbcDatabaseName;
    private static String jdbcHost;
    private static String jdbcPort;
    private static String userName;
    private static String password;
    private static String daoImplementation;

    // flags
    private static boolean isInitialized = false;

    private static Connection connection;

    // to set configuration from file
    public static void init(String propertiesFilePath) {
        if (isInitialized) return;
        File file = new File(propertiesFilePath);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] strings = line.split("=");
                    if (strings[0].equals(jdbcDatabaseNameKey)) jdbcDatabaseName = strings[1];
                    if (strings[0].equals(jdbcHostKey)) jdbcHost = strings[1];
                    if (strings[0].equals(jdbcPortKey)) jdbcPort = strings[1];
                    if (strings[0].equals(userNameKey)) userName = strings[1];
                    if (strings[0].equals(passwordKey)) password = strings[1];
                    if (strings[0].equals(daoImplementationKey)) daoImplementation = strings[1];
                }
                isInitialized = true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // getters


    public static String getJdbcDatabaseName() {
        return jdbcDatabaseName;
    }

    // return string with info about properties
    public static String getConfiguration() {
        if (isInitialized) return "jdbcDatabaseName = " + jdbcDatabaseName +
                " jdbcHost = " + jdbcHost + " jdbcPort = " +
                jdbcPort + " userName = " + userName + " password = " + password + " daoImplementation = " + daoImplementation;
        else return "not initialized";
    }

    // returns object of "Connection" type
    public static Connection getConnection(){
        if (!isInitialized) throw new RuntimeException();
        String url = "jdbc:mysql://" + jdbcHost + ":" + jdbcPort + "/" + jdbcDatabaseName;
        System.out.println("url = " + url);
        url+="?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url,userName,password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmployeeDao getDaoImplementation(){
        EmployeeDao employeeDao = null;
        try {
            Constructor[] constructor = Class.forName(daoImplementation).getConstructors();
            employeeDao = (EmployeeDao) constructor[0].newInstance(connection);
            return employeeDao;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
