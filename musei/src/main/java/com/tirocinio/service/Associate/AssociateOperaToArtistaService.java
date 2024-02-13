package com.tirocinio.service.Associate;

import com.tirocinio.dao.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
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

    public boolean execute(int codOp,int codMuseo) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            Museo museo = museoDAO.getMuseumById(connection, codMuseo);
            Opera opera = operaDAO.getOperaById(connection, codOp);

            if (museo != null && opera !=null) {
               
                // Inserisci l'Opera nel database
                ret=operaDAO.associateWithMuseo(connection, opera,museo);
                 connection.commit();
                return ret;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Museo non trovato con codice: " + codMuseo);
                return false;
            }
        }catch (DAOException e) 
        {
            
            try {
                connection.rollback();
            } 
            catch (SQLException e1) 
            {
                throw new ServiceException("Rollback non eseguito - errore",e1);
            } 
            throw new ServiceException(e);
            
        }
        catch(Exception e)
        {
            throw new ServiceException("Errore generico in commit",e);
        }
        finally
        {
            try 
            {
                connection.close();
            } catch (SQLException e) 
            {
                
                throw new ServiceException("Errore durante la chiusura della connessione",e);
            }
        }
    }
}
