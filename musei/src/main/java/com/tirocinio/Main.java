package com.tirocinio;
import java.io.BufferedReader;
import java.io.IOException;
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
    public static void main(String[] args) throws NumberFormatException, IOException {
        //Inizializzo la connessione
        /*  try (Connection connection = ConnectionManager.getConnection()) {
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
        }*/
        String workingDirectory = System.getProperty("user.dir");
        System.out.println("La working direcotry "+ workingDirectory);
        Connection connection = ConnectionManager.getConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int scelta=0;
        int subscleta=0;
        String arr[] = {"Audio","POI","Museo","Citta","Cliente","Dipendente","Artista","Opere","Biglietteria","Biglietto","Abbonamento"};

        do
        {
            System.out.println("1)Audio");
            System.out.println("2)POI");
            System.out.println("3)Museo");
            System.out.println("4)Citta");
            System.out.println("5)Cliente");
            System.out.println("6)Dipendente");
            System.out.println("7)Artista");
            System.out.println("8)Opere");
            System.out.println("9)Biglietteria");
            System.out.println("10)Biglietto");
            System.out.println("11)Abbonamento");
            System.out.println("12)Esci");

            scelta= Integer.parseInt(in.readLine());
            subscleta=0;
            switch (scelta) {
    
                    case 1:
                            do 
                            {
                                System.out.println("1)Insert "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        
                                    break;

                                    case 2:
                                        
                                    break;

                                    case 3:
                                        
                                    break;

                                    case 4:
                                        
                                    break;

                                    case 5:
                                        
                                    break;

                                    case 6:
                                        
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 2:

                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        
                                    break;

                                    case 2:
                                        
                                    break;

                                    case 3:
                                        
                                    break;

                                    case 4:
                                        
                                    break;

                                    case 5:
                                        
                                    break;

                                    case 6:
                                        
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 3:

                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        
                                    break;

                                    case 2:
                                        
                                    break;

                                    case 3:
                                        
                                    break;

                                    case 4:
                                        
                                    break;

                                    case 5:
                                        
                                    break;

                                    case 6:
                                        
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 4:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break; 

                    case 5:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 6:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 7:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 8:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 9:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 10:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 11:

                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            
                                        break;

                                        case 2:
                                            
                                        break;

                                        case 3:
                                            
                                        break;

                                        case 4:
                                            
                                        break;

                                        case 5:
                                            
                                        break;

                                        case 6:
                                            
                                        break;
                                    
                                        default:

                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 12:
                                System.out.println("Quitting");
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

        }while(scelta <12);
    }
}

