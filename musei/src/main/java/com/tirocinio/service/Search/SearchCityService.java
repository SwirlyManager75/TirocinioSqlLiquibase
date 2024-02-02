package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchCityService {

    private final CittaDAO cittaDAO;

    public SearchCityService() {
        this.cittaDAO = new CittaDAO();
    }

    public List<Citta> execute(Citta criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return cittaDAO.search(connection, criteria);
        } catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}