package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Poi;
import com.tirocinio.model.Museo;

import java.sql.Connection;

public class AssociatePoiToMuseoService{

    private final PoiDAO poiDAO;
    private final MuseoDAO museoDAO;
    private final Connection connection;

    public AssociatePoiToMuseoService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public boolean execute(String descrizione, int museoId) {
        // Cerca il Museo con l'ID fornito
        Museo museo = museoDAO.getMuseumById(connection, museoId);

        if (museo != null) {
            // Crea un nuovo oggetto Poi e associalo al Museo
            Poi poi = new Poi();
            poi.setDescrizione(descrizione);
            
            poi.setCodEM(museo.getCodM());

            // altri attributi del Poi se necessario

            // Inserisco il Poi nel database
            return poiDAO.addPoi(connection, poi);
        } else {
            // Museo non trovato
            System.out.println("Museo non trovato con ID: " + museoId);
            return false;
        }
    }
}