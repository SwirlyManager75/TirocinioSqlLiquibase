package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllPoisService {

    private final PoiDAO poiDAO;

    public GetAllPoisService( ) {
        this.poiDAO = new PoiDAO();
    }

    public List<Poi> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.getAllPois(connection);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
