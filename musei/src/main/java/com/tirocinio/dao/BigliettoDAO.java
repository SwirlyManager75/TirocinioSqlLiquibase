package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigliettoDAO {

    private static final String SELECT_ALL_BIGLIETTI = "SELECT * FROM Biglietto";
    private static final String SELECT_BIGLIETTO_BY_ID = "SELECT * FROM Biglietto WHERE Cod_Bi = ?";
    private static final String INSERT_BIGLIETTO = "INSERT INTO Biglietto (Prezzo, Tipo, Data) VALUES (?, ?, ?)";
    private static final String UPDATE_BIGLIETTO = "UPDATE Biglietto SET Prezzo = ?, Tipo = ?, Data = ? WHERE Cod_Bi = ?";
    private static final String DELETE_BIGLIETTO = "DELETE FROM Biglietto WHERE Cod_Bi = ?";
    private static final String ASSOC_CLIENTE = "UPDATE Biglietto SET Cod_E_Cli = ? WHERE Cod_Bi = ?";
    private static final String ASSOC_BIGLIETTERIA = "UPDATE Biglietto SET Cod_E_B = ? WHERE Cod_Bi = ?";

        private static final Logger logger= LogManager.getLogger();


    public List<Biglietto> getAllBiglietti(Connection connection) throws DAOException {
        List<Biglietto> biglietti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BIGLIETTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                biglietti.add(mapResultSetToBiglietto(resultSet));
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante la selezione di tutti i biglietti", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione di tutti i biglietti", e);

            }
        return biglietti;
    }

    public Biglietto getBigliettoById(Connection connection, int bigliettoId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BIGLIETTO_BY_ID)) {

            preparedStatement.setInt(1, bigliettoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBiglietto(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante la selezione del biglietto con id:"+bigliettoId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione del biglietto con id:"+bigliettoId, e);

            }
        return null;
    }

    public boolean addBiglietto(Connection connection, Biglietto biglietto) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BIGLIETTO)) {

            preparedStatement.setFloat(1, biglietto.getPrezzo());
            preparedStatement.setString(2, biglietto.getTipo().name());
            preparedStatement.setDate(3, biglietto.getData());
        

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiunta del biglietto con data: "+biglietto.getData(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiunta del biglietto con data: "+biglietto.getData(), e);

            }
    }

    public boolean updateBiglietto(Connection connection, Biglietto biglietto) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BIGLIETTO)) {

            preparedStatement.setFloat(1, biglietto.getPrezzo());
            preparedStatement.setString(2, biglietto.getTipo().name());
            preparedStatement.setDate(3, biglietto.getData());
            preparedStatement.setInt(4, biglietto.getCodBi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento del biglietto con id: "+biglietto.getCodBi(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiornamento del biglietto con id: "+biglietto.getCodBi(), e);

            }
    }

    public boolean deleteBiglietto(Connection connection, int bigliettoId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BIGLIETTO)) {

            preparedStatement.setInt(1, bigliettoId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione del biglietto con id: "+bigliettoId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la cancellazione del biglietto con id: "+bigliettoId, e);

            }
    }

    public List<Biglietto> search(Connection connection, Biglietto criteria) throws DAOException {
        List<Biglietto> matchingBiglietti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Biglietto WHERE 1=1");

        if (criteria.getPrezzo() != 0) {
            queryBuilder.append(" AND Prezzo = ?");
        }
        if (criteria.getTipo() != null) {
            queryBuilder.append(" AND Tipo = ?");
        }
        if (criteria.getData() != null) {
            queryBuilder.append(" AND Data = ?");
        }
        

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getPrezzo() != 0) {
                preparedStatement.setFloat(parameterIndex++, criteria.getPrezzo());
            }
            if (criteria.getTipo() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getTipo().name());
            }
            if (criteria.getData() != null) {
                preparedStatement.setDate(parameterIndex++, criteria.getData());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingBiglietti.add(mapResultSetToBiglietto(resultSet));
                }
            }
            catch (SQLException e) {
                throw new DAOException("Errore durante la ricerca del biglietto con criteri", e);
                //return false;
                }
                catch(Exception e)
                {
                    throw new DAOException("Errore generico durante la ricerca del biglietto con criteri", e);
    
                }
        } catch (Exception e) {
            throw new DAOException("Errore durante il prareStatement", null);
            //return false;
        }

        return matchingBiglietti;
    }

    public boolean associateWithClient(Connection connection, Biglietto biglietto, Cliente cliente) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_CLIENTE)) {

            statement.setInt(1, cliente.getCodCli());
            statement.setInt(2, biglietto.getCodBi());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione del biglietto con id:"+biglietto.getCodBi()+ " e cliente con id:"+cliente.getCodCli(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'associazione del biglietto con id:"+biglietto.getCodBi()+ " e cliente con id:"+cliente.getCodCli(), e);

            }
    }

    public boolean associateWithTicketOffice(Connection connection,Biglietto biglietto, Biglietteria biglietteria) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_BIGLIETTERIA)) {

            statement.setInt(1, biglietteria.getCodB());
            statement.setInt(2, biglietto.getCodBi());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione del biglietto con id:"+biglietto.getCodBi()+ " e biglietteria con id:"+biglietteria.getCodB(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'associazione del biglietto con id:"+biglietto.getCodBi()+ " e biglietteria con id:"+biglietteria.getCodB(), e);

            }
    }

    private Biglietto mapResultSetToBiglietto(ResultSet resultSet) throws SQLException {
        Biglietto biglietto = new Biglietto();
        biglietto.setCodBi(resultSet.getInt("Cod_Bi"));
        biglietto.setPrezzo(resultSet.getFloat("Prezzo"));
        biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(resultSet.getString("Tipo")));
        biglietto.setData(resultSet.getDate("Data"));
        
        return biglietto;
    }
}