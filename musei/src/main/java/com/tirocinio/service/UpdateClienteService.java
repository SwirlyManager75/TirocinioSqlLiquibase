package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;

public class UpdateClienteService {

    private final ClienteDAO clienteDAO;
    private final Connection connection;

    public UpdateClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.connection = connection;
    }

    public boolean execute(Cliente cliente) {
        return clienteDAO.updateCliente(connection, cliente);
    }
}
