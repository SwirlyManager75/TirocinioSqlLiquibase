package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;

import java.sql.Connection;

public class DeleteOperaService {

    private final OperaDAO operaDAO;
    private final Connection connection;

    public DeleteOperaService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.connection = connection;
    }

    public boolean execute(int operaId) {
        return operaDAO.deleteOpera(connection, operaId);
    }
}
