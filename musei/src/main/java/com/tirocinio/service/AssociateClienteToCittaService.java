package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Citta;

import java.sql.Connection;

public class AssociateClienteToCittaService {

    private final ClienteDAO clienteDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateClienteToCittaService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(int codCliente, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);
        Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
        if (citta != null && cliente != null)
        {
            // Inserisci il Cliente nel database
            return clienteDAO.associateWithCity(connection, cliente,citta);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}
