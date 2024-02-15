package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AudioDAO {

    private static final String SELECT_ALL_AUDIOS = "SELECT * FROM Audio";
    private static final String SELECT_AUDIO_BY_ID = "SELECT * FROM Audio WHERE Cod_Au = ?";
    private static final String INSERT_AUDIO = "INSERT INTO Audio (URL, Cod_E_Poi) VALUES (?, ?)";
    private static final String UPDATE_AUDIO = "UPDATE Audio SET URL = ? WHERE Cod_Au = ?";
    private static final String DELETE_AUDIO = "DELETE FROM Audio WHERE Cod_Au = ?";
    private static final String ASSOC_POI = "UPDATE Audio SET Cod_E_Poi = ? WHERE Cod_Au = ?";

        private static final Logger logger= LogManager.getLogger();


    public List<Audio> getAllAudios(Connection connection) throws DAOException {
        List<Audio> audios = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUDIOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                audios.add(mapResultSetToAudio(resultSet));
            }
            logger.info("Audio selezionati e mappati");
        }catch (SQLException e) {
            logger.error("SQLEXCEPTION in getAllAudios "+e.getMessage());

            throw new DAOException("Errore durante la selezione di tutti gli audio", e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("EXCEPTION in getAllAudios "+e.getMessage());

        throw new DAOException("Errore generico durante la selezione di tutti gli audio", e);

    }
        logger.info("SUCCESS:");

        return audios;
    }

    public Audio getAudioById(Connection connection, int audioId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUDIO_BY_ID)) {

            preparedStatement.setInt(1, audioId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS: Selezione Audio con id"+audioId);
                    return mapResultSetToAudio(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Errore durante la selezione dell'audio con id:"+audioId+" "+e.getMessage());
            throw new DAOException("Errore durante la selezione dell'audio con id:"+audioId, e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("Errore durante la selezione dell'audio con id:"+audioId+" "+e.getMessage()); 
        throw new DAOException("Errore generico durante la selezione dell'audio con id:"+audioId, e);

    }
        return null;
    }

    public boolean addAudio(Connection connection, Audio audio) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUDIO)) {

            preparedStatement.setString(1, audio.getUrl());
            

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: Aggiunta Audio rowsaffected:"+rowsAffected);
            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("SQLError durante l'aggiunta dell'audio con url:"+audio.getUrl()+" ,"+e.getMessage());
            throw new DAOException("Errore durante l'aggiunta dell'audio con url:"+audio.getUrl(), e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("Errore generico durante l'aggiunta dell'audio con url:"+audio.getUrl()+" ,"+e.getMessage());
        throw new DAOException("Errore generico durante l'aggiunta dell'audio con url:"+audio.getUrl(), e);

    }

    }

    public Audio updateAudio(Connection connection, Audio audio) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUDIO)) {

            preparedStatement.setString(1, audio.getUrl());
            preparedStatement.setInt(2, audio.getCodAu());

            int rowsAffected = preparedStatement.executeUpdate();
            //return rowsAffected > 0;
            logger.info("SUCCESS: Aggiornamento Audio, rowsaffected:"+rowsAffected);
            return audio;
        }catch (SQLException e) {
            logger.error("SQLError durante l'aggiornamento dell'audio con id:"+audio.getCodAu()+" ,"+e.getMessage());
            throw new DAOException("Errore durante l'aggiornamento dell'audio con id:"+audio.getCodAu(), e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("Errore generico durante l'aggiornamento dell'audio con id:"+audio.getCodAu()+" ,"+e.getMessage());

        throw new DAOException("Errore generico durante l'aggiunta dell'audio con id:"+audio.getCodAu(), e);

    }
    }

    public boolean deleteAudio(Connection connection, int audioId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUDIO)) {

            preparedStatement.setInt(1, audioId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS: Cancellazione dell'audio, rowsAffeccted:"+rowsAffected);
            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("Errore durante la cancellazione dell'audio con id:"+audioId+" , "+e.getMessage());
            throw new DAOException("Errore durante la cancellazione dell'audio con id:"+audioId, e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("Errore durante la cancellazione dell'audio con id:"+audioId+" , "+e.getMessage());
        throw new DAOException("Errore generico durante la cancellazione dell'audio con id:"+audioId, e);
    }

    }

    public List<Audio> search(Connection connection, Audio criteria) throws DAOException {
        List<Audio> matchingAudios = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Audio WHERE 1=1");

        
        if (criteria.getUrl() != null) {
            queryBuilder.append(" AND URL LIKE ?");
        }
        
        // Aggiungi altri criteri secondo necessità

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            // Imposta i parametri dinamici nella query
            if (criteria.getUrl() != null) {
                preparedStatement.setString(parameterIndex++, "%" + criteria.getUrl() + "%");
            }
            
            // Imposta altri parametri secondo necessità

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingAudios.add(mapResultSetToAudio(resultSet));
                }
            }
            catch (SQLException e) {
                logger.error("SQLError durante la ricerca dell'audio con criteri "+e.getMessage());
            throw new DAOException("Errore durante la ricerca dell'audio con criteri:", e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico in ricerca audio con criteri "+e.getMessage());

                throw new DAOException("Errore generico durante la ricerca dell'audio con criteri:", e);

            }
        } catch (Exception e) {
            logger.error("Prepared Statement error in ricerca audio con criteri "+e.getMessage());
            throw new DAOException("Prepared Statement error in ricerca audio con criteri", e);
            //return false;
        }
        logger.info("SUCCESS, Search Audios completata");
        return matchingAudios;
    }

    public boolean associateWithPoi(Connection connection, Audio audio, Poi poi) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(ASSOC_POI)) {

            statement.setInt(1, poi.getCodPoi());
            statement.setInt(2, audio.getCodAu());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS: Associazione Audio a POI completata, rowsAffected:"+rowsAffected);
            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("SQLError durante l'associazione dell'audio con il poi, id rispettivi"+audio.getCodAu()+", "+poi.getCodPoi()+" ,"+e.getMessage());
            throw new DAOException("Errore durante l'associazione dell'audio con il poi, id rispettivi"+audio.getCodAu()+", "+poi.getCodPoi(), e);
            //return false;
            }
            catch(Exception e)
            {
                logger.error("Errore generico durante l'associazione dell'audio con il poi, id rispettivi"+audio.getCodAu()+", "+poi.getCodPoi()+" ,"+e.getMessage());

                throw new DAOException("Errore generico durante l'associazione dell'audio con il poi, id rispettivi"+audio.getCodAu()+", "+poi.getCodPoi(), e);
            }
    }

    private Audio mapResultSetToAudio(ResultSet resultSet) throws SQLException {
        Audio audio = new Audio();
        audio.setCodAu(resultSet.getInt("Cod_Au"));
        audio.setUrl(resultSet.getString("URL"));
        return audio;
    }
}