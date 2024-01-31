package com.tirocinio.service.Associate;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateOperaToArtistaService {

    private final OperaDAO operaDAO;
    private final MuseoDAO museoDAO;

    public AssociateOperaToArtistaService( ) {
        this.operaDAO = new OperaDAO();
        this.museoDAO = new MuseoDAO();
    }

    public boolean execute(int codOp,int codMuseo) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try  {

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Opera opera = operaDAO.getOperaById(connection, codOp);

            if (museo != null && opera !=null) {
               
                // Inserisci l'Opera nel database
                 operaDAO.associateWithMuseo(connection, opera,museo);
                 connection.commit();
                return true;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Museo non trovato con codice: " + codMuseo);
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
