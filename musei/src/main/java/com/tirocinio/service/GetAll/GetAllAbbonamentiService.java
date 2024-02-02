package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllAbbonamentiService {

    private final AbbonamentoDAO abbonamentoDAO;
    

    public GetAllAbbonamentiService( ) {
        this.abbonamentoDAO = new AbbonamentoDAO();
    }

    public List<Abbonamento> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return abbonamentoDAO.getAllAbbonamenti(connection);
        }catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
        
        
    }
}
