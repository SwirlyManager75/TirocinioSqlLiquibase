package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAudioService {

    private final AudioDAO audioDAO;

    public UpdateAudioService() {
        this.audioDAO = new AudioDAO();
    }

    public Audio execute(Audio audio) throws  ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Audio ret;
        try  {
            ret= audioDAO.updateAudio(connection, audio);
            connection.commit();
            return ret;
        } catch (DAOException e) 
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
