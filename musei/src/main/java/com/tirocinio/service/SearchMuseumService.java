package com.tirocinio.service;

import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.util.List;

public class SearchMuseumService {

    private final MuseoDAO museoDAO;
    private final Connection connection;

    public SearchMuseumService(Connection connection) {
        this.museoDAO = new MuseoDAO();
        this.connection = connection;
    }

    public List<Museo> execute(Museo criteria) {
        return museoDAO.search(connection, criteria);
    }
}
