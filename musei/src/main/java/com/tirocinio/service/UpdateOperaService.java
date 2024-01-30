package com.tirocinio.service;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateOperaService {

    private final OperaDAO operaDAO;

    public UpdateOperaService() {
        this.operaDAO = new OperaDAO();
    }

    public boolean execute(Opera opera) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return operaDAO.updateOpera(connection, opera);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
