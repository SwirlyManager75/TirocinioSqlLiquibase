package com.tirocinio.service.GetById;

import java.sql.Connection;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.model.Artista;

public class GetByIdArtistiService {

    private final ArtistaDAO artistaDAO;
    

    public GetByIdArtistiService() {
        this.artistaDAO = new ArtistaDAO();
    }

    public Artista execute(int codice) throws ServiceException
    {
        Connection connection = ConnectionManager.getConnection();
        
        try 
        {
            return artistaDAO.getArtistaById(connection, codice);
        } 
        catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        

    }

}
