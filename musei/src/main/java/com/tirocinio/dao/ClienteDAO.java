package com.tirocinio.dao;

import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String SELECT_ALL_CLIENTI = "SELECT * FROM Cliente";
    private static final String SELECT_CLIENTE_BY_ID = "SELECT * FROM Cliente WHERE Cod_Cli = ?";
    private static final String INSERT_CLIENTE = "INSERT INTO Cliente (Nome, Cognome, Mail, Cellulare, Cod_E_Ci) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CLIENTE = "UPDATE Cliente SET Nome = ?, Cognome = ?, Mail = ?, Cellulare = ?, Cod_E_Ci = ? WHERE Cod_Cli = ?";
    private static final String DELETE_CLIENTE = "DELETE FROM Cliente WHERE Cod_Cli = ?";

    public List<Cliente> getAllClienti(Connection connection) {
        List<Cliente> clienti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clienti.add(mapResultSetToCliente(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }

    public Cliente getClienteById(Connection connection, int clienteId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTE_BY_ID)) {

            preparedStatement.setInt(1, clienteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCliente(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCliente(Connection connection, Cliente cliente) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getMail());
            preparedStatement.setString(4, cliente.getCellulare());
            preparedStatement.setInt(5, cliente.getCodECi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCliente(Connection connection, Cliente cliente) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getMail());
            preparedStatement.setString(4, cliente.getCellulare());
            preparedStatement.setInt(5, cliente.getCodECi());
            preparedStatement.setInt(6, cliente.getCodCli());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCliente(Connection connection, int clienteId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTE)) {

            preparedStatement.setInt(1, clienteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> search(Connection connection, Cliente criteria) {
        List<Cliente> matchingClienti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cliente WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getCognome() != null) {
            queryBuilder.append(" AND Cognome = ?");
        }
        if (criteria.getMail() != null) {
            queryBuilder.append(" AND Mail = ?");
        }
        if (criteria.getCellulare() != null) {
            queryBuilder.append(" AND Cellulare = ?");
        }
        if (criteria.getCodECi() != null) {
            queryBuilder.append(" AND Cod_E_Ci = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getCognome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCognome());
            }
            if (criteria.getMail() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getMail());
            }
            if (criteria.getCellulare() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCellulare());
            }
            if (criteria.getCodECi() != null) {
                preparedStatement.setInt(parameterIndex++, criteria.getCodECi());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingClienti.add(mapResultSetToCliente(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingClienti;
    }

    private Cliente mapResultSetToCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCodCli(resultSet.getInt("Cod_Cli"));
        cliente.setNome(resultSet.getString("Nome"));
        cliente.setCognome(resultSet.getString("Cognome"));
        cliente.setMail(resultSet.getString("Mail"));
        cliente.setCellulare(resultSet.getString("Cellulare"));
        cliente.setCodECi(resultSet.getInt("Cod_E_Ci"));
        return cliente;
    }
}