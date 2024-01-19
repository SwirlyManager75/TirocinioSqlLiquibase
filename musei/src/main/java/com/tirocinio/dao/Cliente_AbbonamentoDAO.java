package com.tirocinio.dao;

import com.tirocinio.model.Cliente_Abbonamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_AbbonamentoDAO {

    private static final String SELECT_ALL_CLIENTEABBONAMENTI = "SELECT * FROM Cliente_Abbonamento";
    private static final String SELECT_CLIENTEABBONAMENTO_BY_ID = "SELECT * FROM Cliente_Abbonamento WHERE Cod_CA = ?";
    private static final String INSERT_CLIENTEABBONAMENTO = "INSERT INTO Cliente_Abbonamento (Cod_E_Cli, Cod_E_A) VALUES (?, ?)";
    private static final String UPDATE_CLIENTEABBONAMENTO = "UPDATE Cliente_Abbonamento SET Cod_E_Cli = ?, Cod_E_A = ? WHERE Cod_CA = ?";
    private static final String DELETE_CLIENTEABBONAMENTO = "DELETE FROM Cliente_Abbonamento WHERE Cod_CA = ?";

    public List<Cliente_Abbonamento> getAllClientiAbbonamenti(Connection connection) {
        List<Cliente_Abbonamento> clienteAbbonamenti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTEABBONAMENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clienteAbbonamenti.add(mapResultSetToClienteAbbonamento(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteAbbonamenti;
    }

    public Cliente_Abbonamento getClienteAbbonamentoById(Connection connection, int clienteAbbonamentoId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTEABBONAMENTO_BY_ID)) {

            preparedStatement.setInt(1, clienteAbbonamentoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToClienteAbbonamento(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addClienteAbbonamento(Connection connection, Cliente_Abbonamento clienteAbbonamento) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTEABBONAMENTO)) {

            preparedStatement.setInt(1, clienteAbbonamento.getCodECli());
            preparedStatement.setInt(2, clienteAbbonamento.getCodEA());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateClienteAbbonamento(Connection connection, Cliente_Abbonamento clienteAbbonamento) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTEABBONAMENTO)) {

            preparedStatement.setInt(1, clienteAbbonamento.getCodECli());
            preparedStatement.setInt(2, clienteAbbonamento.getCodEA());
            preparedStatement.setInt(3, clienteAbbonamento.getCodCA());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteClienteAbbonamento(Connection connection, int clienteAbbonamentoId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTEABBONAMENTO)) {

            preparedStatement.setInt(1, clienteAbbonamentoId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente_Abbonamento> search(Connection connection, Cliente_Abbonamento criteria) {
        List<Cliente_Abbonamento> matchingClienteAbbonamenti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cliente_Abbonamento WHERE 1=1");

        if (criteria.getCodECli() != null) {
            queryBuilder.append(" AND Cod_E_Cli = ?");
        }
        if (criteria.getCodEA() != null) {
            queryBuilder.append(" AND Cod_E_A = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getCodECli() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodECli());
            }
            if (criteria.getCodEA() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodEA());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingClienteAbbonamenti.add(mapResultSetToClienteAbbonamento(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingClienteAbbonamenti;
    }

    private Cliente_Abbonamento mapResultSetToClienteAbbonamento(ResultSet resultSet) throws SQLException {
        Cliente_Abbonamento clienteAbbonamento = new Cliente_Abbonamento();
        clienteAbbonamento.setCodCA(resultSet.getInt("Cod_CA"));
        clienteAbbonamento.setCodECli(resultSet.getInt("Cod_E_Cli"));
        clienteAbbonamento.setCodEA(resultSet.getInt("Cod_E_A"));
        return clienteAbbonamento;
    }
}