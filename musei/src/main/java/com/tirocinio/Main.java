package com.tirocinio;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.model.Citta;
import com.tirocinio.service.CreateCityService;
public class Main {
    public static void main(String[] args) {
        //Inizializzo la connessione
         try (Connection connection = ConnectionManager.getConnection()) {
            // Esempio di inserimento di una nuova città
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            CreateCityService insertCittaService = new CreateCityService(connection);
            System.out.println("Inserisci il nome della città da inserire nel DB");
            String cityName= in.readLine();
            insertCittaService.execute(new Citta(cityName,false));

            System.out.println("Inserimento città completato con successo!");

            // Esempio di visualizzazione di tutte le città
            CittaDAO cittaDAO = new CittaDAO();
            List<Citta> tutteLeCitta = cittaDAO.getAllCities(connection);

            System.out.println("Tutte le città nel database:");
            for (Citta citta : tutteLeCitta) {
                System.out.println("Nome: " + citta.getNome() + ", Provincia: " + citta.isProvincia());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Si è verificato un errore durante l'inserimento della città o la visualizzazione delle città.");
        }
    }
}

