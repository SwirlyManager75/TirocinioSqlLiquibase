package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchPoiService {

    private final PoiDAO poiDAO;

    public SearchPoiService() {
        this.poiDAO = new PoiDAO();
    }

    public List<Poi> execute(Poi criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.search(connection, criteria);
        } catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
        
    }
}
