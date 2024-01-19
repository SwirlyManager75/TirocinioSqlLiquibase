package com.tirocinio.dao;

import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BigliettoDAO {

    private static final String SELECT_ALL_BIGLIETTI = "SELECT * FROM Biglietto";
    private static final String SELECT_BIGLIETTO_BY_ID = "SELECT * FROM Biglietto WHERE Cod_Bi = ?";
    private static final String INSERT_BIGLIETTO = "INSERT INTO Biglietto (Prezzo, Tipo, Data, Cod_E_Cli, Cod_E_B) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BIGLIETTO = "UPDATE Biglietto SET Prezzo = ?, Tipo = ?, Data = ?, Cod_E_Cli = ?, Cod_E_B = ? WHERE Cod_Bi = ?";
    private static final String DELETE_BIGLIETTO = "DELETE FROM Biglietto WHERE Cod_Bi = ?";

    public List<Biglietto> getAllBiglietti(Connection connection) {
        List<Biglietto> biglietti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BIGLIETTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                biglietti.add(mapResultSetToBiglietto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biglietti;
    }

    public Biglietto getBigliettoById(Connection connection, int bigliettoId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BIGLIETTO_BY_ID)) {

            preparedStatement.setInt(1, bigliettoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBiglietto(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addBiglietto(Connection connection, Biglietto biglietto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BIGLIETTO)) {

            preparedStatement.setFloat(1, biglietto.getPrezzo());
            preparedStatement.setString(2, biglietto.getTipo().name());
            preparedStatement.setDate(3, biglietto.getData());
            preparedStatement.setInt(4, biglietto.getCodECli());
            preparedStatement.setInt(5, biglietto.getCodEB());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBiglietto(Connection connection, Biglietto biglietto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BIGLIETTO)) {

            preparedStatement.setFloat(1, biglietto.getPrezzo());
            preparedStatement.setString(2, biglietto.getTipo().name());
            preparedStatement.setDate(3, biglietto.getData());
            preparedStatement.setInt(4, biglietto.getCodECli());
            preparedStatement.setInt(5, biglietto.getCodEB());
            preparedStatement.setInt(6, biglietto.getCodBi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBiglietto(Connection connection, int bigliettoId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BIGLIETTO)) {

            preparedStatement.setInt(1, bigliettoId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Biglietto> search(Connection connection, Biglietto criteria) {
        List<Biglietto> matchingBiglietti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Biglietto WHERE 1=1");

        if (criteria.getPrezzo() != 0) {
            queryBuilder.append(" AND Prezzo = ?");
        }
        if (criteria.getTipo() != null) {
            queryBuilder.append(" AND Tipo = ?");
        }
        if (criteria.getData() != null) {
            queryBuilder.append(" AND Data = ?");
        }
        if (criteria.getCodECli() != null) {
            queryBuilder.append(" AND Cod_E_Cli = ?");
        }
        if (criteria.getCodEB() != null) {
            queryBuilder.append(" AND Cod_E_B = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getPrezzo() != 0) {
                preparedStatement.setFloat(parameterIndex++, criteria.getPrezzo());
            }
            if (criteria.getTipo() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getTipo().name());
            }
            if (criteria.getData() != null) {
                preparedStatement.setDate(parameterIndex++, criteria.getData());
            }
            if (criteria.getCodECli() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodECli());
            }
            if (criteria.getCodEB() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEB());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingBiglietti.add(mapResultSetToBiglietto(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingBiglietti;
    }

    private Biglietto mapResultSetToBiglietto(ResultSet resultSet) throws SQLException {
        Biglietto biglietto = new Biglietto();
        biglietto.setCodBi(resultSet.getInt("Cod_Bi"));
        biglietto.setPrezzo(resultSet.getFloat("Prezzo"));
        biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(resultSet.getString("Tipo")));
        biglietto.setData(resultSet.getDate("Data"));
        biglietto.setCodECli(resultSet.getInt("Cod_E_Cli"));
        biglietto.setCodEB(resultSet.getInt("Cod_E_B"));
        return biglietto;
    }
}