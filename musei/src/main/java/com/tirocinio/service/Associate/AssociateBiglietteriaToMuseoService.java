package com.tirocinio.service.Associate;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateBiglietteriaToMuseoService {

    private final BiglietteriaDAO biglietteriaDAO;
    private final MuseoDAO museoDAO;

    public AssociateBiglietteriaToMuseoService() {
        this.biglietteriaDAO = new BiglietteriaDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(int codBiglietteria, int codMuseo) throws  ServiceException {
        // Cerco il Museo con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Biglietteria biglietteria= biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);

            if (museo != null && biglietteria != null) {
                
                // Inserisco la Biglietteria nel database
                ret=biglietteriaDAO.associateWithMuseum(connection, biglietteria,museo);
                 connection.commit();
                return ret;

            } else {
                // Museo non trovato
                System.out.println("Museo non trovato con codice: " + codMuseo);
                return false;
            }
        }catch (SQLException | DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            } 
            throw new ServiceException("In execute - DAOException ");
            
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}