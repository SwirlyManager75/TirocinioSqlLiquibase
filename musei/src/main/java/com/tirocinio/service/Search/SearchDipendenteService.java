package com.tirocinio.service.Search;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public SearchDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
        
    }

    public List<Dipendente> execute(Dipendente criteria) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return dipendenteDAO.search(connection, criteria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
