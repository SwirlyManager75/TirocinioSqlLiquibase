package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ClienteDAO;
import com.tirocinio.dao.impl.ClienteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Cliente;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchClienteService implements MuseoGenericService {

    private final ClienteDAO clienteDAO;

    public SearchClienteService( ) {
        this.clienteDAO = new ClienteDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
                Map<Object, Object> output=new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Cliente> list = clienteDAO.search(connection, (Cliente)input.get("cliente"));
            output.put("SearchCliente", list);
            return output;
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
