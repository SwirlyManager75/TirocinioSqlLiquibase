package com.tirocinio.service;

import com.tirocinio.dao.ClienteDAO;
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

    public boolean execute(int codCliente, int codCitta) throws SQLException {
        // Cerco la Città con il codice fornito
        Connection connection = ConnectionManager.getConnection();
        try  {
            connection.setAutoCommit(false);

            Citta citta = cittaDAO.getCityById(connection, codCitta);
            Cliente cliente = clienteDAO.getClienteById(connection, codCliente);
            if (citta != null && cliente != null)
            {
                // Inserisci il Cliente nel database
                 clienteDAO.associateWithCity(connection, cliente,citta);
                 connection.commit();
                return true;

            } else {
                // Città non trovata
                System.out.println("Città non trovata con codice: " + codCitta);
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
