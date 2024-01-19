package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.util.List;

public class SearchClienteService {

    private final ClienteDAO clienteDAO;
    private final Connection connection;

    public SearchClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.connection = connection;
    }

    public List<Cliente> execute(Cliente criteria) {
        return clienteDAO.search(connection, criteria);
    }
}
