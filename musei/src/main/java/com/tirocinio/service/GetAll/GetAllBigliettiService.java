package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.util.List;

public class GetAllBigliettiService {

    private final BigliettoDAO bigliettoDAO;

    public GetAllBigliettiService( ) {
        this.bigliettoDAO = new BigliettoDAO();
    }

    public List<Biglietto> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return bigliettoDAO.getAllBiglietti(connection);
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
