package com.tirocinio.service.Delete;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteClienteService {

    private final ClienteDAO clienteDAO;
    

    public DeleteClienteService( ) {
        this.clienteDAO = new ClienteDAO();
        
    }

    public boolean execute(int clienteId) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            clienteDAO.deleteCliente(connection, clienteId);            
            connection.commit();
            return true;
        } catch (SQLException e) {
            
            e.printStackTrace();
            connection.rollback();
            
        }
        finally
        {
            connection.close();
        }
        
        return false;
         
    }
}
