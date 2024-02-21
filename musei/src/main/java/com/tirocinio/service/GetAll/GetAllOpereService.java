package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.util.List;

public class GetAllOpereService {

    private final OperaDAO operaDAO;

    public GetAllOpereService( ) {
        this.operaDAO = new OperaDAO();
    }

    public List<Opera> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return operaDAO.getAllOpere(connection);
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
