package com.tirocinio.service.Search;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;

import java.sql.Connection;
import java.util.List;

public class SearchAbbonamentoService {

    private final AbbonamentoDAO abbonamentoDAO;
   

    public SearchAbbonamentoService() {
        this.abbonamentoDAO = new AbbonamentoDAO();

    }

    public List<Abbonamento> execute(Abbonamento criteria) throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return abbonamentoDAO.search(connection, criteria);
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
