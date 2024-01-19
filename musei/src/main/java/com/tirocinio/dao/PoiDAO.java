package com.tirocinio.dao;

import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoiDAO {

    private static final String SELECT_ALL_POIS = "SELECT * FROM Poi";
    private static final String SELECT_POI_BY_ID = "SELECT * FROM Poi WHERE Cod_Poi = ?";
    private static final String INSERT_POI = "INSERT INTO Poi (Descrizione, Cod_E_M) VALUES (?, ?)";
    private static final String UPDATE_POI = "UPDATE Poi SET Descrizione = ?, Cod_E_M = ? WHERE Cod_Poi = ?";
    private static final String DELETE_POI = "DELETE FROM Poi WHERE Cod_Poi = ?";

    public List<Poi> getAllPois(Connection connection) {
        List<Poi> pois = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POIS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                pois.add(mapResultSetToPoi(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco l'eccezione 
        }
        return pois;
    }

    public Poi getPoiById(Connection connection, int poiId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POI_BY_ID)) {

            preparedStatement.setInt(1, poiId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPoi(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco l'eccezione 
        }
        return null;
    }

    public boolean addPoi(Connection connection, Poi poi) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POI)) {

            preparedStatement.setString(1, poi.getDescrizione());
            preparedStatement.setInt(2, poi.getCodEM());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco l'eccezione 
            return false;
        }
    }

    public boolean updatePoi(Connection connection, Poi poi) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POI)) {

            preparedStatement.setString(1, poi.getDescrizione());
            preparedStatement.setInt(2, poi.getCodEM());
            preparedStatement.setInt(3, poi.getCodPoi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco l'eccezione 
            return false;
        }
    }

    public boolean deletePoi(Connection connection, int poiId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POI)) {

            preparedStatement.setInt(1, poiId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); //Gestisco l'eccezione 
            return false;
        }
    }

    public List<Poi> search(Connection connection, Poi criteria) {
        List<Poi> matchingPois = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Poi WHERE 1=1");

        // Aggiungi i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getDescrizione() != null) {
            queryBuilder.append(" AND Descrizione LIKE ?");
        }
        if (criteria.getCodEM() != null) {
            queryBuilder.append(" AND Cod_E_M = ?");
        }
        // Aggiungi altri criteri secondo necessità

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            // Imposta i parametri dinamici nella query
            if (criteria.getDescrizione() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getDescrizione() + "%");
            }
            if (criteria.getCodEM() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEM());
            }
            // Imposta altri parametri secondo necessità

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingPois.add(mapResultSetToPoi(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco l'eccezione 
        }

        return matchingPois;
    }

    private Poi mapResultSetToPoi(ResultSet resultSet) throws SQLException {
        Poi poi = new Poi();
        poi.setCodPoi(resultSet.getInt("Cod_Poi"));
        poi.setDescrizione(resultSet.getString("Descrizione"));
        poi.setCodEM(resultSet.getInt("Cod_E_M"));
        return poi;
    }
}