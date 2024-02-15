package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Citta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArtistaDAO {

    private static final String SELECT_ALL_ARTISTI = "SELECT * FROM Artista";
    private static final String SELECT_ARTISTA_BY_ID = "SELECT * FROM Artista WHERE Cod_Ar = ?";
    private static final String INSERT_ARTISTA = "INSERT INTO Artista (Nome, Cognome, Data_Nascita, In_vita) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ARTISTA = "UPDATE Artista SET Nome = ?, Cognome = ?, Data_Nascita = ?, In_vita = ? WHERE Cod_Ar = ?";
    private static final String DELETE_ARTISTA = "DELETE FROM Artista WHERE Cod_Ar = ?";
    private static final String ASSOC_CITTA = "UPDATE Artista SET Cod_E_Ci = ? WHERE Cod_Ar = ?";

            private static final Logger logger= LogManager.getLogger();


    public List<Artista> getAllArtisti(Connection connection) throws DAOException {
        List<Artista> artisti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTISTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                artisti.add(mapResultSetToArtista(resultSet));
            }
        } catch (SQLException e) {
            logger.error("");

                throw new DAOException("Errore durante la selezione di tutti gli artisti", e);
                //return false;
        }
        catch(Exception e)
        {
            logger.error("");

            throw new DAOException("Errore generico durante la selezione di tutti gli artisti", e);

        }
        logger.info("SUCCESS:");

        return artisti;
    }

    public Artista getArtistaById(Connection connection, int artistaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTISTA_BY_ID)) {

            preparedStatement.setInt(1, artistaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("SUCCESS:");

                    return mapResultSetToArtista(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante la selezione dell'artista con id "+artistaId, e);
            //return false;
        }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante la selezione dell'artista con id "+artistaId, e);

    }
        return null;
    }

    public boolean addArtista(Connection connection, Artista artista) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTISTA)) {

            preparedStatement.setString(1, artista.getNome());
            preparedStatement.setString(2, artista.getCognome());
            preparedStatement.setDate(3, artista.getDataNascita());
            preparedStatement.setBoolean(4, artista.isInVita());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante l'inserimento dell'artista con id "+artista.getNome() + artista.getCognome(), e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante l'inserimento dell'artista con id "+artista.getNome() + artista.getCognome(), e);

    }
    }

    public boolean updateArtista(Connection connection, Artista artista) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTISTA)) {

            preparedStatement.setString(1, artista.getNome());
            preparedStatement.setString(2, artista.getCognome());
            preparedStatement.setDate(3, artista.getDataNascita());
            preparedStatement.setBoolean(4, artista.isInVita());
            preparedStatement.setInt(5, artista.getCodAr());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante l'aggiornemento dell'artista con id "+artista.getCodAr(), e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante l'aggiornamento dell'artista con id "+artista.getCodAr() , e);

    }
    }

    public boolean deleteArtista(Connection connection, int artistaId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTISTA)) {

            preparedStatement.setInt(1, artistaId);

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("SUCCESS:");

            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante la cancellazione dell'artista con id "+artistaId, e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante la cancellazione dell'artista con id "+artistaId, e);

    }
    }

    public List<Artista> search(Connection connection, Artista criteria) throws DAOException {
        List<Artista> matchingArtisti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Artista WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getCognome() != null) {
            queryBuilder.append(" AND Cognome = ?");
        }
        if (criteria.getDataNascita() != null) {
            queryBuilder.append(" AND Data_Nascita = ?");
        }
        if (criteria.isInVita()) {
            queryBuilder.append(" AND In_vita = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getCognome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCognome());
            }
            if (criteria.getDataNascita() != null) {
                preparedStatement.setDate(parameterIndex++, criteria.getDataNascita());
            }
            if (criteria.isInVita()) {
                preparedStatement.setBoolean(parameterIndex++, criteria.isInVita());
            }
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingArtisti.add(mapResultSetToArtista(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante la ricerca degli artisti con criteri", e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante la ricerca degli artisti con id ", e);

    }
        logger.info("SUCCESS:");

        return matchingArtisti;
    }

    public boolean associateWithCity(Connection connection, Artista artista, Citta citta) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_CITTA)) {

            statement.setInt(1, citta.getCodCi());
            statement.setInt(2, artista.getCodAr());

            int rowsAffected =statement.executeUpdate();
            logger.info("SUCCESS:");
            return rowsAffected > 0;
        }catch (SQLException e) {
            logger.error("");

            throw new DAOException("Errore durante l'associazione dell'artista e citta con IDartista:  "+artista.getCodAr()+" e IDCitta: "+citta.getCodCi(), e);
            //return false;
    }
    catch(Exception e)
    {
        logger.error("");

        throw new DAOException("Errore generico durante l'associazione dell'artista e citta con IDartista:  "+artista.getCodAr()+" e IDCitta: "+citta.getCodCi(), e);

    }
    }

    private Artista mapResultSetToArtista(ResultSet resultSet) throws SQLException {
        Artista artista = new Artista();
        artista.setCodAr(resultSet.getInt("Cod_Ar"));
        artista.setNome(resultSet.getString("Nome"));
        artista.setCognome(resultSet.getString("Cognome"));
        artista.setDataNascita(resultSet.getDate("Data_Nascita"));
        artista.setInVita(resultSet.getBoolean("In_vita"));
        return artista;
    }
}