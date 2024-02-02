package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchBiglietteriaService {

    private final BiglietteriaDAO biglietteriaDAO;

    public SearchBiglietteriaService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public List<Biglietteria> execute(Biglietteria criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return biglietteriaDAO.search(connection, criteria);
        } catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
