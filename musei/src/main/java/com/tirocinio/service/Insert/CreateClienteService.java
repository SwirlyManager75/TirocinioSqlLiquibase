package com.tirocinio.service.Insert;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateClienteService {

    private final ClienteDAO clienteDAO;


    public CreateClienteService( ) {
        this.clienteDAO = new ClienteDAO();
       
    }

    public boolean execute(Cliente cliente) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        try 
        {
            ret=clienteDAO.addCliente(connection, cliente);       
            connection.commit();
            return ret;
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
         
    }
}
