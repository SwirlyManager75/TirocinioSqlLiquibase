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

    public boolean execute(String nome, String cognome, String mail, String cellulare, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);

        if (citta != null)
        {
            // Creo un nuovo oggetto Cliente e associalo alla Città
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            cliente.setMail(mail);
            cliente.setCellulare(cellulare);

            cliente.setCodECi(citta.getCodCi());

            // altri attributi del Cliente se necessario

            // Inserisci il Cliente nel database
            return clienteDAO.addCliente(connection, cliente);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}
