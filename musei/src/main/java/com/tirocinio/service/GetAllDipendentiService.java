package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllDipendentiService {

    private final DipendenteDAO dipendenteDAO;
    

    public GetAllDipendentiService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public List<Dipendente> execute() {

        try (Connection connection = ConnectionManager.getConnection()) {
            return dipendenteDAO.getAllDipendenti(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
