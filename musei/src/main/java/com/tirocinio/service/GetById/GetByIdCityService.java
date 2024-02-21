package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Citta;

public class GetByIdCityService {

    private final CittaDAO cittaDAO;
    

    public GetByIdCityService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public Citta execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return cittaDAO.getCityById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
