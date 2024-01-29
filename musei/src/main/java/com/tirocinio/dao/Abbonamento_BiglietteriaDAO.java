package com.tirocinio.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abbonamento_BiglietteriaDAO {

    private static final String SELECT_ALL_ABBONAMENTI_BIGLIETTERIE = "SELECT * FROM Abbonamento_Biglietteria";
    private static final String SELECT_ABBONAMENTO_BIGLIETTERIA_BY_ID = "SELECT * FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";
    private static final String INSERT_ABBONAMENTO_BIGLIETTERIA = "INSERT INTO Abbonamento_Biglietteria (Cod_E_A, Cod_E_B) VALUES (?, ?)";
    private static final String DELETE_ABBONAMENTO_BIGLIETTERIA = "DELETE FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";
    //TODO INVECE DI RITORNARE IL MODEL VECCHIO BISOGNA TORNARE UNA MAP <INT,INT>
   /*  public List<Abbonamento_Biglietteria> getAllAbbonamentiBiglietterie(Connection connection) {
        List<Abbonamento_Biglietteria> abbonamentiBiglietterie = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ABBONAMENTI_BIGLIETTERIE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                abbonamentiBiglietterie.add(mapResultSetToAbbonamentoBiglietteria(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abbonamentiBiglietterie;
    }*/

   /*  public Abbonamento_Biglietteria getAbbonamentoBiglietteriaById(Connection connection, int abbonamentoBiglietteriaId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ABBONAMENTO_BIGLIETTERIA_BY_ID)) {

            preparedStatement.setInt(1, abbonamentoBiglietteriaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAbbonamentoBiglietteria(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public boolean addAbbonamentoBiglietteria(Connection connection, int Cod_Ab, int Cod_B) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, Cod_Ab);
            preparedStatement.setInt(2, Cod_B);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAbbonamentoBiglietteria(Connection connection, int abbonamentoBiglietteriaId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, abbonamentoBiglietteriaId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAbbonamentoBiglietteria(Connection connection, Integer Cod_E_A,Integer Cod_E_B, Integer Cod_AB ) {
        String query = "UPDATE Abbonamento_Biglietteria " +
                       "SET Cod_E_A = ?, Cod_E_B = ? " +
                       "WHERE Cod_AB = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Cod_E_A);
            preparedStatement.setInt(2, Cod_E_B);
            preparedStatement.setInt(3, Cod_AB);

            int rowsAffected = preparedStatement.executeUpdate();

            // Restituisci true se almeno una riga è stata aggiornata
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato
            return false;
        }
    }

    public Map<Integer,Integer> search(Connection connection, Integer Cod_E_A, Integer Cod_E_B) {
        Map<Integer,Integer> matchingAbbonamentiBiglietterie = new HashMap();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Abbonamento_Biglietteria WHERE 1=1");

        if (Cod_E_A != null) {
            queryBuilder.append(" AND Cod_E_A = ?");
        }
        if (Cod_E_B != null) {
            queryBuilder.append(" AND Cod_E_B = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (Cod_E_A != null) {
                preparedStatement.setInt(parameterIndex++, Cod_E_A);
            }
            if (Cod_E_B != null) {
                preparedStatement.setInt(parameterIndex++, Cod_E_B);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingAbbonamentiBiglietterie.put(resultSet.getInt("Cod_E_B"),resultSet.getInt("Cod_E_A"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingAbbonamentiBiglietterie;
    }

    
}