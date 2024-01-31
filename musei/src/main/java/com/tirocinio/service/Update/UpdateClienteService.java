package com.tirocinio.service.Update;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateClienteService {

    private final ClienteDAO clienteDAO;

    public UpdateClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean execute(Cliente cliente) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try  {
            ret= clienteDAO.updateCliente(connection, cliente);
            connection.commit();
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
        finally
        {
            connection.close();
        }
    }
}
