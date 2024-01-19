package com.tirocinio.service;

import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.model.Cliente_Abbonamento;

import java.sql.Connection;

public class CreateCliente_AbbonamentoService {

    private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final Connection connection;

    public CreateCliente_AbbonamentoService(Connection connection) {
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.connection = connection;
    }

    public boolean execute(Cliente_Abbonamento clienteAbbonamento) {
        return clienteAbbonamentoDAO.addClienteAbbonamento(connection, clienteAbbonamento);
    }
}
