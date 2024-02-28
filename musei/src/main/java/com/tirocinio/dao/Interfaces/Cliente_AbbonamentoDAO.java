package com.tirocinio.dao.Interfaces;

import java.sql.*;

import java.util.Map;
import com.tirocinio.exceptions.DAOException;

public interface Cliente_AbbonamentoDAO {
    
    public boolean addClienteAbbonamento(Connection connection,int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException ;

    public boolean deleteClienteAbbonamento(Connection connection,int codiceCliente, int codiceAbbonamento) throws SQLException, DAOException ;

    public Map<Object, Object> leggiAbbonamentiPerCliente(Connection connection,int codiceCliente) throws SQLException, DAOException ;
    
    public Map<Object, Object> leggiClientiPerAbbonamento(Connection connection,int codiceAbbonamento) throws SQLException, DAOException ;

    public boolean updateClienteAbbonamento(Connection connection,int codiceCliente, int vecchioCodiceAbbonamento, int nuovoCodiceAbbonamento) throws SQLException, DAOException;
}
