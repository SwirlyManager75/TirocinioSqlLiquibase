package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.PoiDAO;
import com.tirocinio.dao.impl.PoiDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Poi;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPoiService implements MuseoGenericService {

    private final PoiDAO poiDAO;

    public SearchPoiService() {
        this.poiDAO = new PoiDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
                Map<Object, Object> output=new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Poi> list = poiDAO.search(connection, (Poi)input.get("poi"));
            output.put("poi", list);
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
