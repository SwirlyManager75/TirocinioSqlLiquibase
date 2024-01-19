package com.tirocinio.service;

import com.tirocinio.dao.Cliente_AbbonamentoDAO;

import java.sql.Connection;

public class DeleteCliente_AbbonamentoService {

    private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final Connection connection;

    public DeleteCliente_AbbonamentoService(Connection connection) {
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.connection = connection;
    }

    public boolean execute(int clienteAbbonamentoId) {
        return clienteAbbonamentoDAO.deleteClienteAbbonamento(connection, clienteAbbonamentoId);
    }
}
