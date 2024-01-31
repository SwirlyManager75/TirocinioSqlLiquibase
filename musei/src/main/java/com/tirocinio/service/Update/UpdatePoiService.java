package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdatePoiService {

    private final PoiDAO poiDAO;

    public UpdatePoiService() {
        this.poiDAO = new PoiDAO();
    }

    public boolean execute(Poi poi) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try {
            ret = poiDAO.updatePoi(connection, poi);
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
