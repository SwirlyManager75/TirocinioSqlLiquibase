package com.tirocinio.service.Associate;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
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

    public boolean execute(int codAudio, int codPoi) throws ServiceException {
        // Cerco il Poi con l'ID fornito
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            Poi poi = poiDAO.getPoiById(connection, codPoi);
            Audio audio = audioDAO.getAudioById(connection, codAudio);

            if (poi != null && audio != null) {
               
                // Inserisco l'audio nel database
                 ret=audioDAO.associateWithPoi(connection, audio,poi);
                 connection.commit();
                 return ret;
                 
            } 
            else 
            {
                // Poi non trovato
                System.out.println("Poi o Audio non trovato con ID: " + codPoi + " o "+ codAudio);
                return false;
            }
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}