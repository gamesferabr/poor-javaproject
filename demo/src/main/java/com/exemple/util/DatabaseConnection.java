package com.exemple.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Utilize um caminho absoluto
    private static final String URL = "jdbc:sqlite:demo-1.0-SNAPSHOT/web-inf/database/livros.db";

    static {
        try {
            // Carrega o driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC do SQLite: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
