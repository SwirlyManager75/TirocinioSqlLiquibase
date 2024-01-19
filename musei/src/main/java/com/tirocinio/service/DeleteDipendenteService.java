package com.tirocinio.service;

import com.tirocinio.dao.DipendenteDAO;

import java.sql.Connection;

public class DeleteDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    private final Connection connection;

    public DeleteDipendenteService(Connection connection) {
        this.dipendenteDAO = new DipendenteDAO();
        this.connection = connection;
    }

    public boolean execute(int dipendenteId) {
        return dipendenteDAO.deleteDipendente(connection, dipendenteId);
    }
}
