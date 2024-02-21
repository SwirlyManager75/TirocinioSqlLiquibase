package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Abbonamento;

public class GetByIdAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public GetByIdAbbonamentoService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
    }

    public Abbonamento execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return abbonamentoDAO.getAbbonamentoById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }


}
