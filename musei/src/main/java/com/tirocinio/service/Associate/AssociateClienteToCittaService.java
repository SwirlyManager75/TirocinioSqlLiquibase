package com.tirocinio.service.Associate;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateClienteToCittaService {

    private final ClienteDAO clienteDAO;
    private final CittaDAO cittaDAO;

    public AssociateClienteToCittaService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.cittaDAO = new CittaDAO();
    }

    public boolean execute(int codCliente, int codCitta) throws ServiceException {
        // Cerco la Città con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
            if (citta != null && cliente != null)
            {
                // Inserisci il Cliente nel database
                ret= clienteDAO.associateWithCity(connection, cliente,citta);
                 connection.commit();
                return ret;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
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
