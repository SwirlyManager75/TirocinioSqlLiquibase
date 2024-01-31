package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAudioService {

    private final AudioDAO audioDAO;

    public UpdateAudioService() {
        this.audioDAO = new AudioDAO();
    }

    public boolean execute(Audio audio) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try  {
            ret= audioDAO.updateAudio(connection, audio);
            connection.commit();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
        finally
        {
            connection.close();
        }

    }
}
