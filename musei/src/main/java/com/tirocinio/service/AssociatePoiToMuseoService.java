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

    public boolean execute( int museoId, int poiId) {
        // Cerca il Museo con l'ID fornito
        Museo museo = museoDAO.getMuseumById(connection, museoId);
        Poi poi = poiDAO.getPoiById(connection, poiId);
        if (museo != null && poi != null)
        {
            // Aggiorno il Poi nel database
            return poiDAO.associateWithMuseum(connection,poi,museo);
        } else {
            // Museo non trovato
            System.out.println("Museo non trovato con ID: " + museoId);
            return false;
        }
    }
}