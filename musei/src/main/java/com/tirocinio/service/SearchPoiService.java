package com.tirocinio.service;

import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.util.List;

public class SearchPoiService {

    private final PoiDAO poiDAO;
    private final Connection connection;

    public SearchPoiService(Connection connection) {
        this.poiDAO = new PoiDAO();
        this.connection = connection;
    }

    public List<Poi> execute(Poi criteria) {
        return poiDAO.search(connection, criteria);
    }
}
