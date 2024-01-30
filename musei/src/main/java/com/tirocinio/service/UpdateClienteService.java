package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateClienteService {

    private final ClienteDAO clienteDAO;

    public UpdateClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean execute(Cliente cliente) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return clienteDAO.updateCliente(connection, cliente);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
