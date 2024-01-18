package com.tirocinio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/musei";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private ConnectionManager() {
        // Costruttore privato per garantire la singola istanza (Singleton)
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace(); //  Gestire eccezione 
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); //Gestire eccezione 
            } finally {
                connection = null; // Assicura che la connessione sia resettata
            }
        }
    }


}
