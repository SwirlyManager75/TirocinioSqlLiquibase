package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.PoiDAO;
import com.tirocinio.dao.impl.PoiDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Poi;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdPoiService implements MuseoGenericService {

    private final PoiDAO poiDAO;
    

    public GetByIdPoiService( ) {
        this.poiDAO = new PoiDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codPoi = (Integer)input.get("poi");
            Poi ret= poiDAO.getPoiById(connection, codPoi);
            output.put("GetByIdPoi", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }


}
