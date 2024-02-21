package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.util.List;

public class GetAllMuseumsService {

    private final MuseoDAO museoDAO;
    

    public GetAllMuseumsService() {
        this.museoDAO = new MuseoDAO();
        
    }

    public List<Museo> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.getAllMuseums(connection);
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
