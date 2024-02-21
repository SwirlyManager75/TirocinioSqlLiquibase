package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Museo;

public class GetByIdMuseoService {

    private final MuseoDAO museoDAO;
    

    public GetByIdMuseoService( ) {
        this.museoDAO = new MuseoDAO();
    }

    public Museo execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return museoDAO.getMuseumById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
