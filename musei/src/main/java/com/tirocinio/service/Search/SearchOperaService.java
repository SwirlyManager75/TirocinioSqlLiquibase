package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.util.List;

public class SearchOperaService {

    private final OperaDAO operaDAO;

    public SearchOperaService( ) {
        this.operaDAO = new OperaDAO();
    }

    public List<Opera> execute(Opera criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return operaDAO.search(connection, criteria);
        } catch (DAOException e) 
        {
            throw new ServiceException(e);
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico durante la execute di ",e);
        }
        
    }
}
