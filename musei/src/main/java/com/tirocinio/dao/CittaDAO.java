package com.tirocinio.dao;

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

public class CittaDAO {

    private static final String SELECT_ALL_CITIES = "SELECT * FROM Citta";
    private static final String SELECT_CITY_BY_ID = "SELECT * FROM Citta WHERE Cod_Ci = ?";
    private static final String INSERT_CITY = "INSERT INTO Citta (Nome, Provincia) VALUES (?, ?)";
    private static final String UPDATE_CITY = "UPDATE Citta SET Nome = ?, Provincia = ? WHERE Cod_Ci = ?";
    private static final String DELETE_CITY = "DELETE FROM Citta WHERE Cod_Ci = ?";

        private static final Logger logger= LogManager.getLogger();


    public List<Citta> getAllCities(Connection connection) throws DAOException {
        List<Citta> cities = new ArrayList<>();
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                cities.add(mapResultSetToCity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante  la selezione di tutte le città", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante  la selezione di tutte le città", e);

            }
        return cities;
    }

    public Citta getCityById(Connection connection,int cityId) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)) {

            preparedStatement.setInt(1, cityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCity(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante  la selezione della città con id:"+cityId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante  la selezione della città con id:"+cityId, e);

            }
        return null;
    }

    public boolean addCity(Connection connection,Citta city) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY)) {

            preparedStatement.setString(1, city.getNome());
            preparedStatement.setBoolean(2, city.isProvincia());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("Errore durante  l'aggiunta della città: "+city.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico  durante  l'aggiunta della città: "+city.getNome(), e);

            }
    }

    public boolean updateCity(Connection connection,Citta city) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY)) {

            preparedStatement.setString(1, city.getNome());
            preparedStatement.setBoolean(2, city.isProvincia());
            preparedStatement.setInt(3, city.getCodCi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento della citta con id: "+city.getCodCi(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico  durante  l'aggiornamento della città con id: "+city.getCodCi(), e);

            }
    }

    public boolean deleteCity(Connection connection,int cityId) throws DAOException {
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY)) {

            preparedStatement.setInt(1, cityId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione della citta con id: "+cityId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante  la cancellazione della città con id: "+cityId, e);

            }
    }

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
                throw new DAOException("Errore durante la ricerca della citta con con criteri", e);
                //return false;
                }
                catch(Exception e)
                {
                    throw new DAOException("Errore generico durante la ricerca della città con criteri : ", e);
    
                }
        } catch (Exception e) {
                throw new DAOException("Errore durante la prepareStatement della ricerca", e);
                //return false;
            }
    
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