package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateMuseumService {

    private final MuseoDAO museoDAO;

    public UpdateMuseumService() {
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(Museo museum) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = museoDAO.updateMuseum(connection, museum);
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
