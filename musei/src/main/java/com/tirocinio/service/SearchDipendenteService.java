package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.util.List;

public class SearchDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    private final Connection connection;

    public SearchDipendenteService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.connection = connection;
    }

    public List<Dipendente> execute(Dipendente criteria) {
        return dipendenteDAO.search(connection, criteria);
    }
}
