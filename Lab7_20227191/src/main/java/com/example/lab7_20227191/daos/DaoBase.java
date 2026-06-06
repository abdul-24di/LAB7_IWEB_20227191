package com.example.lab7_20227191.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// clase abstracta
public abstract class DaoBase<T> {
    private void registrarDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //estableciendo conexion
    public Connection getConnection() throws SQLException {
        registrarDriver();
        String url = "jdbc:mysql://localhost:3306/Veterinaria";
        String username = "root";
        String password = "12345678";
        return DriverManager.getConnection(url, username, password);
    }

    // Métodos para las subclases
    public abstract void crear(T object);

    public abstract void borrar(int id);
}