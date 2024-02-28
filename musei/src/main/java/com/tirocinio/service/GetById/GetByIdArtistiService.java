package com.tirocinio.service.GetById;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ArtistaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Artista;
import com.tirocinio.service.MuseoGenericService;

public class GetByIdArtistiService implements MuseoGenericService {

    private final ArtistaDAO artistaDAO;
    

    public GetByIdArtistiService() {
        this.artistaDAO = new ArtistaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        Map<Object, Object> output = new HashMap<>();

        
        try 
        {
            int codArtista = (Integer)input.get("artista");
            Artista ret = artistaDAO.getArtistaById(connection, codArtista);
            output.put("GetByIdArtisti", ret);
            return output;

        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

    

}
