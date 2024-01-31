package com.tirocinio.service.Associate;

import com.tirocinio.dao.BigliettoDAO;
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

    public boolean execute(int codBiglietto, int codBiglietteria) throws SQLException {
        // Cerco la Biglietteria con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        try {

            Biglietteria biglietteria = biglietteriaDAO.getBiglietteriaById(connection, codBiglietteria);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

            if (biglietteria != null && biglietto != null) {
                // Inserisco il Biglietto nel database
                 bigliettoDAO.associateWithTicketOffice(connection, biglietto,biglietteria);
                 connection.commit();
                return true;

            } else {
                // Biglietteria non trovata
                System.out.println("Biglietteria non trovata con codice: " + codBiglietteria);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
        finally
        {
            connection.close();
        }
    }
}
