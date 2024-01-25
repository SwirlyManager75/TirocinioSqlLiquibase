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

    public boolean execute(int codAudio, int codPoi) {
        // Cerco il Poi con l'ID fornito
        Poi poi = poiDAO.getPoiById(connection, codPoi);
        Audio audio = audioDAO.getAudioById(connection, codAudio);

        if (poi != null && audio != null) {
           
            // Inserisco l'audio nel database
            return audioDAO.associateWithPoi(connection, audio,poi);
        } 
        else 
        {
            // Poi non trovato
            System.out.println("Poi o Audio non trovato con ID: " + codPoi + " o "+ codAudio);
            return false;
        }
    }
}