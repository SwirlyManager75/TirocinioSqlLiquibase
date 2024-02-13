package com.tirocinio.service.GetAll;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllBiglietterieService {

    private final BiglietteriaDAO biglietteriaDAO;

    public GetAllBiglietterieService( ) {
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public List<Biglietteria> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return biglietteriaDAO.getAllBiglietterie(connection);
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
