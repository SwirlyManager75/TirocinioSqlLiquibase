package com.tirocinio.service;

import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.model.Cliente_Abbonamento;

import java.sql.Connection;
import java.util.List;

public class SearchCliente_AbbonamentoService {

    private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final Connection connection;

    public SearchCliente_AbbonamentoService(Connection connection) {
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.connection = connection;
    }

    public List<Cliente_Abbonamento> execute(Cliente_Abbonamento criteria) {
        return clienteAbbonamentoDAO.search(connection, criteria);
    }
}
