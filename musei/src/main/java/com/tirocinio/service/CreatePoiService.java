package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;

public class CreatePoiService {

    private final PoiDAO poiDAO;
    private final Connection connection;

    public CreatePoiService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public boolean execute(Poi poi) {
        return poiDAO.addPoi(connection, poi);
    }
}
