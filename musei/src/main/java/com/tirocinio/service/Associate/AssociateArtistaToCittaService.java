package com.tirocinio.service.Associate;

import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateArtistaToCittaService {

    private final ArtistaDAO artistaDAO;
    private final CittaDAO cittaDAO;

    public AssociateArtistaToCittaService( ) {
        this.artistaDAO = new ArtistaDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codAr, int codCitta) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        // Cerco la Città con il codice fornito
        try {
            boolean ret;

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Artista artista = artistaDAO.getArtistaById(connection, codAr);

            if (citta != null && artista != null) {
                
                // Inserisco l'Artista nel database
                ret=artistaDAO.addArtista(connection, artista);
                connection.commit();
                return ret;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
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