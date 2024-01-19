package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;

public class UpdatePoiService {

    private final PoiDAO poiDAO;
    private final Connection connection;

    public UpdatePoiService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public boolean execute(Poi poi) {
        return poiDAO.updatePoi(connection, poi);
    }
}
