package com.tirocinio.dao;

import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DipendenteDAO {

    private static final String SELECT_ALL_DIPENDENTI = "SELECT * FROM Dipendente";
    private static final String SELECT_DIPENDENTE_BY_ID = "SELECT * FROM Dipendente WHERE Cod_D = ?";
    private static final String INSERT_DIPENDENTE = "INSERT INTO Dipendente (Nome, Data_Nascita, CF, Cellulare, Cod_E_Ci, Cod_E_M) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DIPENDENTE = "UPDATE Dipendente SET Nome = ?, Data_Nascita = ?, CF = ?, Cellulare = ?, Cod_E_Ci = ?, Cod_E_M = ? WHERE Cod_D = ?";
    private static final String DELETE_DIPENDENTE = "DELETE FROM Dipendente WHERE Cod_D = ?";

    public List<Dipendente> getAllDipendenti(Connection connection) {
        List<Dipendente> dipendenti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIPENDENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                dipendenti.add(mapResultSetToDipendente(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dipendenti;
    }

    public Dipendente getDipendenteById(Connection connection, int dipendenteId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIPENDENTE_BY_ID)) {

            preparedStatement.setInt(1, dipendenteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDipendente(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addDipendente(Connection connection, Dipendente dipendente) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIPENDENTE)) {

            preparedStatement.setString(1, dipendente.getNome());
            preparedStatement.setDate(2, dipendente.getDataNascita());
            preparedStatement.setString(3, dipendente.getCodiceFiscale());
            preparedStatement.setString(4, dipendente.getCellulare());
            preparedStatement.setInt(5, dipendente.getCodECi());
            preparedStatement.setInt(6, dipendente.getCodEM());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDipendente(Connection connection, Dipendente dipendente) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIPENDENTE)) {

            preparedStatement.setString(1, dipendente.getNome());
            preparedStatement.setDate(2, dipendente.getDataNascita());
            preparedStatement.setString(3, dipendente.getCodiceFiscale());
            preparedStatement.setString(4, dipendente.getCellulare());
            preparedStatement.setInt(5, dipendente.getCodECi());
            preparedStatement.setInt(6, dipendente.getCodEM());
            preparedStatement.setInt(7, dipendente.getCodD());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDipendente(Connection connection, int dipendenteId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DIPENDENTE)) {

            preparedStatement.setInt(1, dipendenteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Dipendente> search(Connection connection, Dipendente criteria) {
        List<Dipendente> matchingDipendenti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Dipendente WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getDataNascita() != null) {
            queryBuilder.append(" AND Data_Nascita = ?");
        }
        if (criteria.getCodiceFiscale() != null) {
            queryBuilder.append(" AND CF = ?");
        }
        if (criteria.getCellulare() != null) {
            queryBuilder.append(" AND Cellulare = ?");
        }
        if (criteria.getCodECi() != null) {
            queryBuilder.append(" AND Cod_E_Ci = ?");
        }
        if (criteria.getCodEM() != null) {
            queryBuilder.append(" AND Cod_E_M = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getDataNascita() != null) {
                preparedStatement.setDate(parameterIndex++, criteria.getDataNascita());
            }
            if (criteria.getCodiceFiscale() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCodiceFiscale());
            }
            if (criteria.getCellulare() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCellulare());
            }
            if (criteria.getCodECi() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodECi());
            }
            if (criteria.getCodEM() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEM());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingDipendenti.add(mapResultSetToDipendente(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingDipendenti;
    }

    private Dipendente mapResultSetToDipendente(ResultSet resultSet) throws SQLException {
        Dipendente dipendente = new Dipendente();
        dipendente.setCodD(resultSet.getInt("Cod_D"));
        dipendente.setNome(resultSet.getString("Nome"));
        dipendente.setDataNascita(resultSet.getDate("Data_Nascita"));
        dipendente.setCodiceFiscale(resultSet.getString("CF"));
        dipendente.setCellulare(resultSet.getString("Cellulare"));
        dipendente.setCodECi(resultSet.getInt("Cod_E_Ci"));
        dipendente.setCodEM(resultSet.getInt("Cod_E_M"));
        return dipendente;
    }
}