package com.tirocinio.service;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.util.List;

public class GetAllOpereService {

    private final OperaDAO operaDAO;
    private final Connection connection;

    public GetAllOpereService(Connection connection) {
        this.operaDAO = new OperaDAO();
        this.connection = connection;
    }

    public List<Opera> execute() {
        return operaDAO.getAllOpere(connection);
    }
}
