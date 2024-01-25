package com.tirocinio.service;

import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Citta;

import java.sql.Connection;

public class AssociateMuseoToCittaService {

    private final MuseoDAO museoDAO;
    private final CittaDAO cittaDAO;
    private final Connection connection;

    public AssociateMuseoToCittaService(Connection connection) {
        this.museoDAO = new MuseoDAO();
        this.cittaDAO = new CittaDAO();
        this.connection = connection;
    }

    public boolean execute(int codMuseo, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);
        if (citta != null && museo != null) {
            // Inserisci il Museo nel database
            return museoDAO.associateWithCity(connection, museo,citta);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}