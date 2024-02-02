package com.tirocinio.service.Insert;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateAudioService {

    private final AudioDAO audioDAO;

    public CreateAudioService( ) 
    {
        this.audioDAO = new AudioDAO();
    }

    public boolean execute(Audio audio) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try 
        {
            ret=audioDAO.addAudio(connection, audio);            
            connection.commit();
            return ret;
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
