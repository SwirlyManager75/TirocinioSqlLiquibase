package com.tirocinio.service.GetAll;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllClientiService {

    private final ClienteDAO clienteDAO;
    

    public GetAllClientiService() {
        this.clienteDAO = new ClienteDAO();
        
    }

    public List<Cliente> execute() {
        try (Connection connection = ConnectionManager.getConnection()) {
            return clienteDAO.getAllClienti(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
