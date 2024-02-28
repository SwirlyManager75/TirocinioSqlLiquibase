package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Poi;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.PoiDAO;
import com.tirocinio.dao.impl.PoiDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllPoisService implements MuseoGenericService {

    private final PoiDAO poiDAO;

    public GetAllPoisService( ) {
        this.poiDAO = new PoiDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Poi> list = poiDAO.getAllPois(connection);
            output.put("GetAllPois", list);
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
