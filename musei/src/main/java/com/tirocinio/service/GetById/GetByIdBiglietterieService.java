package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Biglietteria;

public class GetByIdBiglietterieService {

    private final BiglietteriaDAO biglietteriaDAO;
    

    public GetByIdBiglietterieService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public Biglietteria execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return biglietteriaDAO.getBiglietteriaById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
