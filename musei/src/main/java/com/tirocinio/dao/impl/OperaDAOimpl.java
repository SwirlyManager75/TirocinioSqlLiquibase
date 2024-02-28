package com.tirocinio.dao.impl;
import com.tirocinio.dao.Interfaces.OperaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Opera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperaDAOimpl implements OperaDAO{

    private static final String SELECT_ALL_OPERE = "SELECT * FROM Opera";
    private static final String SELECT_OPERA_BY_ID = "SELECT * FROM Opera WHERE Cod_O = ?";
    private static final String INSERT_OPERA = "INSERT INTO Opera (Nome, Descrizione) VALUES (?, ?)";
    private static final String UPDATE_OPERA = "UPDATE Opera SET Nome = ?, Descrizione = ? WHERE Cod_O = ?";
    private static final String DELETE_OPERA = "DELETE FROM Opera WHERE Cod_O = ?";
    private static final String ASSOC_ARTISTA= "UPDATE Opera SET Cod_E_Ar = ? WHERE Cod_O = ?";
    private static final String ASSOC_MUSEO = "UPDATE Opera SET Cod_E_Ci = ? WHERE Cod_O = ?";
    private static final String LASTKEY_OPERA = "SELECT * FROM OPERA WHERE Cod_O = (SELECT MAX(Cod_O) FROM OPERA)";


    private static final Logger logger= LogManager.getLogger(OperaDAOimpl.class);

    public int getLastKey(Connection connection) throws DAOException{
        Opera opera=null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(LASTKEY_OPERA)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           opera =  mapResultSetToOpera(resultSet);
           logger.info("Ultimo id recuperato");

           return opera.getCodO();
        } catch (SQLException e) {
            logger.error("SQLError "+e.getMessage());

            throw new DAOException("Errore durante il recupero dell'ultimo id Abbonamento", e);
        }
        catch(Exception e)
        {
            logger.error("Errore generico "+e.getMessage());

            throw new DAOException("Errore generico durante il recupero dell'ultimo id Abbonamento", e);

        }
    }

    public List<Opera> getAllOpere(Connection connection) throws DAOException {
        List<Opera> opere = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_OPERE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                opere.add(mapResultSetToOpera(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la selezione di tutte le opere ", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante la selezione di tutte le opere ", e);

            }
            logger.info("SUCCESS: selezione di tutte le opere ,rows:"+opere.size());

        return opere;
    }

    public Opera getOperaById(Connection connection, int operaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OPERA_BY_ID)) {

            preparedStatement.setInt(1, operaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS: selezione opera");

                    return mapResultSetToOpera(resultSet);
                }
            }
        }catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la selezione dell'opera con id: "+operaId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante la selezione dell'opera con id: "+operaId, e);

            }
        return null;
    }

    public Opera addOpera(Connection connection, Opera opera) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_OPERA)) {

            preparedStatement.setString(1, opera.getNome());
            preparedStatement.setString(2, opera.getDescrizione());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiunta dell'opera , rows:"+rowsAffected);
            opera.setCodO( getLastKey(connection));
            return opera;
            //return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiunta dell'opera: "+opera.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante l'aggiunta dell'opera: "+opera.getNome(), e);

            }
    }

    public Opera updateOpera(Connection connection, Opera opera) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_OPERA)) {

            preparedStatement.setString(1, opera.getNome());
            preparedStatement.setString(2, opera.getDescrizione());
            preparedStatement.setInt(3, opera.getCodO());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiornamento dell'opera , rows:"+rowsAffected);
            return opera;
            //return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiornamento dell'opera con id: "+opera.getCodO(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante l'aggiornamento dell'opera con id: "+opera.getCodO(), e);
            }
    }

    public boolean deleteOpera(Connection connection, int operaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_OPERA)) {

            preparedStatement.setInt(1, operaId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: cancellazione dell'opera , rows:"+rowsAffected);

            return rowsAffected > 0;
        } catch (SQLException e) 
        {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la cancellazione dell'opera con id: "+operaId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante la cancellazione dell'opera con id: "+operaId, e);

            }
    }

    public List<Opera> search(Connection connection, Opera criteria) throws DAOException {
        List<Opera> matchingOpere = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Opera WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getDescrizione() != null) {
            queryBuilder.append(" AND Descrizione = ?");
        }
        

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getDescrizione() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getDescrizione());
            }
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingOpere.add(mapResultSetToOpera(resultSet));
                }
            }
            catch (SQLException e) {
                logger.error("SqlError "+e.getMessage());
                throw new DAOException("Errore durante la ricerca dell'opera con criterio ", e);
                //return false;
                }
                catch(Exception e)
                {
                    logger.error("Errore generico "+e.getMessage());
                    throw new DAOException("Errore generico durante la ricerca dell'opera con criterio ", e);
    
                }
        } catch (Exception e) {
            logger.error("Errore genrico "+e.getMessage());
            throw new DAOException("Errore generico durante la prepare statement della ricerca con criterio delle opere", e);
            //return false;
        }
        logger.info("SUCCESS: ricerca con criterio delle opere , rows:"+matchingOpere.size());

        return matchingOpere;
    }

    public boolean associateWithMuseo(Connection connection, Opera opera, Museo museo) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_MUSEO)) {

            statement.setInt(1, museo.getCodM());
            statement.setInt(2, opera.getCodO());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS: associazione di opera e museo con id rispettivi "+opera.getCodO()+","+museo.getCodM());

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'associazione tra un opera e un museo con id rispettivi: "+opera.getCodO()+","+museo.getCodM(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante l'associazione tra un opera e un museo con id rispettivi: "+opera.getCodO()+","+museo.getCodM(), e);

            }
    }

    public boolean associateWithArtist(Connection connection, Opera opera, Artista artista) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_ARTISTA)) {

            statement.setInt(1, artista.getCodAr());
            statement.setInt(2, opera.getCodO());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS: associazione di opera e museo con id rispettivi "+opera.getCodO()+","+artista.getCodAr());

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'associazione tra un opera e un artista con id rispettivi: "+opera.getCodO()+","+artista.getCodAr(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante l'associazione tra un opera e un artista con id rispettivi: "+opera.getCodO()+","+artista.getCodAr(), e);

            }
    }

    private Opera mapResultSetToOpera(ResultSet resultSet) throws SQLException {
        Opera opera = new Opera();
        opera.setCodO(resultSet.getInt("Cod_O"));
        opera.setNome(resultSet.getString("Nome"));
        opera.setDescrizione(resultSet.getString("Descrizione"));
        
        return opera;
    }
}