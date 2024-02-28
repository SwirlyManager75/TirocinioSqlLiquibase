package com.tirocinio.service.Insert;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ClienteDAO;
import com.tirocinio.dao.impl.ClienteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateClienteService implements MuseoGenericService{

    private final ClienteDAO clienteDAO;


    public CreateClienteService( ) {
        this.clienteDAO = new ClienteDAOimpl();
       
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {

        Connection connection = ConnectionManager.getConnection();
        Cliente ret;
        Map<Object, Object> output=new HashMap<>();

        try 
        {

            ret=clienteDAO.addCliente(connection, (Cliente)input.get("cliente"));  
            output.put("CreateCliente", ret);   
            connection.commit();
            return output;
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
