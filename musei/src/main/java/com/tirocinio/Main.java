package com.tirocinio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.tirocinio.connection.ConnectionManager;
import com.tirocinio.dao.AbbonamentoDAO;
import com.tirocinio.dao.ArtistaDAO;
import com.tirocinio.dao.AudioDAO;
import com.tirocinio.dao.BiglietteriaDAO;
import com.tirocinio.dao.BigliettoDAO;
import com.tirocinio.dao.CittaDAO;
import com.tirocinio.dao.ClienteDAO;
import com.tirocinio.dao.DipendenteDAO;
import com.tirocinio.dao.MuseoDAO;
import com.tirocinio.dao.OperaDAO;
import com.tirocinio.dao.PoiDAO;
import com.tirocinio.model.Abbonamento;
import com.tirocinio.model.Artista;
import com.tirocinio.model.Audio;
import com.tirocinio.model.Biglietteria;
import com.tirocinio.model.Biglietto;
import com.tirocinio.model.Citta;
import com.tirocinio.model.Cliente;
import com.tirocinio.model.Dipendente;
import com.tirocinio.model.Museo;
import com.tirocinio.model.Opera;
import com.tirocinio.model.Poi;
import com.tirocinio.service.CreateAbbonamentoService;
import com.tirocinio.service.CreateArtistaService;
import com.tirocinio.service.CreateAudioService;
import com.tirocinio.service.CreateBiglietteriaService;
import com.tirocinio.service.CreateBigliettoService;
import com.tirocinio.service.CreateCityService;
import com.tirocinio.service.CreateClienteService;
import com.tirocinio.service.CreateDipendenteService;
import com.tirocinio.service.CreateMuseumService;
import com.tirocinio.service.CreateOperaService;
import com.tirocinio.service.CreatePoiService;
import com.tirocinio.service.DeleteAbbonamentoService;
import com.tirocinio.service.DeleteArtistaService;
import com.tirocinio.service.DeleteAudioService;
import com.tirocinio.service.DeleteBiglietteriaService;
import com.tirocinio.service.DeleteBigliettoService;
import com.tirocinio.service.DeleteCityService;
import com.tirocinio.service.DeleteClienteService;
import com.tirocinio.service.DeleteDipendenteService;
import com.tirocinio.service.DeleteMuseumService;
import com.tirocinio.service.DeleteOperaService;
import com.tirocinio.service.DeletePoiService;
import com.tirocinio.service.GetAllAbbonamentiService;
import com.tirocinio.service.GetAllArtistiService;
import com.tirocinio.service.GetAllAudiosService;
import com.tirocinio.service.GetAllBiglietterieService;
import com.tirocinio.service.GetAllBigliettiService;
import com.tirocinio.service.GetAllCitiesService;
import com.tirocinio.service.GetAllClientiService;
import com.tirocinio.service.GetAllDipendentiService;
import com.tirocinio.service.GetAllMuseumsService;
import com.tirocinio.service.GetAllOpereService;
import com.tirocinio.service.GetAllPoisService;
import com.tirocinio.service.SearchAbbonamentoService;
import com.tirocinio.service.SearchArtistaService;
import com.tirocinio.service.SearchAudioService;
import com.tirocinio.service.SearchBiglietteriaService;
import com.tirocinio.service.SearchBigliettoService;
import com.tirocinio.service.SearchCityService;
import com.tirocinio.service.SearchClienteService;
import com.tirocinio.service.SearchDipendenteService;
import com.tirocinio.service.SearchMuseumService;
import com.tirocinio.service.SearchOperaService;
import com.tirocinio.service.SearchPoiService;
import com.tirocinio.service.UpdateAbbonamentoService;
import com.tirocinio.service.UpdateArtistaService;
import com.tirocinio.service.UpdateAudioService;
import com.tirocinio.service.UpdateBiglietteriaService;
import com.tirocinio.service.UpdateBigliettoService;
import com.tirocinio.service.UpdateCityService;
import com.tirocinio.service.UpdateClienteService;
import com.tirocinio.service.UpdateDipendenteService;
import com.tirocinio.service.UpdateMuseumService;
import com.tirocinio.service.UpdateOperaService;
import com.tirocinio.service.UpdatePoiService;
public class Main 
{
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException 
    {
        //Inizializzo la connessione
        Connection connection = ConnectionManager.getConnection();// TODO SPOSTARE APERTURA E CHIUSURA DELLA CONNESSIONE NEI SERVICE
        connection.setAutoCommit(false); //TODO GESTIRE LE TRANSAZIONI, RIVEDERE TUTTE LE OPERAZIONI DB
        
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
            switch (scelta) 
            {
    
                    case 1:
                    AudioDAO audioDAO= new AudioDAO();

                            do 
                            {
                                System.out.println("1)Insert "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Audio audio = new Audio();
                                List<Audio> audioList = new ArrayList<>();
                                

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                            
                                        System.out.println("Inserisci la URL dell'audio");
                                        audio.setUrl(in.readLine());

                                        CreateAudioService cr= new CreateAudioService();

                                        cr.execute(audio);

                                    break;

                                    case 2:

                                        System.out.println("Inserisci la URL modificata");
                                        audio.setUrl(in.readLine());
                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        UpdateAudioService up= new UpdateAudioService();
                                        up.execute(audio);

                                    break;

                                    case 3:

                                        System.out.println("Inserisci il codice dell'audio per la cancellazione");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        DeleteAudioService dl= new DeleteAudioService();
                                        dl.execute(audio.getCodAu());

                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        audio= audioDAO.getAudioById(connection,audio.getCodAu());
                                        System.out.println("Audio.URL: " + audio.getUrl());
                                    break;

                                    case 5:
                                        GetAllAudiosService allAudiosService = new GetAllAudiosService();
                                        
                                        audioList=allAudiosService.execute();

                                        for(Audio aud : audioList)
                                        {
                                            System.out.println("Audio.Cod_Au:"+aud.getCodAu()+"  Audio.Url:"+aud.getUrl());
                                        }
                                    break;

                                    case 6:
                                        SearchAudioService searchAudioService = new SearchAudioService();
                                        
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la URL)");
                                        audio.setUrl(in.readLine());
                                        audioList= searchAudioService.execute(audio);

                                        for(Audio aud : audioList)
                                        {
                                            System.out.println("Audio.Cod_Au:"+aud.getCodAu()+"  Audio.Url:"+aud.getUrl());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 2:
                        PoiDAO poiDao = new PoiDAO();
                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Poi poi= new Poi();
                                List<Poi> poiList= new ArrayList<>();

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        System.out.println("Inserisci la descriozione dell'POI");
                                        poi.setDescrizione(in.readLine());

                                        CreatePoiService createPoiService = new CreatePoiService();

                                        createPoiService.execute(poi);
                                    break;

                                    case 2:
                                        System.out.println("Inserisci la Descrizione del POI modificata");
                                        poi.setDescrizione(in.readLine());
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        UpdatePoiService updatePoiService = new UpdatePoiService();
                                        updatePoiService.execute(poi);
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del POI per la cancellazione");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        DeletePoiService deletePoiService = new DeletePoiService();
                                        deletePoiService.execute(poi.getCodPoi());
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        poiDao.getPoiById(connection, poi.getCodPoi());
                                    break;

                                    case 5:
                                        GetAllPoisService getAllPoisService = new GetAllPoisService();

                                        poiList = getAllPoisService.execute();

                                        for(Poi poi2 : poiList)
                                        {
                                            System.out.println("Poi.CodPoi:"+poi2.getCodPoi()+"  Poi.Descrizione:"+poi2.getDescrizione());
                                        }
                                        
                                    break;

                                    case 6:
                                        SearchPoiService searchPoiService = new SearchPoiService();
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la Descrizione)");

                                        poi.setDescrizione(in.readLine());

                                        poiList= searchPoiService.execute(poi);

                                        for(Poi poi2 : poiList)
                                        {
                                            System.out.println("Poi.CodPoi:"+poi2.getCodPoi()+"  Poi.Descrizione:"+poi2.getDescrizione());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 3:
                        MuseoDAO museoDAO = new MuseoDAO();
                            do 
                            {
                                System.out.println("1)Insert  "+ arr[scelta-1]);
                                System.out.println("2)Update  "+ arr[scelta-1]);
                                System.out.println("3)Delete  "+ arr[scelta-1]);
                                System.out.println("4)Get  "+ arr[scelta-1]);
                                System.out.println("5)Get All  "+ arr[scelta-1]);
                                System.out.println("6)Search  "+ arr[scelta-1]);
                                System.out.println("7)Esci");

                                Museo museo = new Museo();
                                List<Museo> museoList = new ArrayList<>();

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                        System.out.println("Inserisci il nome del Museo");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la via del Museo se ne sei a conoscenza");
                                        museo.setVia(in.readLine());

                                        CreateMuseumService createMuseumService = new CreateMuseumService();
                                        createMuseumService.execute(museo);
                                    break;

                                    case 2:
                                        System.out.println("Inserisci il nome sostituitivo del Museo ");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la via sostituitiva del Museo se ne sei a conoscenza");
                                        museo.setVia(in.readLine());

                                        UpdateMuseumService updateMuseumService = new UpdateMuseumService();
                                        updateMuseumService.execute(museo);
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del Museo per la cancellazione");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        DeleteMuseumService deleteMuseumService = new DeleteMuseumService();
                                        deleteMuseumService.execute(museo.getCodM());
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del Museo per la ricerca");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        museoDAO.getMuseumById(connection, museo.getCodM());
                                        System.out.println("Museo.CodM:" + museo.getCodM()+"Museo.Nome:"+museo.getNome()+"Museo.Via:"+museo.getVia());
                                    break;

                                    case 5:
                                        GetAllMuseumsService getAllMuseumsService = new GetAllMuseumsService();

                                        museoList = getAllMuseumsService.execute();

                                        for(Museo museo2  : museoList)
                                        {
                                            System.out.println("Museo.CodM:" + museo2.getCodM()+"Museo.Nome:"+museo2.getNome()+"Museo.Via:"+museo2.getVia());
                                        }
                                        
                                    break;

                                    case 6:
                                        SearchMuseumService searchMuseumService = new SearchMuseumService();
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro il Nome)");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la Via)");
                                        museo.setVia(in.readLine());

                                        museoList = searchMuseumService.execute(museo);

                                        for(Museo museo2  : museoList)
                                        {
                                            System.out.println("Museo.CodM:" + museo2.getCodM()+"Museo.Nome:"+museo2.getNome()+"Museo.Via:"+museo2.getVia());
                                        }
                                    break;
                                
                                    default:

                                    break;
                                }
                                
                            } while (subscleta < 7);
                    
                    break;

                    case 4:
                        CittaDAO cittaDAO = new CittaDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Citta citta = new Citta();
                                    List<Citta> cittaList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome della Citta");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci true se la citta è una provincia");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine())); //inserire true nella stringa
    
                                            CreateCityService createCityService = new CreateCityService();
                                            createCityService.execute(citta);
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo della Città");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci true se la Città è una provincia");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine())); //inserire true nella stringa

                                            UpdateCityService updateCityService = new UpdateCityService();
                                            updateCityService.execute(citta);
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Città per la cancellazione");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            DeleteCityService deleteCityService = new DeleteCityService();
                                            deleteCityService.execute(citta.getCodCi());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Città per la ricerca");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            cittaDAO.getCityById(connection, citta.getCodCi()); //TODO CREARE SERVICE
                                            System.out.println("Citta.CodCi:" + citta.getCodCi()+"Citta.Nome:"+citta.getNome()+"Citta.Provincia:"+citta.isProvincia());
                                        break;
    
                                        case 5:
                                            GetAllCitiesService getAllCitiesService = new GetAllCitiesService();
    
                                            cittaList = getAllCitiesService.execute();
    
                                            for(Citta citta2  : cittaList)
                                            {
                                                System.out.println("Citta.CodCi:" + citta2.getCodCi()+"Citta.Nome:"+citta2.getNome()+"Citta.Provincia:"+citta2.isProvincia());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchCityService searchCityService = new SearchCityService();

                                            System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro il Nome)");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci se la Città è una provincia (si cerca l'occorrenza della stringa dentro la Via)");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine()));
    
                                            cittaList = searchCityService.execute(citta);
    
                                            for(Citta citta2  : cittaList)
                                            {
                                                System.out.println("Citta.CodCi:" + citta2.getCodCi()+"Citta.Nome:"+citta2.getNome()+"Citta.Provincia:"+citta2.isProvincia());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break; 

                    case 5:
                        ClienteDAO clienteDAO = new ClienteDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Cliente cliente = new Cliente();
                                    List<Cliente> clientList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
                                            
                                            CreateClienteService createClienteService = new CreateClienteService();
                                            createClienteService.execute(cliente);
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome sostituitivo del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare sostituitivo del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail sostituitiva del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
                                            
                                            UpdateClienteService updateClienteService = new UpdateClienteService();
                                            updateClienteService.execute(cliente);
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Cliente per la cancellazione");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));
    
                                            DeleteClienteService deleteClienteService = new DeleteClienteService();
                                            deleteClienteService.execute(cliente.getCodCli());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Cliente per la ricerca");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));

                                            cliente = clienteDAO.getClienteById(connection, subscleta);
                                            
                                            System.out.println("Cliente.CodCli:" + cliente.getCodCli()+" Cliente.Nome:"+cliente.getNome()+" Cliente.Cognome:"+cliente.getCognome()+"Cliente.Cellulare:"+cliente.getCellulare()+"Cliente.Mail:"+cliente.getMail());
                                        break;
    
                                        case 5:
                                            GetAllClientiService getAllClientiService = new GetAllClientiService();
    
                                            clientList = getAllClientiService.execute();
    
                                            for(Cliente cliente2  : clientList)
                                            {
                                                System.out.println("Cliente.CodCli:" + cliente2.getCodCli()+" Cliente.Nome:"+cliente2.getNome()+" Cliente.Cognome:"+cliente2.getCognome()+"Cliente.Cellulare:"+cliente2.getCellulare()+"Cliente.Mail:"+cliente2.getMail());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchClienteService searchClienteService = new SearchClienteService();

                                            System.out.println("Inserimento Criteri di ricerca");
                                            System.out.println("Inserisci il nome del Cliente");
                                            cliente.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Cliente se pervenuto");
                                            cliente.setCognome(in.readLine());
                                            System.out.println("Inserisci il cellulare del Cliente se pervenuto");
                                            cliente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la mail del Cliente se pervenuta");
                                            cliente.setMail(in.readLine());
    
                                            clientList = searchClienteService.execute(cliente);
    
                                            for(Cliente cliente2  : clientList)
                                            {
                                                System.out.println("Cliente.CodCli:" + cliente2.getCodCli()+" Cliente.Nome:"+cliente2.getNome()+" Cliente.Cognome:"+cliente2.getCognome()+"Cliente.Cellulare:"+cliente2.getCellulare()+"Cliente.Mail:"+cliente2.getMail());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 6:
                            DipendenteDAO dipendenteDAO = new DipendenteDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Dipendente dipendente = new Dipendente();
                                    List<Dipendente> dipList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) {
                                        case 1:
                                            System.out.println("Inserisci il nome del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita nel formato 'yyyy-MM-dd' del Dipendente");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            CreateDipendenteService createDipendenteService = new CreateDipendenteService();
                                            createDipendenteService.execute(dipendente);
                                        break;
    
                                        case 2:
                                            
                                            System.out.println("Inserisci il nome sostituitivo del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale sostituitivo del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare sostituitivo del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita sostituitiva nel formato 'yyyy-MM-dd' del Dipendente se pervenuta");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            UpdateDipendenteService updetaDipendenteService = new UpdateDipendenteService();
                                            updetaDipendenteService.execute(dipendente);
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Dipendente per la cancellazione");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));
    
                                            DeleteDipendenteService deleteDipendenteService = new DeleteDipendenteService();
                                            deleteDipendenteService.execute(dipendente.getCodD());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Dipendente");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));

                                            dipendente = dipendenteDAO.getDipendenteById(connection, subscleta);
                                            
                                            System.out.println("Dipedente.CodCli:" + dipendente.getCodD()+" Dipedente.Nome:"+dipendente.getNome()+" Dipedente.CodiceFiscale:"+dipendente.getCodiceFiscale()+"Dipedente.Cellulare:"+dipendente.getCellulare()+"Dipedente.Data_Di_Nascita:"+dipendente.getDataNascita());

                                        break;
    
                                        case 5:
                                            GetAllDipendentiService getAllDipendentiService = new GetAllDipendentiService();
    
                                            dipList = getAllDipendentiService.execute();
    
                                            for(Dipendente dip  : dipList)
                                            {
                                                System.out.println("Dipedente.CodCli:" + dip.getCodD()+" Dipedente.Nome:"+dip.getNome()+" Dipedente.CodiceFiscale:"+dip.getCodiceFiscale()+"Dipedente.Cellulare:"+dip.getCellulare()+"Dipedente.Data_Di_Nascita:"+dip.getDataNascita());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchDipendenteService searchDipendenteService = new SearchDipendenteService();

                                            System.out.println("Inserimento Criteri di ricerca");
                                            System.out.println("Inserisci il nome del Dipendente");
                                            dipendente.setNome(in.readLine());
                                            System.out.println("Inserisci il Codice Fiscale del Dipendente se pervenuto");
                                            dipendente.setCodiceFiscale(in.readLine());
                                            System.out.println("Inserisci il cellulare del Dipendente se pervenuto");
                                            dipendente.setCellulare(in.readLine());
                                            System.out.println("Inserisci la data di nascita nel formato 'yyyy-MM-dd' del Dipendente");
                                            dipendente.setDataNascita(Date.valueOf(in.readLine()));
    
                                            dipList = searchDipendenteService.execute(dipendente);
    
                                            for(Dipendente dip  : dipList)
                                            {
                                                System.out.println("Dipedente.CodCli:" + dip.getCodD()+" Dipedente.Nome:"+dip.getNome()+" Dipedente.CodiceFiscale:"+dip.getCodiceFiscale()+"Dipedente.Cellulare:"+dip.getCellulare()+"Dipedente.Data_Di_Nascita:"+dip.getDataNascita());
                                            }
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 7:
                                ArtistaDAO artistaDAO = new ArtistaDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Artista artista = new Artista();
                                    List<Artista> artList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:
                                            System.out.println("Inserisci il nome del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            CreateArtistaService createArtistaService = new CreateArtistaService();
                                            createArtistaService.execute(artista);
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome sostituitivo del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita sostituitiva del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
                                            
                                            UpdateArtistaService updateArtistaService = new UpdateArtistaService();
                                            updateArtistaService.execute(artista);
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Artista per la cancellazione");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));
    
                                            DeleteArtistaService deleteArtistaService = new DeleteArtistaService();
                                            deleteArtistaService.execute(artista.getCodAr());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Artista per la ricerca");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));

                                            artista= artistaDAO.getArtistaById(connection, artista.getCodAr());
                                            
                                            System.out.println("Artista.CodAr:" + artista.getCodAr()+" Artista.Nome:"+artista.getNome()+" Artista.Cognome:"+artista.getCognome()+"Artista.In_Vita:"+artista.isInVita()+"Artista.Data_Nascita:"+artista.getDataNascita());
                                        break;
    
                                        case 5:
                                            GetAllArtistiService getAllArtistiService = new GetAllArtistiService();
    
                                            artList = getAllArtistiService.execute();
                                            for(Artista art : artList)
                                            {
                                                System.out.println("|Artista.CodAr:" + art.getCodAr()+"| Artista.Nome:"+art.getNome()+"| Artista.Cognome:"+art.getCognome()+" |Artista.In_Vita:"+art.isInVita()+" |Artista.Data_Nascita:"+art.getDataNascita());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchArtistaService searchArtistaService = new SearchArtistaService();
                                            System.out.println("Inserisci il nome del Artista");
                                            artista.setNome(in.readLine());
                                            System.out.println("Inserisci il cognome del Artista");
                                            artista.setCognome(in.readLine());
                                            System.out.println("Inserisci true se l'Artista è in vita ");
                                            artista.setInVita(Boolean.parseBoolean(in.readLine()));
                                            System.out.println("Inserisci la data di nascita del Artista se pervenuta");
                                            artista.setDataNascita(Date.valueOf(in.readLine()));
    
                                            artList = searchArtistaService.execute(artista);
    
                                            for(Artista art : artList)
                                            {
                                                System.out.println("|Artista.CodAr:" + art.getCodAr()+"| Artista.Nome:"+art.getNome()+"| Artista.Cognome:"+art.getCognome()+" |Artista.In_Vita:"+art.isInVita()+" |Artista.Data_Nascita:"+art.getDataNascita());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 8:
                            OperaDAO operaDAO = new OperaDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Opera opera = new Opera();
                                    List<Opera> operList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci il nome dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione dell'Opera");
                                            opera.setDescrizione(in.readLine());
                                            
                                            CreateOperaService createOperaService = new CreateOperaService();
                                            createOperaService.execute(opera);

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il nome sostituitivo dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Opera");
                                            opera.setDescrizione(in.readLine());
                                            
                                            UpdateOperaService updateOperaService = new UpdateOperaService();
                                            updateOperaService.execute(opera);

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Opera  per la cancellazione");
                                            opera.setCodO(Integer.parseInt(in.readLine()));
    
                                            DeleteOperaService deleteOperaService = new DeleteOperaService();
                                            deleteOperaService.execute(opera.getCodO());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Opera per la ricerca");
                                            opera.setCodO(Integer.parseInt(in.readLine()));

                                            opera= operaDAO.getOperaById(connection, opera.getCodO());
                                            
                                            System.out.println("Opera.CodO:" + opera.getCodO()+" Opera.Nome:"+opera.getNome()+" Opera.Descrizione:"+opera.getDescrizione());
                                        break;
    
                                        case 5:
                                            GetAllOpereService getAllOpereService = new GetAllOpereService();
    
                                            operList = getAllOpereService.execute();
                                            for(Opera oper : operList)
                                            {
                                                System.out.println("|Oper.CodO:" + oper.getCodO()+"| Artista.Nome:"+oper.getNome()+"| Opera.Descrizione:"+oper.getDescrizione());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchOperaService searchOperaService = new SearchOperaService();

                                            System.out.println("Inserisci il nome dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione dell'Opera");
                                            opera.setDescrizione(in.readLine());
    
                                            operList = searchOperaService.execute(opera);
    
                                            for(Opera oper : operList)
                                            {
                                                System.out.println("|Oper.CodO:" + oper.getCodO()+"| Artista.Nome:"+oper.getNome()+"| Opera.Descrizione:"+oper.getDescrizione());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                } while (subscleta < 7);
                    
                    break;

                    case 9:
                            BiglietteriaDAO biglietteriaDAO = new BiglietteriaDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");
                                    
                                    Biglietteria biglietteria = new Biglietteria();
                                    List<Biglietteria> biglietteriaList = new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci la modalita di pagamento della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));
                                            
                                            CreateBiglietteriaService createBiglietteriaService = new CreateBiglietteriaService();
                                            createBiglietteriaService.execute(biglietteria);

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la modalita di pagamento sostituitiva della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));
                                            
                                            UpdateBiglietteriaService updateBiglietteriaService = new UpdateBiglietteriaService();
                                            updateBiglietteriaService.execute(biglietteria);

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Biglietteria per la cancellazione");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));
    
                                            DeleteBiglietteriaService deleteBiglietteriaService = new DeleteBiglietteriaService();
                                            deleteBiglietteriaService.execute(biglietteria.getCodB());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));

                                            biglietteria= biglietteriaDAO.getBiglietteriaById(connection, biglietteria.getCodB());
                                        
                                            System.out.println("Biglietteria.CodB:" + biglietteria.getCodB()+" Biglietteria.Modalità_Pagamento:"+biglietteria.getModPag()+" Biglietteria.Ora_Apertura:"+biglietteria.getOraApertura()+" Biglietteria.Ora_Chiusura:"+biglietteria.getOraChiusura());
                                        break;
    
                                        case 5:
                                            GetAllBiglietterieService getAllBiglietterieService = new GetAllBiglietterieService();
    
                                            biglietteriaList = getAllBiglietterieService.execute();
                                            for(Biglietteria bigl : biglietteriaList)
                                            {
                                                System.out.println("|Biglietteria.CodB:" + bigl.getCodB()+" |Biglietteria.Modalità_Pagamento:"+bigl.getModPag()+" |Biglietteria.Ora_Apertura:"+bigl.getOraApertura()+" |Biglietteria.Ora_Chiusura:"+bigl.getOraChiusura());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchBiglietteriaService searchBiglietteriaService = new SearchBiglietteriaService();

                                            System.out.println("Inserisci la modalita di pagamento della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));

                                            biglietteriaList = searchBiglietteriaService.execute(biglietteria);
    
                                            for(Biglietteria bigl : biglietteriaList)
                                            {
                                                System.out.println("|Biglietteria.CodB:" + bigl.getCodB()+" |Biglietteria.Modalità_Pagamento:"+bigl.getModPag()+" |Biglietteria.Ora_Apertura:"+bigl.getOraApertura()+" |Biglietteria.Ora_Chiusura:"+bigl.getOraChiusura());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 10:
                            BigliettoDAO bigliettoDAO = new BigliettoDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Biglietto biglietto = new Biglietto();
                                    List<Biglietto> biglList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci la data di acquisto del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));
                                            
                                            CreateBigliettoService createBigliettoService = new CreateBigliettoService();
                                            createBigliettoService.execute(biglietto);

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la data di acquisto sostituitiva del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo sostituitivo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo sostituitivo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));
                                            
                                            UpdateBigliettoService updateBigliettoService = new UpdateBigliettoService();
                                            updateBigliettoService.execute(biglietto);

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Biglietto ");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));
    
                                            DeleteBigliettoService deleteBigliettoService = new DeleteBigliettoService();
                                            deleteBigliettoService.execute(biglietto.getCodBi());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));

                                            biglietto= bigliettoDAO.getBigliettoById(connection, biglietto.getCodBi());
                                        
                                            System.out.println("Biglietto.CodBi:" + biglietto.getCodBi()+" Biglietto.Tipo_Biglietto:"+biglietto.getTipo()+" Biglietto.Prezzo:"+biglietto.getPrezzo()+" Biglietto.Data:"+biglietto.getData());
                                        break;
    
                                        case 5:
                                            GetAllBigliettiService getAllBigliettiService = new GetAllBigliettiService();
    
                                            biglList = getAllBigliettiService.execute();
                                            for(Biglietto bigl : biglList)
                                            {
                                                System.out.println("|Biglietto.CodBi:" + bigl.getCodBi()+" |Biglietto.Tipo_Biglietto:"+bigl.getTipo()+" |Biglietto.Prezzo:"+bigl.getPrezzo()+" |Biglietto.Data:"+bigl.getData());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchBigliettoService searchBigliettoService = new SearchBigliettoService();

                                            System.out.println("Inserisci la data di acquisto del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));

                                            biglList = searchBigliettoService.execute(biglietto);
    
                                            for(Biglietto bigl : biglList)
                                            {
                                                System.out.println("|Biglietto.CodBi:" + bigl.getCodBi()+" |Biglietto.Tipo_Biglietto:"+bigl.getTipo()+" |Biglietto.Prezzo:"+bigl.getPrezzo()+" |Biglietto.Data:"+bigl.getData());
                                            }

                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 7);
                    
                    break;

                    case 11:
                            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
                                do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete  "+ arr[scelta-1]);
                                    System.out.println("4)Get  "+ arr[scelta-1]);
                                    System.out.println("5)Get All  "+ arr[scelta-1]);
                                    System.out.println("6)Search  "+ arr[scelta-1]);
                                    System.out.println("7)Esci");

                                    Abbonamento abbonamento = new Abbonamento();
                                    List<Abbonamento> abbList= new ArrayList<>();

                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                            System.out.println("Inserisci il tipo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));
                                            
                                            CreateAbbonamentoService createAbbonamentoService = new CreateAbbonamentoService();
                                            createAbbonamentoService.execute(abbonamento);

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il tipo sostituitivo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo sostituitivo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));
                                                
                                            UpdateAbbonamentoService updateAbbonamentoService = new UpdateAbbonamentoService();
                                            updateAbbonamentoService.execute(abbonamento);

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la cancellazione");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));
    
                                            DeleteAbbonamentoService deleteAbbonamentoService = new DeleteAbbonamentoService();
                                            deleteAbbonamentoService.execute(abbonamento.getCodAb());
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la ricerca ");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));

                                            abbonamento= abbonamentoDAO.getAbbonamentoById(connection, abbonamento.getCodAb());
                                        
                                            System.out.println("Abbonamento.CodAb:" + abbonamento.getCodAb()+" Abbonamento.Tipo_Biglietto:"+abbonamento.getTipo()+" Abbonamento.Prezzo:"+abbonamento.getPrezzo());
                                        break;
    
                                        case 5:
                                            GetAllAbbonamentiService getAllAbbonamentiService = new GetAllAbbonamentiService();
    
                                            abbList = getAllAbbonamentiService.execute();
                                            for(Abbonamento abb : abbList)
                                            {
                                                System.out.println("|Abbonamento.CodAb:" + abb.getCodAb()+" |Abbonamento.Tipo_Biglietto:"+abb.getTipo()+" |Abbonamento.Prezzo:"+abb.getPrezzo());
                                            }
                                            
                                        break;
    
                                        case 6:
                                            SearchAbbonamentoService searchAbbonamentoService = new SearchAbbonamentoService();

                                            System.out.println("Inserisci il tipo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));

                                            abbList = searchAbbonamentoService.execute(abbonamento);
    
                                            for(Abbonamento abb : abbList)
                                            {
                                                System.out.println("|Abbonamento.CodAb:" + abb.getCodAb()+" |Abbonamento.Tipo_Biglietto:"+abb.getTipo()+" |Abbonamento.Prezzo:"+abb.getPrezzo());
                                            }

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

