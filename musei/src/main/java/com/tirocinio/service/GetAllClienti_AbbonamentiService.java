package com.tirocinio.service;

import java.sql.Connection;
import java.util.List;

import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.model.Cliente_Abbonamento;

public class GetAllClienti_AbbonamentiService {

     private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final Connection connection;

    public GetAllClienti_AbbonamentiService(Connection connection) {
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.connection = connection;
    }

    public List<Cliente_Abbonamento> execute() {
        return clienteAbbonamentoDAO.getAllClientiAbbonamenti(connection);
    }

}
