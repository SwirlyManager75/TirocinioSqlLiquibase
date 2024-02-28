package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.BigliettoDAO;
import com.tirocinio.dao.impl.BigliettoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Biglietto;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdBigliettoService implements MuseoGenericService {

    private final BigliettoDAO bigliettoDAO;
    

    public GetByIdBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codBiglietto= (Integer)input.get("biglietto");
            Biglietto ret= bigliettoDAO.getBigliettoById(connection, codBiglietto);
            output.put("GetByIdBiglietto", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
