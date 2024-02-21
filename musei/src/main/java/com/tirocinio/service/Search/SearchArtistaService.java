package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.util.List;

public class SearchArtistaService {

    private final ArtistaDAO artistaDAO;

    public SearchArtistaService( ) {
        this.artistaDAO = new ArtistaDAO();
    }

    public List<Artista> execute(Artista criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return artistaDAO.search(connection, criteria);
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
