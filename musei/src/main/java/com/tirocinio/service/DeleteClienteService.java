package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;

import java.sql.Connection;

public class DeleteClienteService {

    private final ClienteDAO clienteDAO;
    private final Connection connection;

    public DeleteClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.connection = connection;
    }

    public boolean execute(int clienteId) {
        return clienteDAO.deleteCliente(connection, clienteId);
    }
}
