package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Biglietto;

public class GetByIdBigliettoService {

    private final BigliettoDAO bigliettoDAO;
    

    public GetByIdBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public Biglietto execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return bigliettoDAO.getBigliettoById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
