package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Museo;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.MuseoDAO;
import com.tirocinio.dao.impl.MuseoDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllMuseumsService  implements MuseoGenericService{

    private final MuseoDAO museoDAO;
    

    public GetAllMuseumsService() {
        this.museoDAO = new MuseoDAOimpl();
        
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Museo> list = museoDAO.getAllMuseums(connection);
            output.put("GetAllMusei", list);
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
