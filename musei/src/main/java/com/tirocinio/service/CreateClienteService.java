package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;

public class CreateClienteService {

    private final ClienteDAO clienteDAO;
    private final Connection connection;

    public CreateClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.connection = connection;
    }

    public boolean execute(Cliente cliente) {
        return clienteDAO.addCliente(connection, cliente);
    }
}
