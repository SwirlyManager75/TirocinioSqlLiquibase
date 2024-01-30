package com.tirocinio.service;

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

    public boolean execute(Poi poi) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.updatePoi(connection, poi);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
