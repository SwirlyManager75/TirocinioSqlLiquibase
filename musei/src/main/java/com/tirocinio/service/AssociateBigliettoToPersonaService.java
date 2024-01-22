package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.util.List;

public class AssociateBigliettoToPersonaService {

    private final ClienteDAO clienteDAO;
    private final BigliettoDAO bigliettoDAO;
    private final Connection connection;

    public AssociateBigliettoToPersonaService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.bigliettoDAO = new BigliettoDAO();
        this.connection = connection;
    }

    public boolean execute(String nome, String cognome, int idBiglietteria) {
        // Cerco il cliente con il nome e cognome forniti
        List<Cliente> clienti = clienteDAO.search(connection, new Cliente(nome, cognome));

        if (!clienti.isEmpty()) {
            // Prendi il primo cliente trovato (puoi gestire più clienti se necessario)
            Cliente cliente = clienti.get(0);

            // Creo un nuovo biglietto e inseriscilo
            Biglietto biglietto = new Biglietto();
            biglietto.setCodEB(idBiglietteria);
            biglietto.setCodECli(cliente.getCodCli());

            //  altri attributi del biglietto se necessario

            return bigliettoDAO.addBiglietto(connection, biglietto);
        } else {
            // Cliente non trovato
            System.out.println("Cliente non trovato con nome: " + nome + " e cognome: " + cognome);
            return false;
        }
    }
}