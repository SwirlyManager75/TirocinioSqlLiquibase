package com.tirocinio.service.Insert;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateClienteService {

    private final ClienteDAO clienteDAO;


    public CreateClienteService( ) {
        this.clienteDAO = new ClienteDAO();
       
    }

    public boolean execute(Cliente cliente) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        try 
        {
            clienteDAO.addCliente(connection, cliente);       
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
