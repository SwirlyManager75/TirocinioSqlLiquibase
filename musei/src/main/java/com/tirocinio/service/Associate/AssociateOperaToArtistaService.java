package com.tirocinio.service.Associate;

import com.tirocinio.dao.Interfaces.ArtistaDAO;
import com.tirocinio.dao.Interfaces.OperaDAO;
import com.tirocinio.dao.impl.ArtistaDAOimpl;
import com.tirocinio.dao.impl.OperaDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.model.Opera;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Artista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateOperaToArtistaService implements MuseoGenericService {

    private final OperaDAO operaDAO;
    private final ArtistaDAO artistaDAO;

    public AssociateOperaToArtistaService() {
        this.operaDAO = new OperaDAOimpl();
        this.artistaDAO= new ArtistaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try  {

            int codAr=(Integer)input.get("artista");
            int codOp=(Integer)input.get("opera");

            Opera opera = operaDAO.getOperaById(connection, codOp);
            Artista artista = artistaDAO.getArtistaById(connection, codAr);

            if (artista != null && opera != null) {

                // Inserisci l'Opera nel database
                ret=operaDAO.associateWithArtist(connection, opera,artista);
                output.put("AssociateOperaToArtista", ret);
                 connection.commit();
                return output;

            } else {
                // Museo non trovato, gestisci la situazione di conseguenza
                System.out.println("Artista o Opera non trovato con codice: " + codAr+" o "+codOp);
                output.put("AssociateOperaToArtista", false);

                return output;
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
