package com.tirocinio.service.Associate;

import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Biglietteria;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateBigliettoToBiglietteriaService {

    private final BigliettoDAO bigliettoDAO;
    private final BiglietteriaDAO biglietteriaDAO;

    public AssociateBigliettoToBiglietteriaService() {
        this.bigliettoDAO = new BigliettoDAO();
        this.biglietteriaDAO = new BiglietteriaDAO();
    }

    public boolean execute(int codBiglietto, int codBiglietteria) throws ServiceException {
        // Cerco la Biglietteria con il codice fornito
        
    	Connection connection = null;
        boolean ret;

        try {
        	
        	connection = ConnectionManager.getConnection();

            Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

            if (biglietteria != null && biglietto != null) {
                // Inserisco il Biglietto nel database
                ret=bigliettoDAO.associateWithTicketOffice(connection, biglietto,biglietteria);
                 connection.commit();
                return ret;

            } else {
                // Biglietteria non trovata
                System.out.println("Biglietteria non trovata con codice: " + codBiglietteria);
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
