package com.tirocinio.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tirocinio.dao.Interfaces.Abbonamento_BiglietteriaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Biglietteria;

public class Abbonamento_BiglietteriaDAOimpl implements Abbonamento_BiglietteriaDAO {

    Connection connection;

    private static final String INSERT_ABBONAMENTO_BIGLIETTERIA = "INSERT INTO Abbonamento_Biglietteria (Cod_E_A, Cod_E_B) VALUES (?, ?)";
    private static final String DELETE_ABBONAMENTO_BIGLIETTERIA = "DELETE FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";

    private static final Logger logger= LogManager.getLogger(Abbonamento_BiglietteriaDAOimpl.class);

    public boolean addAbbonamentoBiglietteria(Connection connection, int Cod_Ab, int Cod_B) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, Cod_Ab);
            preparedStatement.setInt(2, Cod_B);

            int rowsAffected = preparedStatement.executeUpdate();

            logger.info("SUCCESS: Associazione Abbonamento a Biglietteria con id rispettivi"+Cod_Ab+" , "+Cod_B);
            return rowsAffected > 0;
        } catch (SQLException e) {

            logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

            throw new DAOException("Errore durante l'associazione tra abbonamento e biglietto", e);
            //return false;
        }catch (Exception e) {
            logger.error("Errore generico durante l'associazione tra abbonamento e biglietto "+e.getMessage());

        	throw new DAOException("Errore generico durante l'associazione tra abbonamento e biglietto", e);
        }
    }

    public boolean deleteAbbonamentoBiglietteria(Connection connection, int abbonamentoBiglietteriaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, abbonamentoBiglietteriaId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Errore durante la cancellazione dell'abbonamento dalla biglietteria "+abbonamentoBiglietteriaId+" ,"+e.getMessage());

            throw new DAOException("Errore durante la cancellazione dell'abbonamento dalla biglietteria "+abbonamentoBiglietteriaId,e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("Errore generico durante la cancellazione dell'abbonamento dalla biglietteria "+abbonamentoBiglietteriaId+" ,"+e.getMessage());

            throw new DAOException("Errore generico durente la cancellazione di un abbonamento dalla biglietteria "+abbonamentoBiglietteriaId, e);
        }
    }

    public boolean updateAbbonamentoBiglietteria(Connection connection, Integer Cod_E_A,Integer Cod_E_B, Integer Cod_AB ) throws DAOException {
        String query = "UPDATE Abbonamento_Biglietteria " +
                       "SET Cod_E_A = ?, Cod_E_B = ? " +
                       "WHERE Cod_AB = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Cod_E_A);
            preparedStatement.setInt(2, Cod_E_B);
            preparedStatement.setInt(3, Cod_AB);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiornamento abbonamento e biglietteria con id riga "+Cod_AB+" risultato:"+rowsAffected);
            // Restituisci true se almeno una riga è stata aggiornata
            return rowsAffected > 0;
        } catch (SQLException e) {
            
            throw new DAOException("Errore durante l'aggiornamento dell'abbonamento e della biglietteria con codice AB"+Cod_AB, e);
            //return false;
        }
        catch(Exception e)
        {
            throw new DAOException("Errore generico durante l'aggiornamento dell'abbonamento e della biglietteria con codice AB"+Cod_AB, e);
        }
    }

    public Map<Object, Object> leggiBiglietteriePerAbbonamento(Connection connection,int codiceAbbonamento) throws SQLException, DAOException {
        Map<Object, Object> result = new HashMap<>();
        String query = "SELECT ab.Cod_Abbonamento, ab.Cod_Biglietteria FROM Abbonamento_Biglietteria ab " +
                       "WHERE ab.Cod_E_A = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceAbbonamento);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    
                    int codAb = resultSet.getInt("Cod_Abbonamento");
                    int codBi = resultSet.getInt("Cod_Biglietteria");

                    Biglietteria bigl= new Biglietteria();
                    Abbonamento abb= new Abbonamento();

                    bigl.setCodB(codBi);
                    abb.setCodAb(codAb);

                    result.put(abb, bigl);
                }
            }
            catch (SQLException e) {
                logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

                throw new DAOException("Errore durante la lettura delle biglietterie che hanno l'abbonamento "+codiceAbbonamento, e);
                //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico durante l'associazione tra abbonamento e biglietto "+e.getMessage());

                throw new DAOException("Errore generico durante la lettura delle biglietterie che hanno l'abbonamento "+codiceAbbonamento, e);
            }
        }
        catch (SQLException e) {
            logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

            throw new DAOException("Errore durante il prepareStatement per la query: "+query, e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("Errore generico durante l'associazione tra abbonamento e biglietto "+e.getMessage());

            throw new DAOException("Errore generico durante il prepareStatement per la query: "+query, e);
        }
        logger.info("SUCCESS: Lette biglietterie con abbonamento "+codiceAbbonamento+" rowsaffected:"+result.size());

        return result;
    }

    public Map<Object, Object> leggiAbbonamentiPerBiglietteria(Connection connection,int codiceBiglietteria) throws SQLException, DAOException {
        Map<Object, Object> result = new HashMap<>();
        String query = "SELECT ab.Cod_Abbonamento, ab.Cod_Biglietteria FROM Abbonamento_Biglietteria ab " +
                       "WHERE ab.Cod_E_B = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceBiglietteria);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codAb = resultSet.getInt("Cod_Abbonamento");
                    int codBi = resultSet.getInt("Cod_Biglietteria");

                    Biglietteria bigl= new Biglietteria();
                    Abbonamento abb= new Abbonamento();

                    bigl.setCodB(codBi);
                    abb.setCodAb(codAb);

                    result.put(bigl, abb);
                }
            }
            catch (SQLException e) {
                logger.error("SQLError durante la lettura abbonamento e biglietto "+e.getMessage());

                throw new DAOException("Errore durante la lettura degli abbonamenti della biglietteria "+codiceBiglietteria, e);
                //return false;
            }
            catch(Exception e)
            {
                logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

                throw new DAOException("Errore generico durante la lettura degli abbonamenti della biglietteria "+codiceBiglietteria, e);

            }
        }
        catch (SQLException e) {
            logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

            throw new DAOException("Errore durante il prepare statement per la query: "+query, e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("SQLError durante l'associazione tra abbonamento e biglietto "+e.getMessage());

            throw new DAOException("Errore generico durante il prepare statement per la query: "+query, e);
        }
        logger.info("SUCCESS: Letti abbonamenti con biglietteria "+codiceBiglietteria+" rowsaffected:"+result.size());

        return result;
    }

    public Map<Object, Object> getAllAbbonementiBiglietterie(Connection connection) throws SQLException, DAOException {
        Map<Object, Object> result = new HashMap<>();
        String query = "SELECT Cod_Abbonamento, Cod_Biglietteria FROM Abbonamento_Biglietteria";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int codiceAbbonamento = resultSet.getInt("Cod_Abbonamento");
                int codiceBiglietteria = resultSet.getInt("Cod_Biglietteria");

                Biglietteria bigl= new Biglietteria();
                Abbonamento abb= new Abbonamento();

                bigl.setCodB(codiceBiglietteria);
                abb.setCodAb(codiceAbbonamento);

                result.put(abb, bigl);
            }
        }
        catch (SQLException e) {
            logger.error("SQLError durante la lettura di tutti le associazioni abbonamenti e biglietterie "+e.getMessage());

            throw new DAOException("Errore durante la lettura degli abbonamenti e delle biglietterie", e);
            //return false;
        }
        catch(Exception e)
        {
            logger.error("Errore generico durante la lettura di tutti le associazioni abbonamenti e biglietterie "+e.getMessage());


            throw new DAOException("Errore generico durante la lettura degli abbonamenti e delle biglietterie", e);

        }
        logger.info("SUCCESS:lettura di tutti gli elementi: "+result.size());
        
        return result;
    }


    
}