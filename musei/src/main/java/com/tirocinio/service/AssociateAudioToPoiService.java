package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateAudioToPoiService {

    private final AudioDAO audioDAO;
    private final PoiDAO poiDAO;

    public AssociateAudioToPoiService( ) {
        this.audioDAO = new AudioDAO();
        this.poiDAO = new PoiDAO();
    }

    public boolean execute(int codAudio, int codPoi) throws SQLException {
        // Cerco il Poi con l'ID fornito
        Connection connection = ConnectionManager.getConnection();
        try  {
            connection.setAutoCommit(false);

            Poi poi = poiDAO.getPoiById(connection, codPoi);
            Audio audio = audioDAO.getAudioById(connection, codAudio);

            if (poi != null && audio != null) {
               
                // Inserisco l'audio nel database
                 audioDAO.associateWithPoi(connection, audio,poi);
                 connection.commit();
                 return true;
                 
            } 
            else 
            {
                // Poi non trovato
                System.out.println("Poi o Audio non trovato con ID: " + codPoi + " o "+ codAudio);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }
}