package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.util.List;

public class GetAllArtistiService {

    private final ArtistaDAO artistaDAO;

    public GetAllArtistiService( ) {
        this.artistaDAO = new ArtistaDAO();
        
    }

    public List<Artista> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return artistaDAO.getAllArtisti(connection);
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
