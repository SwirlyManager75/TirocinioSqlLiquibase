package com.tirocinio.dao;

import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperaDAO {

    private static final String SELECT_ALL_OPERE = "SELECT * FROM Opera";
    private static final String SELECT_OPERA_BY_ID = "SELECT * FROM Opera WHERE Cod_O = ?";
    private static final String INSERT_OPERA = "INSERT INTO Opera (Nome, Descrizione, Cod_E_Ar, Cod_E_M) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_OPERA = "UPDATE Opera SET Nome = ?, Descrizione = ?, Cod_E_Ar = ?, Cod_E_M = ? WHERE Cod_O = ?";
    private static final String DELETE_OPERA = "DELETE FROM Opera WHERE Cod_O = ?";

    public List<Opera> getAllOpere(Connection connection) {
        List<Opera> opere = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_OPERE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                opere.add(mapResultSetToOpera(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opere;
    }

    public Opera getOperaById(Connection connection, int operaId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OPERA_BY_ID)) {

            preparedStatement.setInt(1, operaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToOpera(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addOpera(Connection connection, Opera opera) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_OPERA)) {

            preparedStatement.setString(1, opera.getNome());
            preparedStatement.setString(2, opera.getDescrizione());
            preparedStatement.setInt(3, opera.getCodEAr());
            preparedStatement.setInt(4, opera.getCodEM());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOpera(Connection connection, Opera opera) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_OPERA)) {

            preparedStatement.setString(1, opera.getNome());
            preparedStatement.setString(2, opera.getDescrizione());
            preparedStatement.setInt(3, opera.getCodEAr());
            preparedStatement.setInt(4, opera.getCodEM());
            preparedStatement.setInt(5, opera.getCodO());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOpera(Connection connection, int operaId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_OPERA)) {

            preparedStatement.setInt(1, operaId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Opera> search(Connection connection, Opera criteria) {
        List<Opera> matchingOpere = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Opera WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getDescrizione() != null) {
            queryBuilder.append(" AND Descrizione = ?");
        }
        if (criteria.getCodEAr() != null) {
            queryBuilder.append(" AND Cod_E_Ar = ?");
        }
        if (criteria.getCodEM() != null) {
            queryBuilder.append(" AND Cod_E_M = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getDescrizione() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getDescrizione());
            }
            if (criteria.getCodEAr() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEAr());
            }
            if (criteria.getCodEM() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEM());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingOpere.add(mapResultSetToOpera(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingOpere;
    }

    private Opera mapResultSetToOpera(ResultSet resultSet) throws SQLException {
        Opera opera = new Opera();
        opera.setCodO(resultSet.getInt("Cod_O"));
        opera.setNome(resultSet.getString("Nome"));
        opera.setDescrizione(resultSet.getString("Descrizione"));
        opera.setCodEAr(resultSet.getInt("Cod_E_Ar"));
        opera.setCodEM(resultSet.getInt("Cod_E_M"));
        return opera;
    }
}