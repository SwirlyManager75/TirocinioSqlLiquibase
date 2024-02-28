package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Audio;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.AudioDAO;
import com.tirocinio.dao.impl.AudioDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllAudiosService  implements MuseoGenericService{

    private final AudioDAO audioDAO;

    public GetAllAudiosService( ) {
        this.audioDAO = new AudioDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Audio> list = audioDAO.getAllAudios(connection);
            output.put("GetAllAudios", list);
            return output;
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
