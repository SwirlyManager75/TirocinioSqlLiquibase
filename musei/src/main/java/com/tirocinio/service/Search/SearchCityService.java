package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.util.List;

public class SearchCityService {

    private final CittaDAO cittaDAO;

    public SearchCityService() {
        this.cittaDAO = new CittaDAO();
    }

    public List<Citta> execute(Citta criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return cittaDAO.search(connection, criteria);
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