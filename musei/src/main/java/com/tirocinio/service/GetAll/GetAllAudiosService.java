package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllAudiosService {

    private final AudioDAO audioDAO;

    public GetAllAudiosService( ) {
        this.audioDAO = new AudioDAO();
    }

    public List<Audio> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return audioDAO.getAllAudios(connection);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
