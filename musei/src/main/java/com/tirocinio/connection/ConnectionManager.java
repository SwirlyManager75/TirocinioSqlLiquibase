package com.tirocinio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static Connection connection;

    private ConnectionManager() {
        // Costruttore privato per garantire la singola istanza (Singleton)
    }

    public static Connection getConnection() 
    {
        
        PropertiesManager propertiesManager = PropertiesManager.getInstance();

        URL=propertiesManager.getUrl();
        USER=propertiesManager.getUsername();
        PASSWORD=propertiesManager.getPassword();

        System.out.println("Connesso usando "+URL+"   e "+USER+"    e    "+PASSWORD);

        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);
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
