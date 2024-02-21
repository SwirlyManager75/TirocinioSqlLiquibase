package com.tirocinio.dao;

import java.sql.*;

import java.util.HashMap;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tirocinio.exceptions.DAOException;

public class Cliente_AbbonamentoDAO {
    
    private static final Logger logger= LogManager.getLogger(Cliente_AbbonamentoDAO.class);

    public Cliente_AbbonamentoDAO() {

    }

    public boolean addClienteAbbonamento(Connection connection,int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException {
        String query = "INSERT INTO Cliente_Abbonamenti (Cod_Cli, Cod_Ab) VALUES (?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            statement.setInt(2, codiceAbbonamento);
            int rowsAffeccted=statement.executeUpdate();
            logger.info("SUCCESS: associato cliente ad un abbonamento con id rispettivi"+codiceCliente+", "+codiceAbbonamento+"rows:"+rowsAffeccted);

            return  rowsAffeccted > 0;
        }
        catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'associazione tra cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante l'associazione tra cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);

            }
    }

    public boolean deleteClienteAbbonamento(Connection connection,int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException {
        String query = "DELETE FROM Cliente_Abbonamenti WHERE Cod_Cli = ? AND Cod_Ab = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            statement.setInt(2, codiceAbbonamento);
            int rowsAffeccted=statement.executeUpdate();
            logger.info("SUCCESS: cancellazione dell'associazione tra cliente e abbonamento con id rispettivi "+codiceCliente+" , "+codiceAbbonamento+" rows:"+rowsAffeccted);

            return rowsAffeccted > 0;
        }
        catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore  durante la cancellazione dell'associazione del cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante la cancellazione dell'associazione cliente con id:"+codiceCliente+" e abbonamento con id:"+codiceAbbonamento, e);

            }
    }

    public Map<Integer, Integer> leggiAbbonamentiPerCliente(Connection connection,int codiceCliente) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT Cod_Ab FROM Cliente_Abbonamenti WHERE Cod_Cli = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codiceAbbonamento = resultSet.getInt("Cod_Ab");
                    result.put(codiceCliente, codiceAbbonamento);
                }
            }
        }
        catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore  durante la lettura degli abbonamenti del cliente con id:"+codiceCliente, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore  "+e.getMessage());
                throw new DAOException("Errore generico durante la lettura degli abbonamenti del cliente con id:"+codiceCliente, e);

            }

            logger.info("SUCCESS: selezione abbonamenti del cliente con id "+codiceCliente+" rows:"+result.size());

        return result;
    }
    
    public Map<Integer, Integer> leggiClientiPerAbbonamento(Connection connection,int codiceAbbonamento) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT Cod_Cli FROM Cliente_Abbonamenti WHERE Cod_Ab = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceAbbonamento);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codiceCliente = resultSet.getInt("Cod_Cli");
                    result.put(codiceCliente, codiceAbbonamento);
                }
            }
        }
        catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore  durante la lettura dei clienti con abbonamento di id:"+codiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore  "+e.getMessage());
                throw new DAOException("Errore generico durante la lettura dei clienti con abbonamento di id:"+codiceAbbonamento, e);

            }

            logger.info("SUCCESS: selezione abbonamenti dei clienti con abbonamento di id: "+codiceAbbonamento+" rows:"+result.size());

        return result;
    }

    public boolean updateClienteAbbonamento(Connection connection,int codiceCliente, int vecchioCodiceAbbonamento, int nuovoCodiceAbbonamento) throws SQLException, DAOException {
        String query = "UPDATE Cliente_Abbonamenti SET Cod_Ab = ? WHERE Cod_Cli = ? AND Cod_Abbonamento = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuovoCodiceAbbonamento);
            statement.setInt(2, codiceCliente);
            statement.setInt(3, vecchioCodiceAbbonamento);
            int rowsAffeccted=statement.executeUpdate();
            logger.info("SUCCESS: aggiornamento dell'abbonamento per Cliente id rispettivi"+nuovoCodiceAbbonamento+", "+codiceCliente);

            return rowsAffeccted > 0;
        }
        catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiornamento dell'abbonamento del cliente con id:"+codiceCliente+" che ha abbonamento id"+vecchioCodiceAbbonamento, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante l'aggiornamento dell'abbonamento del cliente con id:"+codiceCliente+" che ha abbonamento id"+vecchioCodiceAbbonamento, e);

            }
    }
    

}
