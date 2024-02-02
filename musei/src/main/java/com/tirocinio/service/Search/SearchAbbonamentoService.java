package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
   

    public SearchAbbonamentoService() {
        this.abbonamentoDAO = new AbbonamentoDAO();

    }

    public List<Abbonamento> execute(Abbonamento criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return abbonamentoDAO.search(connection, criteria);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
    }
}
