package com.tirocinio.service.Delete;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteAudioService {

    private final AudioDAO audioDAO;
    

    public DeleteAudioService( ) {
        this.audioDAO = new AudioDAO();
    }

    public boolean execute(int audioId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            audioDAO.deleteAudio(connection, audioId);          
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
