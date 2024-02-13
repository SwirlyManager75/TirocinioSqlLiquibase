package com.tirocinio.dao;

import com.tirocinio.exceptions.DAOException;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DipendenteDAO {

    private static final String SELECT_ALL_DIPENDENTI = "SELECT * FROM Dipendente";
    private static final String SELECT_DIPENDENTE_BY_ID = "SELECT * FROM Dipendente WHERE Cod_D = ?";
    private static final String INSERT_DIPENDENTE = "INSERT INTO Dipendente (Nome, Data_Nascita, CF, Cellulare) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_DIPENDENTE = "UPDATE Dipendente SET Nome = ?, Data_Nascita = ?, CF = ?, Cellulare = ? WHERE Cod_D = ?";
    private static final String DELETE_DIPENDENTE = "DELETE FROM Dipendente WHERE Cod_D = ?";
    private static final String ASSOC_MUSEO = "UPDATE Dipendente SET Cod_E_M = ? WHERE Cod_D = ?";
    private static final String ASSOC_CITTA = "UPDATE Dipendente SET Cod_E_Ci = ? WHERE Cod_D = ?";

    public List<Dipendente> getAllDipendenti(Connection connection) throws DAOException {
        List<Dipendente> dipendenti = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIPENDENTI);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                dipendenti.add(mapResultSetToDipendente(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la selezione di tutti i dipendenti", e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la selezione di tutti i dipendenti", e);

            }
        return dipendenti;
    }

    public Dipendente getDipendenteById(Connection connection, int dipendenteId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIPENDENTE_BY_ID)) {

            preparedStatement.setInt(1, dipendenteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDipendente(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la selezione del dipendente con id "+dipendenteId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore durante la selezione del dipendente con id "+dipendenteId, e);

            }
        return null;
    }

    public boolean addDipendente(Connection connection, Dipendente dipendente) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIPENDENTE)) {

            preparedStatement.setString(1, dipendente.getNome());
            preparedStatement.setDate(2, dipendente.getDataNascita());
            preparedStatement.setString(3, dipendente.getCodiceFiscale());
            preparedStatement.setString(4, dipendente.getCellulare());
            

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiunta del dipendente: "+dipendente.getNome(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiunta del dipendente: "+dipendente.getNome(), e);

            }
    }

    public boolean updateDipendente(Connection connection, Dipendente dipendente) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIPENDENTE)) {

            preparedStatement.setString(1, dipendente.getNome());
            preparedStatement.setDate(2, dipendente.getDataNascita());
            preparedStatement.setString(3, dipendente.getCodiceFiscale());
            preparedStatement.setString(4, dipendente.getCellulare());
            preparedStatement.setInt(5, dipendente.getCodD());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiornamento del dipendente: "+dipendente.getCodD(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante l'aggiornamento del dipendente: "+dipendente.getCodD(), e);
                //return false;
    
            }
    }

    public boolean deleteDipendente(Connection connection, int dipendenteId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DIPENDENTE)) {

            preparedStatement.setInt(1, dipendenteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante la cancellazione del dipendente: "+dipendenteId, e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore generico durante la cancellazione del dipendente: "+dipendenteId, e);

            }
    }

    public List<Dipendente> search(Connection connection, Dipendente criteria) throws DAOException {
        List<Dipendente> matchingDipendenti = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Dipendente WHERE 1=1");

        if (criteria.getNome() != null) {
            queryBuilder.append(" AND Nome = ?");
        }
        if (criteria.getDataNascita() != null) {
            queryBuilder.append(" AND Data_Nascita = ?");
        }
        if (criteria.getCodiceFiscale() != null) {
            queryBuilder.append(" AND CF = ?");
        }
        if (criteria.getCellulare() != null) {
            queryBuilder.append(" AND Cellulare = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (criteria.getNome() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getNome());
            }
            if (criteria.getDataNascita() != null) {
                preparedStatement.setDate(parameterIndex++, criteria.getDataNascita());
            }
            if (criteria.getCodiceFiscale() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCodiceFiscale());
            }
            if (criteria.getCellulare() != null) {
                preparedStatement.setString(parameterIndex++, criteria.getCellulare());
            }
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingDipendenti.add(mapResultSetToDipendente(resultSet));
                }
            }
            catch (SQLException e) {
                throw new DAOException("Errore generico durante la ricerca dei dipendenti con criteri: "+criteria.getCodiceFiscale(), e);
                //return false;
                }
                catch(Exception e)
                {
                    throw new DAOException("Errore generico durante la ricerca dei dipendenti con criteri: "+criteria.getCodiceFiscale(), e);
    
                }
        } catch (Exception e) {
            throw new DAOException("DAO Exception search", null);
            //return false;
        }

        return matchingDipendenti;
    }

    public boolean associateWithCity(Connection connection, Dipendente dipendente, Citta citta) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_CITTA)) {

            statement.setInt(1, citta.getCodCi());
            statement.setInt(2, dipendente.getCodD());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione tra dipendente e citta id rispettivi:"+dipendente.getCodD()+","+citta.getCodCi(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore durante l'associazione tra dipendente e citta id rispettivi:"+dipendente.getCodD()+","+citta.getCodCi(), e);

            }
    }

    public boolean associateWithMuseum(Connection connection, Dipendente dipendente, Museo museo) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                ASSOC_MUSEO)) {

            statement.setInt(1, museo.getCodM());
            statement.setInt(2, dipendente.getCodD());

            int rowsAffected =statement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new DAOException("Errore durante l'associazione tra dipendente e museo id rispettivi:"+dipendente.getCodD()+","+museo.getCodM(), e);
            //return false;
            }
            catch(Exception e)
            {
                throw new DAOException("Errore durante l'associazione tra dipendente e museo id rispettivi:"+dipendente.getCodD()+","+museo.getCodM(), e);

            }
    }

    private Dipendente mapResultSetToDipendente(ResultSet resultSet) throws SQLException {
        Dipendente dipendente = new Dipendente();
        dipendente.setCodD(resultSet.getInt("Cod_D"));
        dipendente.setNome(resultSet.getString("Nome"));
        dipendente.setDataNascita(resultSet.getDate("Data_Nascita"));
        dipendente.setCodiceFiscale(resultSet.getString("CF"));
        dipendente.setCellulare(resultSet.getString("Cellulare"));
        
        return dipendente;
    }
}