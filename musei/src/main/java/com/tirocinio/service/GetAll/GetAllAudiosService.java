package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;

import java.sql.Connection;
import java.util.List;

public class GetAllAudiosService {

    private final AudioDAO audioDAO;

    public GetAllAudiosService( ) {
        this.audioDAO = new AudioDAO();
    }

    public List<Audio> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return audioDAO.getAllAudios(connection);
        }catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }
}
