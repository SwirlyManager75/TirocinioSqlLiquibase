package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.OperaDAO;
import com.tirocinio.dao.impl.OperaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Opera;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdOperaService implements MuseoGenericService {

    private final OperaDAO operaDAO;
    

    public GetByIdOperaService( ) {
        this.operaDAO = new OperaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codAbbonamento = (Integer)input.get("abbonamento");
            Opera ret = operaDAO.getOperaById(connection, codAbbonamento);
            output.put("GetByIdOpera", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
