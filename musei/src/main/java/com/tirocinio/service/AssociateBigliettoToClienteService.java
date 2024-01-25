package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;

public class AssociateBigliettoToClienteService {

    private final ClienteDAO clienteDAO;
    private final BigliettoDAO bigliettoDAO;
    private final Connection connection;

    public AssociateBigliettoToClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.bigliettoDAO = new BigliettoDAO();
        this.connection = connection;
    }

    public boolean execute(int codCliente, int codBiglietto) {
        // Cerco il cliente con il nome e cognome forniti
        Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
        Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

        if (cliente != null && biglietto != null) {
            //  altri attributi del biglietto se necessario

            return bigliettoDAO.associateWithClient(connection, biglietto,cliente);
        } else {
            // Cliente non trovato
            System.out.println("Cliente o Biglietto non trovato con Codice:" + codCliente +" o " +codBiglietto );
            return false;
        }
    }
}