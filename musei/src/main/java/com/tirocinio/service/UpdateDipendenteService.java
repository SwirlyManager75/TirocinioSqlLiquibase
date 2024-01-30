package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDipendenteService {

    private final DipendenteDAO dipendenteDAO;

    public UpdateDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public boolean execute(Dipendente dipendente) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return dipendenteDAO.updateDipendente(connection, dipendente);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
