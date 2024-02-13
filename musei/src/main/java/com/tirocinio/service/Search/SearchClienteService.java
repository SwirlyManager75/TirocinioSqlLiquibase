package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchClienteService {

    private final ClienteDAO clienteDAO;

    public SearchClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> execute(Cliente criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return clienteDAO.search(connection, criteria);
        }catch (SQLException |DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
        
    }
}
