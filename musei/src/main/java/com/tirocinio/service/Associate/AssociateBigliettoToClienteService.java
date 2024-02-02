package com.tirocinio.service.Associate;

import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.exceptions.DAOException;
import com.google.protobuf.ServiceException;
import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Biglietto;

import java.sql.Connection;
import java.sql.SQLException;

public class AssociateBigliettoToClienteService {

    private final ClienteDAO clienteDAO;
    private final BigliettoDAO bigliettoDAO;

    public AssociateBigliettoToClienteService(Connection connection) {
        this.clienteDAO = new ClienteDAO();
        this.bigliettoDAO = new BigliettoDAO();
    }

    public boolean execute(int codCliente, int codBiglietto) throws ServiceException {
        // Cerco il cliente con il nome e cognome forniti
        Connection connection = ConnectionManager.getConnection();
        boolean ret;

        try {

            Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

            if (cliente != null && biglietto != null) {
                //  altri attributi del biglietto se necessario

                ret= bigliettoDAO.associateWithClient(connection, biglietto,cliente);
                 connection.commit();
                return ret;

            } else {
                // Cliente non trovato
                System.out.println("Cliente o Biglietto non trovato con Codice:" + codCliente +" o " +codBiglietto );
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