package com.tirocinio.service.Associate;

import com.tirocinio.dao.ClienteDAO;
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

    public boolean execute(int codCliente, int codBiglietto) throws SQLException {
        // Cerco il cliente con il nome e cognome forniti
        Connection connection = ConnectionManager.getConnection();
        try {

            Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(connection, codBiglietto);

            if (cliente != null && biglietto != null) {
                //  altri attributi del biglietto se necessario

                 bigliettoDAO.associateWithClient(connection, biglietto,cliente);
                 connection.commit();
                return true;

            } else {
                // Cliente non trovato
                System.out.println("Cliente o Biglietto non trovato con Codice:" + codCliente +" o " +codBiglietto );
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