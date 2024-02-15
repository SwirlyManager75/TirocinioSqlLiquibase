package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PoiDAO {

    private static final String SELECT_ALL_POIS = "SELECT * FROM Poi";
    private static final String SELECT_POI_BY_ID = "SELECT * FROM Poi WHERE Cod_Poi = ?";
    private static final String INSERT_POI = "INSERT INTO Poi (Descrizione) VALUES (?)";
    private static final String UPDATE_POI = "UPDATE Poi SET Descrizione = ? WHERE Cod_Poi = ?";
    private static final String DELETE_POI = "DELETE FROM Poi WHERE Cod_Poi = ?";
    private static final String ASSOC_MUSEO =  "UPDATE Poi SET Cod_E_M = ? WHERE Cod_Poi = ?";
    private static final String GETLAST_POI = "SELECT * FROM Poi WHERE Cod_Poi = (SELECT MAX(Cod_Poi) FROM Poi) ";
    
    private static final Logger logger= LogManager.getLogger(PoiDAO.class);

    public List<Poi> getAllPois(Connection connection) throws DAOException {
        

        List<Poi> pois = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POIS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                pois.add(mapResultSetToPoi(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante la selezione di tutti i POI", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore generico durante la selezione di tutti i POI", e);

            }
            logger.info("SUCCESS:");

        return pois;
    }

    public Poi getPoiById(Connection connection, int poiId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POI_BY_ID)) {

            preparedStatement.setInt(1, poiId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS:");

                    return mapResultSetToPoi(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante la selezione del POI con id:"+poiId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore durante la selezione del POI con id:"+poiId, e);

            }
        return null;
    }

    public Poi getLastPoi(Connection connection) throws DAOException
    {
        Poi poi=new Poi();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GETLAST_POI))
        {

            ResultSet resultP = preparedStatement.executeQuery();
            poi.setCodPoi(resultP.getInt("Cod_Poi"));
            poi.setDescrizione(resultP.getString("Descrizione"));
            
            if(resultP.getInt("Cod_E_M")!=0)
            {
                try(PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM Museo WHERE Cod_M = "+resultP.getString("Cod_E_M")))
                {
                    Museo m=new Museo();

                    ResultSet resultM =  preparedStatement2.executeQuery();
                    
                    m.setCodM(resultM.getInt("Cod_M"));
                    m.setNome("Nome");
                    m.setVia("Via");
                }
                catch(SQLException e)
                {
                    logger.error("SqlError");

                    throw new DAOException("Errore durante il recupero del Museo a cui è legato il POI ", e);
                }
                catch(Exception e)
                {
                    logger.error("SqlError");

                    throw new DAOException("Errore generico durante il recupero del Museo a cui è legato il POI ", e);

                }

            }
            logger.info("SUCCESS:");

            return poi;
        }
        catch(SQLException e)
        {
            logger.error("SqlError");

            throw new DAOException("Errore durante la selezione dell'ultimo poi inserito", e);
        }
        catch(Exception e)
        {
            logger.error("SqlError");

            throw new DAOException("Errore generico durante la selezione dell'ultimo poi inserito", e);

        }
    }

    public Poi addPoi(Connection connection, Poi poi) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POI)) {

            preparedStatement.setString(1, poi.getDescrizione());
    
            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return getLastPoi(connection);
        } catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante l'aggiunta del POI", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore generico durante l'aggiunta del POI", e);

            }
    }

    public Poi updatePoi(Connection connection, Poi poi) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POI)) {

            preparedStatement.setString(1, poi.getDescrizione());
           
            preparedStatement.setInt(2, poi.getCodPoi());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return poi;//prima ritornavo bool
        }  catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante l'aggiornamento del POI con id:"+poi.getCodPoi(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore generico durante l'aggiornamento del POI con id:"+poi.getCodPoi(), e);

            }
    }

    public boolean deletePoi(Connection connection, int poiId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POI)) {

            preparedStatement.setInt(1, poiId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante la cancellazione del POI con id:"+poiId, e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore generico durante la cancellazione del POI con id:"+poiId, e);

            }
    }

    public List<Poi> search(Connection connection, Poi criteria) throws DAOException {
        List<Poi> matchingPois = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Poi WHERE 1=1");

        // Aggiungo i criteri alla query dinamicamente se sono presenti nella classe criteria
        if (criteria.getDescrizione() != null) {
            queryBuilder.append(" AND Descrizione LIKE ?");
        }
        
        // Aggiungo altri criteri secondo necessità

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            // Imposto i parametri dinamici nella query
            if (criteria.getDescrizione() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getDescrizione() + "%");
            }
            
            // altri parametri secondo necessità

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingPois.add(mapResultSetToPoi(resultSet));
                }
            }
            catch (SQLException e) {
                logger.error("SqlError");

                throw new DAOException("Errore durante la ricerca dei POI con criteri", e);
                //return false;
                }
                catch(Exception e)
                {
                    logger.error("SqlError");

                    throw new DAOException("Errore durante la ricerca dei POI con criteri", e);
    
                }
        }
        catch (Exception e) {
            logger.error("SqlError");

            throw new DAOException("Errore generico durante la prepare statement  ", e);
            //return false;
        }

        logger.info("SUCCESS:");

        return matchingPois;
    }

    public boolean associateWithMuseum(Connection connection, Poi poi, Museo museo) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(ASSOC_MUSEO)) {

            statement.setInt(1, museo.getCodM());
            statement.setInt(2, poi.getCodPoi());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS:");

            return rowsAffected > 0;
        }  catch (SQLException e) {
            logger.error("SqlError");

            throw new DAOException("Errore durante l'associazione tra il POI e il museo con id rispettivi:"+poi.getCodPoi()+","+museo.getCodM(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("SqlError");

                throw new DAOException("Errore durante l'associazione tra il POI e il museo con id rispettivi:"+poi.getCodPoi()+","+museo.getCodM(), e);

            }
    }

    private Poi mapResultSetToPoi(ResultSet resultSet) throws SQLException {
        Poi poi = new Poi();
        poi.setCodPoi(resultSet.getInt("Cod_Poi"));
        poi.setDescrizione(resultSet.getString("Descrizione"));
        return poi;
    }
}