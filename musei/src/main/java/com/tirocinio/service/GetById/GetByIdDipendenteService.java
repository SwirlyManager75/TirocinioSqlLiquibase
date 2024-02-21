package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Dipendente;

public class GetByIdDipendenteService {

    private final DipendenteDAO dipendenteDAO;
    

    public GetByIdDipendenteService( ) {
        this.dipendenteDAO = new DipendenteDAO();
    }

    public Dipendente execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return dipendenteDAO.getDipendenteById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
