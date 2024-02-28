package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.AudioDAO;
import com.tirocinio.dao.impl.AudioDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Audio;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdAudiosService implements MuseoGenericService {

    private final AudioDAO audio;
    

    public GetByIdAudiosService( ) {
        this.audio = new AudioDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codAudio = (Integer)input.get("audio");
            Audio ret= audio.getAudioById(connection, codAudio);
            output.put("GetByIdAudio", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
