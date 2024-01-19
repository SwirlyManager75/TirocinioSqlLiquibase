package com.tirocinio.dao;

import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuseoDAO {

    private static final String SELECT_ALL_MUSEUMS = "SELECT * FROM Museo";
    private static final String SELECT_MUSEUM_BY_ID = "SELECT * FROM Museo WHERE Cod_M = ?";
    private static final String INSERT_MUSEUM = "INSERT INTO Museo (Nome, Via, Cod_E_Ci) VALUES (?, ?, ?)";
    private static final String UPDATE_MUSEUM = "UPDATE Museo SET Nome = ?, Via = ?, Cod_E_Ci = ? WHERE Cod_M = ?";
    private static final String DELETE_MUSEUM = "DELETE FROM Museo WHERE Cod_M = ?";

    public List<Museo> getAllMuseums(Connection connection) {
        List<Museo> museums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUSEUMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                museums.add(mapResultSetToMuseum(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); //Gestisco eccezione 
        }
        return museums;
    }

    public Museo getMuseumById(Connection connection, int museumId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MUSEUM_BY_ID)) {

            preparedStatement.setInt(1, museumId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMuseum(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco eccezione  
        }
        return null;
    }

    public boolean addMuseum(Connection connection, Museo museum) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSEUM)) {

            preparedStatement.setString(1, museum.getNome());
            preparedStatement.setString(2, museum.getVia());
            preparedStatement.setInt(3, museum.getCodECi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); //Gestisco eccezione 
            return false;
        }
    }

    public boolean updateMuseum(Connection connection, Museo museum) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MUSEUM)) {

            preparedStatement.setString(1, museum.getNome());
            preparedStatement.setString(2, museum.getVia());
            preparedStatement.setInt(3, museum.getCodECi());
            preparedStatement.setInt(4, museum.getCodM());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco eccezione 
            return false;
        }
    }

    public boolean deleteMuseum(Connection connection, int museumId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MUSEUM)) {

            preparedStatement.setInt(1, museumId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco eccezione 
            return false;
        }
    }

    public List<Museo> search(Connection connection, Museo criteria) {
        List<Museo> matchingMuseums = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Museo WHERE 1=1");
    
        // Aggiungo i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome LIKE ?");
        }
        if (criteria.getVia() != null) {
            queryBuilder.append(" AND Via LIKE ?");
        }
        // In futuro aggiungi qui altri criteri a secondo della necessità
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;
    
            // Controlla e imposta i parametri dinamici nella query
            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getNome() + "%");
            }
            if (criteria.getVia() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getVia() + "%");
            }
            // In futuro imposta altri parametri qui
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingMuseums.add(mapResultSetToMuseum(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisco eccezione 
        }
    
        return matchingMuseums;
    }

    private Museo mapResultSetToMuseum(ResultSet resultSet) throws SQLException {
        Museo museum = new Museo();
        museum.setCodM(resultSet.getInt("Cod_M"));
        museum.setNome(resultSet.getString("Nome"));
        museum.setVia(resultSet.getString("Via"));
        museum.setCodECi(resultSet.getInt("Cod_E_Ci"));
        return museum;
    }
}