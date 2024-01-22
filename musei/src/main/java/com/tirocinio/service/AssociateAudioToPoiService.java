package com.tirocinio.service;

import com.tirocinio.dao.AudioDAO;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Poi;

import java.sql.Connection;

public class AssociateAudioToPoiService {

    private final AudioDAO audioDAO;
    private final PoiDAO poiDAO;
    private final Connection connection;

    public AssociateAudioToPoiService(Connection connection) {
        this.audioDAO = new AudioDAO();
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public boolean execute(String audioURL, int poiId) {
        // Cerco il Poi con l'ID fornito
        Poi poi = poiDAO.getPoiById(connection, poiId);

        if (poi != null) {
            // Creo un nuovo oggetto Audio e associalo al Poi
            Audio audio = new Audio();
            audio.setUrl(audioURL);
            
            audio.setCodEPoi(poi.getCodPoi());

            // Inserisco l'audio nel database
            return audioDAO.addAudio(connection, audio);
        } 
        else 
        {
            // Poi non trovato
            System.out.println("Poi non trovato con ID: " + poiId);
            return false;
        }
    }
}