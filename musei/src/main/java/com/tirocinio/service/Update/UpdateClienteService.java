package com.tirocinio.service.Update;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateClienteService {

    private final ClienteDAO clienteDAO;

    public UpdateClienteService( ) {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente execute(Cliente cliente) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        Cliente ret;
        try  {
            ret= clienteDAO.updateCliente(connection, cliente);
            connection.commit();
            return ret;

        }catch (DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                throw new ServiceException("Rollback non eseguito - errore",e1);
            } 
            throw new ServiceException(e);
            
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico in commit",e);
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                
                throw new ServiceException("Errore durante la chiusura della connessione",e);
            }
        }
    }
}
