package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Artista;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.Interfaces.ArtistaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.service.MuseoGenericService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllArtistiService implements MuseoGenericService {

    private final ArtistaDAO artistaDAO;

    public GetAllArtistiService( ) {
        this.artistaDAO = new ArtistaDAOimpl();
        
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Map<Object, Object> output = new HashMap<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            List<Artista> list = artistaDAO.getAllArtisti(connection);
            output.put("GetAllGetAllArtisti", list);
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
