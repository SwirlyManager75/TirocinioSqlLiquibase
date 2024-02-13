package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BiglietteriaDAO {

    private static final String SELECT_ALL_BIGLIETTERIE = "SELECT * FROM Biglietteria";
    private static final String SELECT_BIGLIETTERIA_BY_ID = "SELECT * FROM Biglietteria WHERE Cod_B = ?";
    private static final String INSERT_BIGLIETTERIA = "INSERT INTO Biglietteria (Ora_Ap, Ora_Ch, Mod_Pag) VALUES (?, ?, ?)";
    private static final String UPDATE_BIGLIETTERIA = "UPDATE Biglietteria SET Ora_Ap = ?, Ora_Ch = ?, Mod_Pag = ? WHERE Cod_B = ?";
    private static final String DELETE_BIGLIETTERIA = "DELETE FROM Biglietteria WHERE Cod_B = ?";
    private static final String ASSOC_MUSEO= "UPDATE Biglietteria SET Cod_E_M = ? WHERE Cod_B = ?" ;
    //TODO AGGIUNGERE LOGICA PER LEGARE BIGLIETTERIE AD ABBONAMENTI (SI USA LA TABELLA ABBONAMENTI_BIGLIETTERIE)
    

    public List<Biglietteria> getAllBiglietterie(Connection connection) throws DAOException {
        List<Biglietteria> biglietterie = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BIGLIETTERIE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                biglietterie.add(mapResultSetToBiglietteria(resultSet));
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante la selezione di tutte le biglietterie", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione di tutte le biglietterie ", e);

            }
        return biglietterie;
    }

    public Biglietteria getBiglietteriaById(Connection connection, int biglietteriaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BIGLIETTERIA_BY_ID)) {

            preparedStatement.setInt(1, biglietteriaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBiglietteria(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante la selezione della biglietteria con id:"+biglietteriaId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione della biglietteria con id:"+biglietteriaId, e);

            }
        return null;
    }

    public boolean addBiglietteria(Connection connection, Biglietteria biglietteria) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BIGLIETTERIA)) {

            preparedStatement.setTime(1, biglietteria.getOraApertura());
            preparedStatement.setTime(2, biglietteria.getOraChiusura());
            preparedStatement.setString(3, convertModPagToDatabase(biglietteria.getModPag()));
            

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiunta della biglietteria", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiunta della biglietteria", e);

            }
    }

    public boolean updateBiglietteria(Connection connection, Biglietteria biglietteria) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BIGLIETTERIA)) {

            preparedStatement.setTime(1, biglietteria.getOraApertura());
            preparedStatement.setTime(2, biglietteria.getOraChiusura());
            preparedStatement.setString(3, convertModPagToDatabase(biglietteria.getModPag()));
           
            preparedStatement.setInt(4, biglietteria.getCodB());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento della biglietteria con id: "+biglietteria.getCodB(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiornamento della biglietteria con id: "+biglietteria.getCodB(), e);

            }
    }

    public boolean deleteBiglietteria(Connection connection, int biglietteriaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BIGLIETTERIA)) {

            preparedStatement.setInt(1, biglietteriaId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione della biglietteria con id: "+biglietteriaId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la cancellazione della biglietteria con id: "+biglietteriaId, e);

            }
    }

    public List<Biglietteria> search(Connection connection, Biglietteria criteria) throws DAOException {
        List<Biglietteria> matchingBiglietterie = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Biglietteria WHERE 1=1");

        if (criteria.getOraApertura() != null) {
            queryBuilder.append(" AND Ora_Ap = ?");
        }
        if (criteria.getOraChiusura() != null) {
            queryBuilder.append(" AND Ora_Ch = ?");
        }
        if (criteria.getModPag() != null) {
            queryBuilder.append(" AND Mod_Pag = ?");
        }
        

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getOraApertura() != null) {
                preparedStatement.setTime(parameterIndex++, criteria.getOraApertura());
            }
            if (criteria.getOraChiusura() != null) {
                preparedStatement.setTime(parameterIndex++, criteria.getOraChiusura());
            }
            if (criteria.getModPag() != null) {
                preparedStatement.setString(parameterIndex++, convertModPagToDatabase(criteria.getModPag()));
            }
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingBiglietterie.add(mapResultSetToBiglietteria(resultSet));
                }
            }
            catch (SQLException e) {
                throw new DAOException("Errore durante la ricerca della biglietteria con criteri: ", e);
                //return false;
                }
                catch(Exception e)
                {
                    throw new DAOException("Errore generico durante la ricerca della biglietteria con criteri: ", e);
    
                }
        }catch(Exception e)
            {
                throw new DAOException("Errore generico durante il prepare statement della ricerca con criterio delle biglietterie", e);

            }

        return matchingBiglietterie;
    }

    public boolean associateWithMuseum(Connection connection, Biglietteria biglietteria, Museo museo) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_MUSEO)) {

            statement.setInt(1, museo.getCodM());
            statement.setInt(2, biglietteria.getCodB());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione della biglietteria con id: "+biglietteria.getCodB()+" e il museo con id:"+museo.getCodM(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'associazione della biglietteria con id: "+biglietteria.getCodB()+" e il museo con id:"+museo.getCodM(), e);

            }
    }

    private Biglietteria mapResultSetToBiglietteria(ResultSet resultSet) throws SQLException {
        Biglietteria biglietteria = new Biglietteria();
        biglietteria.setCodB(resultSet.getInt("Cod_B"));
        biglietteria.setOraApertura(resultSet.getTime("Ora_Ap"));
        biglietteria.setOraChiusura(resultSet.getTime("Ora_Ch"));
        biglietteria.setModPag(convertDatabaseToModPag(resultSet.getString("Mod_Pag")));
        
        return biglietteria;
    }

    private String convertModPagToDatabase(Biglietteria.ModalitaPagamento modPag) {
        return modPag.name();
    }

    private Biglietteria.ModalitaPagamento convertDatabaseToModPag(String modPagString) {
        return Biglietteria.ModalitaPagamento.valueOf(modPagString);
    }
}