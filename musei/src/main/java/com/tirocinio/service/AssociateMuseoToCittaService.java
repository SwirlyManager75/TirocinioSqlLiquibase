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

    public boolean execute(String nomeMuseo, String viaMuseo, int codCitta) {
        // Cerco la Città con il codice fornito
        Citta citta = cittaDAO.getCityById(connection, codCitta);

        if (citta != null) {
            // Creo un nuovo oggetto Museo
            Museo museo = new Museo();

            museo.setNome(nomeMuseo);
            museo.setVia(viaMuseo);
            museo.setCodECi(citta.getCodCi());

            // altri attributi del Museo se necessario

            // Inserisci il Museo nel database
            return museoDAO.addMuseum(connection, museo);
        } else {
            // Città non trovata
            System.out.println("Città non trovata con codice: " + codCitta);
            return false;
        }
    }
}