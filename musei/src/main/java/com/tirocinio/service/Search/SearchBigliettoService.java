package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.util.List;

public class SearchBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public SearchBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public List<Biglietto> execute(Biglietto criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.search(connection, criteria);
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
