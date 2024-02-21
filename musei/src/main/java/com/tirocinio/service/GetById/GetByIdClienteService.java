package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Cliente;

public class GetByIdClienteService {

    private final ClienteDAO clienteDAO;
    

    public GetByIdClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return clienteDAO.getClienteById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
