package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BiglietteriaDAO;
import com.tirocinio.dao.impl.BiglietteriaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdBiglietterieService implements MuseoGenericService {

    private final BiglietteriaDAO biglietteriaDAO;
    

    public GetByIdBiglietterieService( ) {
        this.biglietteriaDAO = new BiglietteriaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codBiglietteria = (Integer)input.get("biglietteria");
            Biglietteria ret= biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);
            output.put("GetByIdBiglietterie", ret);
            return output;
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
