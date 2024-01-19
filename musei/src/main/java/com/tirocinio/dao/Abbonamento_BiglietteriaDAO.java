package com.tirocinio.dao;

import com.tirocinio.model.Abbonamento_Biglietteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Abbonamento_BiglietteriaDAO {

    private static final String SELECT_ALL_ABBONAMENTI_BIGLIETTERIE = "SELECT * FROM Abbonamento_Biglietteria";
    private static final String SELECT_ABBONAMENTO_BIGLIETTERIA_BY_ID = "SELECT * FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";
    private static final String INSERT_ABBONAMENTO_BIGLIETTERIA = "INSERT INTO Abbonamento_Biglietteria (Cod_E_A, Cod_E_B) VALUES (?, ?)";
    private static final String DELETE_ABBONAMENTO_BIGLIETTERIA = "DELETE FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";

    public List<Abbonamento_Biglietteria> getAllAbbonamentiBiglietterie(Connection connection) {
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
    }

    public Abbonamento_Biglietteria getAbbonamentoBiglietteriaById(Connection connection, int abbonamentoBiglietteriaId) {
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
    }

    public boolean addAbbonamentoBiglietteria(Connection connection, Abbonamento_Biglietteria abbonamentoBiglietteria) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, abbonamentoBiglietteria.getCodEA());
            preparedStatement.setInt(2, abbonamentoBiglietteria.getCodEB());

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

    public List<Abbonamento_Biglietteria> search(Connection connection, Abbonamento_Biglietteria criteria) {
        List<Abbonamento_Biglietteria> matchingAbbonamentiBiglietterie = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Abbonamento_Biglietteria WHERE 1=1");

        if (criteria.getCodEA() != null) {
            queryBuilder.append(" AND Cod_E_A = ?");
        }
        if (criteria.getCodEB() != null) {
            queryBuilder.append(" AND Cod_E_B = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getCodEA() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEA());
            }
            if (criteria.getCodEB() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEB());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingAbbonamentiBiglietterie.add(mapResultSetToAbbonamentoBiglietteria(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingAbbonamentiBiglietterie;
    }

    private Abbonamento_Biglietteria mapResultSetToAbbonamentoBiglietteria(ResultSet resultSet) throws SQLException {
        Abbonamento_Biglietteria abbonamentoBiglietteria = new Abbonamento_Biglietteria();
        abbonamentoBiglietteria.setCodAB(resultSet.getInt("Cod_AB"));
        abbonamentoBiglietteria.setCodEA(resultSet.getInt("Cod_E_A"));
        abbonamentoBiglietteria.setCodEB(resultSet.getInt("Cod_E_B"));
        return abbonamentoBiglietteria;
    }
}