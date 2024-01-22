package com.tirocinio.service;

import com.tirocinio.dao.Cliente_AbbonamentoDAO;
import com.tirocinio.model.Cliente_Abbonamento;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Cliente;

import java.sql.Connection;

public class UpdateCliente_AbbonamentoService {

    private final Cliente_AbbonamentoDAO clienteAbbonamentoDAO;
    private final ClienteDAO clienteDAO;
    private final AbbonamentoDAO abbonamentoDAO;
    private final Connection connection;

    public UpdateCliente_AbbonamentoService(Connection connection) {
        this.clienteAbbonamentoDAO = new Cliente_AbbonamentoDAO();
        this.abbonamentoDAO= new AbbonamentoDAO();
        this.clienteDAO= new ClienteDAO();
        this.connection = connection;
    }

    public boolean execute(int codClienteAbbonamento, int nuovoCodCliente, int nuovoCodAbbonamento) {
        // Cerco l'associazione tra cliente e abbonamento con il codice fornito
        Cliente_Abbonamento clienteAbbonamento = clienteAbbonamentoDAO.getClienteAbbonamentoById(connection, codClienteAbbonamento);

        if (clienteAbbonamento != null) {
            // Cerco il cliente con il nuovo codice
            Cliente nuovoCliente = clienteDAO.getClienteById(connection, nuovoCodCliente);

            // Cerco l'abbonamento con il nuovo codice
            Abbonamento nuovoAbbonamento = abbonamentoDAO.getAbbonamentoById(connection, nuovoCodAbbonamento);

            if (nuovoCliente != null && nuovoAbbonamento != null) {
                // Aggiorno le chiavi esterne nella tabella Cliente_Abbonamento
                clienteAbbonamento.setCodECli(nuovoCliente.getCodCli());
                clienteAbbonamento.setCodEA(nuovoAbbonamento.getCodAb());

                // Esegui l'aggiornamento nel database
                return clienteAbbonamentoDAO.updateClienteAbbonamento(connection, clienteAbbonamento);
            } else {
                // Nuovo cliente o nuovo abbonamento non trovato
                System.out.println("Nuovo cliente o nuovo abbonamento non trovato con codice: " + nuovoCodCliente + " o " + nuovoCodAbbonamento);
                return false;
            }
        } else {
            // Associazione non trovata
            System.out.println("Associazione tra cliente e abbonamento non trovata con codice: " + codClienteAbbonamento);
            return false;
        }
    }
}
