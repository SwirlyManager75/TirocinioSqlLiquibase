package com.tirocinio.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import com.tirocinio.exceptions.DAOException;

public class Abbonamento_BiglietteriaDAO {

    private static final String INSERT_ABBONAMENTO_BIGLIETTERIA = "INSERT INTO Abbonamento_Biglietteria (Cod_E_A, Cod_E_B) VALUES (?, ?)";
    private static final String DELETE_ABBONAMENTO_BIGLIETTERIA = "DELETE FROM Abbonamento_Biglietteria WHERE Cod_AB = ?";

    public boolean addAbbonamentoBiglietteria(Connection connection, int Cod_Ab, int Cod_B) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, Cod_Ab);
            preparedStatement.setInt(2, Cod_B);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione tra abbonamento e biglietto", e);
            //return false;
        }catch (Exception e) {
        	throw new DAOException("Errore generico durante l'associazione tra abbonamento e biglietto", e);
        }
    }

    public boolean deleteAbbonamentoBiglietteria(Connection connection, int abbonamentoBiglietteriaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ABBONAMENTO_BIGLIETTERIA)) {

            preparedStatement.setInt(1, abbonamentoBiglietteriaId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione dell'abbonamento dalla biglietteria "+abbonamentoBiglietteriaId,e);
            //return false;
        }
        catch(Exception e)
        {
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

    public Map<Integer, Integer> leggiBiglietteriePerAbbonamento(Connection connection,int codiceAbbonamento) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT ab.Cod_Abbonamento, ab.Cod_Biglietteria FROM Abbonamento_Biglietteria ab " +
                       "WHERE ab.Cod_E_A = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceAbbonamento);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codAb = resultSet.getInt("Cod_Abbonamento");
                    int codBi = resultSet.getInt("Cod_Biglietteria");
                    result.put(codAb, codBi);
                }
            }
            catch (SQLException e) {
                throw new DAOException("Errore durante la lettura delle biglietterie che hanno l'abbonamento "+codiceAbbonamento, e);
                //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la lettura delle biglietterie che hanno l'abbonamento "+codiceAbbonamento, e);
            }
        }
        catch (SQLException e) {
            throw new DAOException("Errore durante il prepareStatement per la query: "+query, e);
            //return false;
        }
        catch(Exception e)
        {
            throw new DAOException("Errore generico durante il prepareStatement per la query: "+query, e);
        }
        
        return result;
    }

    public Map<Integer, Integer> leggiAbbonamentiPerBiglietteria(Connection connection,int codiceBiglietteria) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT ab.Cod_Abbonamento, ab.Cod_Biglietteria FROM Abbonamento_Biglietteria ab " +
                       "WHERE ab.Cod_E_B = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codiceBiglietteria);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int codAb = resultSet.getInt("Cod_Abbonamento");
                    int codBi = resultSet.getInt("Cod_Biglietteria");
                    result.put(codAb, codBi);
                }
            }
            catch (SQLException e) {
                throw new DAOException("Errore durante la lettura degli abbonamenti della biglietteria "+codiceBiglietteria, e);
                //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la lettura degli abbonamenti della biglietteria "+codiceBiglietteria, e);

            }
        }
        catch (SQLException e) {
            throw new DAOException("Errore durante il prepare statement per la query: "+query, e);
            //return false;
        }
        catch(Exception e)
        {
            throw new DAOException("Errore generico durante il prepare statement per la query: "+query, e);
        }
        return result;
    }

    public Map<Integer, Integer> getAllAbbonementiBiglietterie(Connection connection) throws SQLException, DAOException {
        Map<Integer, Integer> result = new HashMap<>();
        String query = "SELECT Cod_Abbonamento, Cod_Biglietteria FROM Abbonamento_Biglietteria";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int codiceAbbonamento = resultSet.getInt("Cod_Abbonamento");
                int codiceBiglietteria = resultSet.getInt("Cod_Biglietteria");
                result.put(codiceAbbonamento, codiceBiglietteria);
            }
        }
        catch (SQLException e) {
            throw new DAOException("Errore durante la lettura degli abbonamenti e delle biglietterie", e);
            //return false;
        }
        catch(Exception e)
        {
            throw new DAOException("Errore generico durante la lettura degli abbonamenti e delle biglietterie", e);

        }
        return result;
    }


    
}