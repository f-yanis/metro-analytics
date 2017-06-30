package ru.fyanis.metro_analytics;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    Connection connection;

    public DBConnect(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}


