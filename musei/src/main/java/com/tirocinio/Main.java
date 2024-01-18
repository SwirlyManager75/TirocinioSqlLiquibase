package com.tirocinio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tirocinio.connection.ConnectionManager;
public class Main {
    public static void main(String[] args) {
        //Inizializzo la connessione
        Connection connection = ConnectionManager.getConnection();

         try {
            // Esempio di utilizzo della connessione
            String query = "SELECT * FROM museo";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    // Processa i risultati
                    String colonna1 = resultSet.getString("Nome");
                    String colonna2 = resultSet.getString("Via");

                    // Fai qualcosa con i dati
                    System.out.println("Nome " + colonna1 + ", Via " + colonna2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            ConnectionManager.closeConnection();
        }
    }
}
