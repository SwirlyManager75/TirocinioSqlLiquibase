package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllMuseumsService {

    private final MuseoDAO museoDAO;
    

    public GetAllMuseumsService() {
        this.museoDAO = new MuseoDAO();
        
    }

    public List<Museo> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.getAllMuseums(connection);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
