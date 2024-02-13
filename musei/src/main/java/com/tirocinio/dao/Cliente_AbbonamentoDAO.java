package com.tirocinio.dao;

import java.sql.*;

import java.util.HashMap;

import java.util.Map;

import com.tirocinio.exceptions.DAOException;

public class Cliente_AbbonamentoDAO {
    private Connection connection;

    // Costruttore che accetta una connessione al database
    public Cliente_AbbonamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addClienteAbbonamento(int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException {
        String query = "INSERT INTO Cliente_Abbonamenti (Cod_Cliente, Cod_Abbonamento) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            statement.setInt(2, codiceAbbonamento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione tra cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'associazione tra cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);

            }
    }

    public void deleteClienteAbbonamento(int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException {
        String query = "DELETE FROM Cliente_Abbonamenti WHERE Cod_Cliente = ? AND Cod_Abbonamento = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            statement.setInt(2, codiceAbbonamento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Errore  durante la cancellazione dell'associazione del cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la cancellazione dell'associazione cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);

            }
    }

    public Map<Integer, Integer> leggiAbbonamentiPerCliente(int codiceCliente) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT Cod_Abbonamento FROM Cliente_Abbonamenti WHERE Cod_Cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codiceAbbonamento = resultSet.getInt("Cod_Abbonamento");
                    result.put(codiceCliente, codiceAbbonamento);
                }
            }
        }
        catch (SQLException e) {
            throw new DAOException("Errore  durante la lettura degli abbonamenti del cliente con id:"+codiceCliente, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la lettura degli abbonamenti del cliente con id:"+codiceCliente, e);

            }
        return result;
    }
    
    public void updateClienteAbbonamento(int codiceCliente, int vecchioCodiceAbbonamento, int nuovoCodiceAbbonamento) throws SQLException, DAOException {
        String query = "UPDATE Cliente_Abbonamenti SET Cod_Abbonamento = ? WHERE Cod_Cliente = ? AND Cod_Abbonamento = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuovoCodiceAbbonamento);
            statement.setInt(2, codiceCliente);
            statement.setInt(3, vecchioCodiceAbbonamento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento dell'abbonamento del cliente con id:"+codiceCliente+" che ha abbonamento id"+vecchioCodiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiornamento dell'abbonamento del cliente con id:"+codiceCliente+" che ha abbonamento id"+vecchioCodiceAbbonamento, e);

            }
    }
    

}
