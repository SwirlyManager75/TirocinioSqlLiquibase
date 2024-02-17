package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MuseoDAO {

    private static final String SELECT_ALL_MUSEUMS = "SELECT * FROM Museo";
    private static final String SELECT_MUSEUM_BY_ID = "SELECT * FROM Museo WHERE Cod_M = ?";
    private static final String INSERT_MUSEUM = "INSERT INTO Museo (Nome, Via) VALUES (?, ?)";
    private static final String UPDATE_MUSEUM = "UPDATE Museo SET Nome = ?, Via = ? WHERE Cod_M = ?";
    private static final String DELETE_MUSEUM = "DELETE FROM Museo WHERE Cod_M = ?";
    private static final String ASSOC_MUSEUM = "UPDATE Museo SET Cod_E_Ci = ? WHERE Cod_M = ?";
    private static final String LASTKEY_MUSEUM = "SELECT * FROM MUSEO WHERE Cod_M = (SELECT MAX(Cod_M) FROM MUSEO)";


    private static final Logger logger= LogManager.getLogger(MuseoDAO.class);

    public int getLastKey(Connection connection) throws DAOException{
        Museo museo=null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(LASTKEY_MUSEUM)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           museo =  mapResultSetToMuseum(resultSet);
           logger.info("Ultimo id recuperato");

           return museo.getCodM();
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

    public List<Museo> getAllMuseums(Connection connection) throws DAOException {
        List<Museo> museums = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUSEUMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                museums.add(mapResultSetToMuseum(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la selezione di tutti i musei", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante la selezione di tutti i musei", e);

            }
            logger.info("SUCCESS: selezione di tutti i musei , rows:"+museums.size());

        return museums;
    }

    public Museo getMuseumById(Connection connection, int museumId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MUSEUM_BY_ID)) {

            preparedStatement.setInt(1, museumId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS: selezione del museo");

                    return mapResultSetToMuseum(resultSet);
                }
            }
        }  catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la selezione del museo con id"+museumId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante la selezione del museo con id"+museumId, e);

            }

        return null;
    }

    public int addMuseum(Connection connection, Museo museum) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSEUM)) {

            preparedStatement.setString(1, museum.getNome());
            preparedStatement.setString(2, museum.getVia());
            //preparedStatement.setInt(3, museum.getCodECi());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiunta del museo , rows:"+rowsAffected);

            return getLastKey(connection);
        }  catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiunta del museo: "+museum.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore genereico durante l'aggiunta del museo: "+museum.getNome(), e);
            }
    }

    public Museo updateMuseum(Connection connection, Museo museum) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MUSEUM)) {

            preparedStatement.setString(1, museum.getNome());
            preparedStatement.setString(2, museum.getVia());
            preparedStatement.setInt(3, museum.getCodM());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiornamento del museo , rows:"+rowsAffected);

            return museum;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiornamento del museo con id: "+museum.getCodM(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante l'aggiornamento del museo con id: "+museum.getCodM(), e);

            }
    }

    public boolean deleteMuseum(Connection connection, int museumId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MUSEUM)) {

            preparedStatement.setInt(1, museumId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: cancellazione del museo , rows:"+rowsAffected);

            return rowsAffected > 0;
        }  catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la cancellazione del museo con id: "+museumId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante la cancellazione del museo con id: "+museumId, e);

            }
    }

    public boolean associateWithCity(Connection connection, Museo museo, Citta citta) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(ASSOC_MUSEUM)) {

            statement.setInt(1, citta.getCodCi());
            statement.setInt(2, museo.getCodM());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS: associazione del museo con citta , rows:"+rowsAffected);

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'associazione del museo con la citta con id rispettivi: "+museo.getCodM()+","+citta.getCodCi(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore durante l'associazione del museo con la citta con id rispettivi: "+museo.getCodM()+","+citta.getCodCi(), e);

            }
    }

    public List<Museo> search(Connection connection, Museo criteria) throws DAOException {
        List<Museo> matchingMuseums = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Museo WHERE 1=1");
    
        // Aggiungo i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome LIKE ?");
        }
        if (criteria.getVia() != null) {
            queryBuilder.append(" AND Via LIKE ?");
        }
        if (criteria.getCitta() != null) {
            queryBuilder.append(" AND Cod_E_Ci = ?");
        }
        // In futuro aggiungi qui altri criteri a secondo della necessità
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;
    

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getNome() + "%");
            }
            if (criteria.getVia() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getVia() + "%");
            }
            if (criteria.getCitta() != null)
            {
                preparedStatement.setInt(parameterIndex++, criteria.getCitta().getCodCi());
            }
            // In futuro imposta altri parametri qui
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingMuseums.add(mapResultSetToMuseum(resultSet));
                }
            }
            catch (SQLException e) {
                logger.error("SqlError "+e.getMessage());
                throw new DAOException("Errore durante la ricerca del museo con criteri: "+criteria.getNome(), e);
                //return false;
                }
                catch(Exception e)
                {
                    logger.error("Errore generico "+e.getMessage());
                    throw new DAOException("Errore generico durante la ricerca del museo con criteri: "+criteria.getNome(), e);
    
                }
        } catch (Exception e) {
            logger.error("Errore generico "+e.getMessage());
            throw new DAOException("Errore generico durante la prepare statement ", e);
            //return false;
        }
        logger.info("SUCCESS: ricerca del museo con criterio , rows:"+matchingMuseums.size());

        return matchingMuseums;
    }

    private Museo mapResultSetToMuseum(ResultSet resultSet) throws SQLException {
        Museo museum = new Museo();
        museum.setCodM(resultSet.getInt("Cod_M"));
        museum.setNome(resultSet.getString("Nome"));
        museum.setVia(resultSet.getString("Via"));

        return museum;
    }
}