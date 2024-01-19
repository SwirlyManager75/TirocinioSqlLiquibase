package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.model.Opera;

import java.sql.Connection;

public class CreateOperaService {

    private final OperaDAO operaDAO;
    private final Connection connection;

    public CreateOperaService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.connection = connection;
    }

    public boolean execute(Opera opera) {
        return operaDAO.addOpera(connection, opera);
    }
}
