package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.CittaDAO;
import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Citta;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdCityService implements MuseoGenericService {

    private final CittaDAO cittaDAO;
    

    public GetByIdCityService( ) {
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codCitta = (Integer)input.get("citta");
            Citta ret= cittaDAO.getCityById(connection, codCitta);
            output.put("GetByIdCitta", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }


}
