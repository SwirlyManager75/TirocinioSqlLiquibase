package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.MuseoDAO;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Museo;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdMuseoService  implements MuseoGenericService{

    private final MuseoDAO museoDAO;
    

    public GetByIdMuseoService( ) {
        this.museoDAO = new MuseoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codMuseo= (Integer)input.get("museo");
            Museo ret= museoDAO.getMuseumById(connection, codMuseo);
            output.put("GetByIdMuseo", ret);
            return output;
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }


}
