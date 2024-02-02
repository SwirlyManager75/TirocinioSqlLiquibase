package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
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
        catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
