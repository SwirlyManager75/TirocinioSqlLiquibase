package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClienteDAO {

    private static final String SELECT_ALL_CLIENTI = "SELECT * FROM Cliente";
    private static final String SELECT_CLIENTE_BY_ID = "SELECT * FROM Cliente WHERE Cod_Cli = ?";
    private static final String INSERT_CLIENTE = "INSERT INTO Cliente (Nome, Cognome, Mail, Cellulare) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CLIENTE = "UPDATE Cliente SET Nome = ?, Cognome = ?, Mail = ?, Cellulare = ? WHERE Cod_Cli = ?";
    private static final String DELETE_CLIENTE = "DELETE FROM Cliente WHERE Cod_Cli = ?";
    private static final String ASSOC_CITTA = "UPDATE Cliente SET Cod_E_Ci = ? WHERE Cod_Cli = ?";

        private static final Logger logger= LogManager.getLogger();



    public List<Cliente> getAllClienti(Connection connection) throws DAOException {
        List<Cliente> clienti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clienti.add(mapResultSetToCliente(resultSet));
            }
        }  catch (SQLException e) {
            throw new DAOException("Errore durante la selezione di tutti i clienti", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione di tutti i clienti", e);

            }
        return clienti;
    }

    public Cliente getClienteById(Connection connection, int clienteId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTE_BY_ID)) {

            preparedStatement.setInt(1, clienteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCliente(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la selezione del cliente con id "+clienteId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione del cliente con id "+clienteId, e);

            }
        return null;
    }

    public boolean addCliente(Connection connection, Cliente cliente) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE)) {
            //TODO PRENDERE LA CHIAVE PRIMA DI INSERIRE CON MAX+1
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getMail());
            preparedStatement.setString(4, cliente.getCellulare());
            

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiunta del cliente del cliente "+cliente.getNome()+" "+cliente.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiunta del cliente del cliente "+cliente.getNome()+" "+cliente.getNome(), e);

            }
    }

    public boolean updateCliente(Connection connection, Cliente cliente) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getMail());
            preparedStatement.setString(4, cliente.getCellulare());
            
            preparedStatement.setInt(5, cliente.getCodCli());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento del cliente con id "+cliente.getCodCli(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiornamento del cliente con id "+cliente.getCodCli(), e);

            }
    }

    public boolean deleteCliente(Connection connection, int clienteId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTE)) {

            preparedStatement.setInt(1, clienteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione del cliente con id "+clienteId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la cancellazione del cliente con id "+clienteId, e);

            }
    }

    public List<Cliente> search(Connection connection, Cliente criteria) throws DAOException {
        List<Cliente> matchingClienti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cliente WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getCognome() != null) {
            queryBuilder.append(" AND Cognome = ?");
        }
        if (criteria.getMail() != null) {
            queryBuilder.append(" AND Mail = ?");
        }
        if (criteria.getCellulare() != null) {
            queryBuilder.append(" AND Cellulare = ?");
        }
        

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getCognome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCognome());
            }
            if (criteria.getMail() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getMail());
            }
            if (criteria.getCellulare() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCellulare());
            }
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingClienti.add(mapResultSetToCliente(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("Errore durante la ricerca con criterio dei clienti ", e);
                //return false;
                }
                catch(Exception e)
                {
                    throw new DAOException("Errore generico durante la ricerca con criterio dei clienti ", e);
    
                }
        } catch (Exception e) {
            throw new DAOException("Errore generico durante la prepare statement della ricarca con criterio dei clienti", e);
            //return false;
        }

        return matchingClienti;
    }

    public boolean associateWithCity(Connection connection, Cliente cliente, Citta citta) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_CITTA)) {

            statement.setInt(1, citta.getCodCi());
            statement.setInt(2, cliente.getCodCli());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione del cliente con una Città", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'associazione del cliente con una Città", e);

            }
    }

    private Cliente mapResultSetToCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCodCli(resultSet.getInt("Cod_Cli"));
        cliente.setNome(resultSet.getString("Nome"));
        cliente.setCognome(resultSet.getString("Cognome"));
        cliente.setMail(resultSet.getString("Mail"));
        cliente.setCellulare(resultSet.getString("Cellulare"));
        
        return cliente;
    }
}