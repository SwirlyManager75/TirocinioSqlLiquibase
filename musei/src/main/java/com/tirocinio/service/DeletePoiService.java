package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;

import java.sql.Connection;

public class DeletePoiService {

    private final PoiDAO poiDAO;
    private final Connection connection;

    public DeletePoiService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public boolean execute(int poiId) {
        return poiDAO.deletePoi(connection, poiId);
    }
}
