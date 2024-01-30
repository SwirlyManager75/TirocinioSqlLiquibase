package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchClienteService {

    private final ClienteDAO clienteDAO;

    public SearchClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> execute(Cliente criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return clienteDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
