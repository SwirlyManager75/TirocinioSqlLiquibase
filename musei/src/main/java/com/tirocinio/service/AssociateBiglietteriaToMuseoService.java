package com.tirocinio.service;

import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;

public class AssociateBiglietteriaToMuseoService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final MuseoDAO museoDAO;
    private final Connection connection;

    public AssociateBiglietteriaToMuseoService(Connection connection) {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(int codBiglietteria, int codMuseo) {
        // Cerco il Museo con il codice fornito
        Museo museo = museoDAO.getMuseumById(connection, codMuseo);
        Biglietteria biglietteria= biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

        if (museo != null && biglietteria != null) {
            
            // Inserisco la Biglietteria nel database
            return biglietteriaDAO.associateWithMuseum(connection, biglietteria,museo);
        } else {
            // Museo non trovato
            System.out.println("Museo non trovato con codice: " + codMuseo);
            return false;
        }
    }
}