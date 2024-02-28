package com.tirocinio.dao.Interfaces;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.util.List;



public interface ClienteDAO {

    String SELECT_ALL_CLIENTI = "SELECT * FROM Cliente";
    String SELECT_CLIENTE_BY_ID = "SELECT * FROM Cliente WHERE Cod_Cli = ?";
    String INSERT_CLIENTE = "INSERT INTO Cliente (Nome, Cognome, Mail, Cellulare) VALUES (?, ?, ?, ?)";
    String UPDATE_CLIENTE = "UPDATE Cliente SET Nome = ?, Cognome = ?, Mail = ?, Cellulare = ? WHERE Cod_Cli = ?";
    String DELETE_CLIENTE = "DELETE FROM Cliente WHERE Cod_Cli = ?";
    String ASSOC_CLIENTE = "UPDATE Cliente SET Cod_E_Ci = ? WHERE Cod_Cli = ?";
    String LASTKEY_CLIENTE = "SELECT * FROM Abbonamento WHERE Cod_Cli = (SELECT MAX(Cod_Cli) FROM Abbonamento)";

    public int getLastKey(Connection connection) throws DAOException;

    public List<Cliente> getAllClienti(Connection connection) throws DAOException;

    public Cliente getClienteById(Connection connection, int clienteId) throws DAOException ;

    public Cliente addCliente(Connection connection, Cliente cliente) throws DAOException ;

    public Cliente updateCliente(Connection connection, Cliente cliente) throws DAOException ;

    public boolean deleteCliente(Connection connection, int clienteId) throws DAOException ;

    public List<Cliente> search(Connection connection, Cliente criteria) throws DAOException ;

    public boolean associateWithCity(Connection connection, Cliente cliente, Citta citta) throws DAOException ;
}