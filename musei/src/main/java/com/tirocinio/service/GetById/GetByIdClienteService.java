package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ClienteDAO;
import com.tirocinio.dao.impl.ClienteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Cliente;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdClienteService implements MuseoGenericService {

    private final ClienteDAO clienteDAO;
    

    public GetByIdClienteService( ) {
        this.clienteDAO = new ClienteDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codCliente = (Integer)input.get("cliente");
            Cliente ret= clienteDAO.getClienteById(connection, codCliente);
            output.put("GetByIdCliente", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
