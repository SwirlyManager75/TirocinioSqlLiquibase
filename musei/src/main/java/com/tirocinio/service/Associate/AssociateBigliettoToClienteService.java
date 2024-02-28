package com.tirocinio.service.Associate;

import com.tirocinio.dao.impl.ClienteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.BigliettoDAOimpl;
import com.tirocinio.model.Cliente;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateBigliettoToClienteService implements MuseoGenericService {

    private final ClienteDAOimpl clienteDAO;
    private final BigliettoDAOimpl bigliettoDAO;

    public AssociateBigliettoToClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAOimpl();
        this.bigliettoDAO = new BigliettoDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        // Cerco il cliente con il nome e cognome forniti
        Connection connection = ConnectionManager.getConnection();
        boolean ret;
        Map<Object, Object> output = new HashMap<>();


        try {

            int codCliente=(Integer)input.get("cliente");
            int codBiglietto=(Integer)input.get("biglietto");

            Cliente cliente = clienteDAO.getClienteById(connection, (Integer)input.get("cliente"));
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, (Integer)input.get("biglietto"));

            if (cliente != null && biglietto != null) {
                //  altri attributi del biglietto se necessario

                ret= bigliettoDAO.associateWithClient(connection, biglietto,cliente);
                output.put("AssociateBigliettoToCliente", ret);
                 connection.commit();
                return output;

            } else {
                // Cliente non trovato
                System.out.println("Cliente o Biglietto non trovato con Codice:" + codCliente +" o " +codBiglietto );
                output.put("AssociateBigliettoToCliente", false);

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