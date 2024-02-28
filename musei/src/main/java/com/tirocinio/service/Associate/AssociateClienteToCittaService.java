package com.tirocinio.service.Associate;

import com.tirocinio.dao.impl.ClienteDAOimpl;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.impl.CittaDAOimpl;
import com.tirocinio.model.Cliente;
import com.tirocinio.service.MuseoGenericService;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AssociateClienteToCittaService  implements MuseoGenericService{

    private final ClienteDAOimpl clienteDAO;
    private final CittaDAOimpl cittaDAO;

    public AssociateClienteToCittaService(Connection connection) {
        this.clienteDAO = new ClienteDAOimpl();
        this.cittaDAO = new CittaDAOimpl();
    }

    public Map<Object, Object> execute(Map<Object, Object> input) throws ServiceException {
        // Cerco la Città con il codice fornito
        Map<Object, Object> output = new HashMap<>();
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try  {

            int codCitta=(Integer)input.get("citta");
            int codCliente=(Integer)input.get("cliente");

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
            if (citta != null && cliente != null)
            {
                // Inserisci il Cliente nel database
                ret= clienteDAO.associateWithCity(connection, cliente,citta);
                output.put("AssociateClienteToCitta", ret);
                 connection.commit();
                return output;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
                output.put("AssociateClienteToCitta", false);

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
