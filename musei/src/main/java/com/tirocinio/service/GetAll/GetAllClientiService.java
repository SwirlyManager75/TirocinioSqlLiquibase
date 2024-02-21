package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.util.List;

public class GetAllClientiService {

    private final ClienteDAO clienteDAO;
    

    public GetAllClientiService() {
        this.clienteDAO = new ClienteDAO();
        
    }

    public List<Cliente> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return clienteDAO.getAllClienti(connection);
        }catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }
}
