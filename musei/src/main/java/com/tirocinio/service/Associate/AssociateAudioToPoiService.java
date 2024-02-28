package com.tirocinio.service.Associate;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.AudioDAOimpl;
import com.tirocinio.dao.impl.PoiDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Poi;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateAudioToPoiService implements MuseoGenericService {

    private final AudioDAOimpl audioDAO;
    private final PoiDAOimpl poiDAO;

    public AssociateAudioToPoiService( ) {
        this.audioDAO = new AudioDAOimpl();
        this.poiDAO = new PoiDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        // Cerco il Poi con l'ID fornito
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();
        boolean ret;

        try  {
            int codAudio=(Integer)input.get("audio");
            int codPoi=(Integer)input.get("poi");
            Poi poi = poiDAO.getPoiById(connection, codAudio);
            Audio audio = audioDAO.getAudioById(connection, codPoi);
            

            if (poi != null && audio != null) {
               
                // Inserisco l'audio nel database
                 ret=audioDAO.associateWithPoi(connection, audio,poi);
                 output.put("AssociateAudioToPoi", ret);
                 connection.commit();
                 return output;
                 
            } 
            else 
            {
                // Poi non trovato
                System.out.println("Poi o Audio non trovato con ID: " + codPoi + " o "+ codAudio);
                output.put("AssociateAudioToPoi", false);

                return output;
            }
        }catch (DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                throw new ServiceException("Rollback non eseguito - errore",e1);
            } 
            throw new ServiceException(e);
            
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico in commit",e);
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                
                throw new ServiceException("Errore durante la chiusura della connessione",e);
            }
        }
    }
}