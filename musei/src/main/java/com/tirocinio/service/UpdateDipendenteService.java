package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.model.Dipendente;

import java.sql.Connection;

public class UpdateDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    private final Connection connection;

    public UpdateDipendenteService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.connection = connection;
    }

    public boolean execute(Dipendente dipendente) {
        return dipendenteDAO.updateDipendente(connection, dipendente);
    }
}
