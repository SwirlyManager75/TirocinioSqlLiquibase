package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchPoiService {

    private final PoiDAO poiDAO;

    public SearchPoiService() {
        this.poiDAO = new PoiDAO();
    }

    public List<Poi> execute(Poi criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
