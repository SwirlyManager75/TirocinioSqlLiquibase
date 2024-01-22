package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Cliente_Abbonamento;

import java.sql.Connection;

public class AssociateClienteToAbbonamentoService {

    private final ClienteDAO clienteDAO;
    private final AbbonamentoDAO abbonamentoDAO;
    private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final Connection connection;

    public AssociateClienteToAbbonamentoService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.abbonamentoDAO = new AbbonamentoDAO();
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.connection = connection;
    }

    public boolean execute(int codCliente, int codAbbonamento) {
        // Cerco il Cliente con il codice fornito
        Cliente cliente = clienteDAO.getClienteById(connection, codCliente);

        // Cerco l'Abbonamento con il codice fornito
        Abbonamento abbonamento = abbonamentoDAO.getAbbonamentoById(connection, codAbbonamento);

        if (cliente != null && abbonamento != null) {
            // Creo un nuovo oggetto ClienteAbbonamento e associalo al Cliente e all'Abbonamento
            Cliente_Abbonamento clienteAbbonamento = new Cliente_Abbonamento();
            clienteAbbonamento.setCodECli(cliente.getCodCli());
            clienteAbbonamento.setCodEA(abbonamento.getCodAb());

            // altri attributi di ClienteAbbonamento se necessario

            // Inserisco il ClienteAbbonamento nel database
            return clienteAbbonamentoDAO.addClienteAbbonamento(connection, clienteAbbonamento);
        } else {
            // Cliente o Abbonamento non trovato
            System.out.println("Cliente o Abbonamento non trovato con codice: " + codCliente + " o " + codAbbonamento);
            return false;
        }
    }
}
