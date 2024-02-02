package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchBigliettoService {

    private final BigliettoDAO bigliettoDAO;

    public SearchBigliettoService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public List<Biglietto> execute(Biglietto criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.search(connection, criteria);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}