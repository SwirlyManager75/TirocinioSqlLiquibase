package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.util.List;

public class GetAllCitiesService {

    private final CittaDAO cittaDAO;

    public GetAllCitiesService( ) {
        this.cittaDAO = new CittaDAO();
    }

    public List<Citta> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) 
        {
            return cittaDAO.getAllCities(connection);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }
}
