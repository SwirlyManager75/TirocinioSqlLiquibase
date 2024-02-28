package com.tirocinio.dao.impl;

import com.tirocinio.dao.Interfaces.CittaDAO;
import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CittaDAOimpl implements CittaDAO {

    private static final Logger logger= LogManager.getLogger(CittaDAOimpl.class);

    @Override
    public int getLastKey(Connection connection) throws DAOException{
        Citta citta=null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(LASTKEY_CITTA)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           citta =  mapResultSetToCity(resultSet);
           logger.info("Ultimo id recuperato");

           return citta.getCodCi();
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

    @Override
    public List<Citta> getAllCities(Connection connection) throws DAOException {
        List<Citta> cities = new ArrayList<>();
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                cities.add(mapResultSetToCity(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante  la selezione di tutte le città", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante  la selezione di tutte le città", e);

            }
            logger.info("SUCCESS: selezione di tutte le città eseguita, rows"+cities.size());

        return cities;
    }

    @Override
    public Citta getCityById(Connection connection,int cityId) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)) {

            preparedStatement.setInt(1, cityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS: selezione della citta con id:"+cityId);

                    return mapResultSetToCity(resultSet);
                }
            }
        }catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante  la selezione della città con id:"+cityId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante  la selezione della città con id:"+cityId, e);

            }
        return null;
    }

    @Override
    public Citta addCity(Connection connection,Citta city) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY)) {

            preparedStatement.setString(1, city.getNome());
            preparedStatement.setBoolean(2, city.isProvincia());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiunta città, rows:"+rowsAffected);

            city.setCodCi(getLastKey(connection));
            return city;
        } catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante  l'aggiunta della città: "+city.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico  durante  l'aggiunta della città: "+city.getNome(), e);

            }
    }

    @Override
    public Citta updateCity(Connection connection,Citta city) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY)) {

            preparedStatement.setString(1, city.getNome());
            preparedStatement.setBoolean(2, city.isProvincia());
            preparedStatement.setInt(3, city.getCodCi());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: aggiorna citta , rows:"+rowsAffected);

            return city;
        }catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante l'aggiornamento della citta con id: "+city.getCodCi(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico  durante  l'aggiornamento della città con id: "+city.getCodCi(), e);

            }
    }

    @Override
    public boolean deleteCity(Connection connection,int cityId) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY)) {

            preparedStatement.setInt(1, cityId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: cancellazione citta , rows "+rowsAffected);

            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("SqlError "+e.getMessage());
            throw new DAOException("Errore durante la cancellazione della citta con id: "+cityId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico "+e.getMessage());
                throw new DAOException("Errore generico durante  la cancellazione della città con id: "+cityId, e);

            }
    }

    @Override
    public List<Citta> search(Connection connection, Citta criteria) throws DAOException {
        List<Citta> matchingCities = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Citta WHERE 1=1");
    
        // Aggiungi i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome LIKE ?");
        }
        if (criteria.isProvincia()) {
            queryBuilder.append(" AND Provincia = ?");
        }
        // Aggiungi altri criteri secondo necessità
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;
    
            // Imposta i parametri dinamici nella query
            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getNome() + "%");
            }
            if (criteria.isProvincia()) {
                preparedStatement.setBoolean(parameterIndex++, criteria.isProvincia());
            }
            // Imposta altri parametri secondo necessità
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingCities.add(mapResultSetToCity(resultSet));
                }
            }
            catch (SQLException e) {
                logger.error("SqlError "+e.getMessage());
                throw new DAOException("Errore durante la ricerca della citta con con criteri", e);
                //return false;
                }
                catch(Exception e)
                {
                    logger.error("Errore generico "+e.getMessage());
                    throw new DAOException("Errore generico durante la ricerca della città con criteri : ", e);
    
                }
        } catch (Exception e) {
            logger.error("SqlError "+e.getMessage());
                throw new DAOException("Errore durante la prepareStatement della ricerca", e);
                //return false;
            }
    
            logger.info("SUCCESS: ricerca citta con criterio, rows:"+matchingCities.size());

        return matchingCities;
    }

    private Citta mapResultSetToCity(ResultSet resultSet) throws SQLException {
        Citta city = new Citta();
        city.setCodCi(resultSet.getInt("Cod_Ci"));
        city.setNome(resultSet.getString("Nome"));
        city.setProvincia(resultSet.getBoolean("Provincia"));
        return city;
    }
}