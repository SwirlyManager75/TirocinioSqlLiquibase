package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateAudioService {

    private final AudioDAO audioDAO;

    public CreateAudioService( ) 
    {
        this.audioDAO = new AudioDAO();
    }

    public boolean execute(Audio audio) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try 
        {
            audioDAO.addAudio(connection, audio);            
            connection.commit();
            return true;
        } catch (SQLException e) {
            
            e.printStackTrace();
            connection.rollback();
            
        }
        finally
        {
            connection.close();
        }
        
        return false;
        
    }
}
