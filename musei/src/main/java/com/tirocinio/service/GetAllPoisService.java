package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.util.List;

public class GetAllPoisService {

    private final PoiDAO poiDAO;
    private final Connection connection;

    public GetAllPoisService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public List<Poi> execute() {
        return poiDAO.getAllPois(connection);
    }
}
