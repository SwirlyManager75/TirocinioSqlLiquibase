package com.tirocinio.service.GetAll;

import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.util.List;

public class GetAllPoisService {

    private final PoiDAO poiDAO;

    public GetAllPoisService( ) {
        this.poiDAO = new PoiDAO();
    }

    public List<Poi> execute() throws ServiceException {
        try (Connection connection = ConnectionManager.getConnection()) {
            return poiDAO.getAllPois(connection);
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
