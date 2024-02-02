package com.tirocinio.service.Search;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchOperaService {

    private final OperaDAO operaDAO;

    public SearchOperaService( ) {
        this.operaDAO = new OperaDAO();
    }

    public List<Opera> execute(Opera criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return operaDAO.search(connection, criteria);
        } catch (SQLException | DAOException e) 
        {
            throw new ServiceException("In execute - DAOException ");
        }
        
    }
}
