package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchMuseumService {

    private final MuseoDAO museoDAO;

    public SearchMuseumService() {
        this.museoDAO = new MuseoDAO();
    }

    public List<Museo> execute(Museo criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return museoDAO.search(connection, criteria);
        }catch (SQLException |DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
    }
}
