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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.tirocinio.exceptions.ServiceException;
import com.tirocinio.connection.ConnectionManager;
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
import com.tirocinio.service.Delete.DeleteAbbonamentoBiglietterieService;
import com.tirocinio.service.Delete.DeleteAbbonamentoService;
import com.tirocinio.service.Delete.DeleteArtistaService;
import com.tirocinio.service.Delete.DeleteAudioService;
import com.tirocinio.service.Delete.DeleteBiglietteriaService;
import com.tirocinio.service.Delete.DeleteBigliettoService;
import com.tirocinio.service.Delete.DeleteCityService;
import com.tirocinio.service.Delete.DeleteClienteAbbonamentoService;
import com.tirocinio.service.Delete.DeleteClienteService;
import com.tirocinio.service.Delete.DeleteDipendenteService;
import com.tirocinio.service.Delete.DeleteMuseumService;
import com.tirocinio.service.Delete.DeleteOperaService;
import com.tirocinio.service.Delete.DeletePoiService;
import com.tirocinio.service.GetAll.GetAllAbbonamentiService;
import com.tirocinio.service.GetAll.GetAllArtistiService;
import com.tirocinio.service.GetAll.GetAllAudiosService;
import com.tirocinio.service.GetAll.GetAllBiglietterieService;
import com.tirocinio.service.GetAll.GetAllBigliettiService;
import com.tirocinio.service.GetAll.GetAllCitiesService;
import com.tirocinio.service.GetAll.GetAllClientiService;
import com.tirocinio.service.GetAll.GetAllDipendentiService;
import com.tirocinio.service.GetAll.GetAllMuseumsService;
import com.tirocinio.service.GetAll.GetAllOpereService;
import com.tirocinio.service.GetAll.GetAllPoisService;
import com.tirocinio.service.GetById.GetByIdAbbonamentoService;
import com.tirocinio.service.GetById.GetByIdArtistiService;
import com.tirocinio.service.GetById.GetByIdAudiosService;
import com.tirocinio.service.GetById.GetByIdBiglietterieService;
import com.tirocinio.service.GetById.GetByIdBigliettoService;
import com.tirocinio.service.GetById.GetByIdCityService;
import com.tirocinio.service.GetById.GetByIdClienteService;
import com.tirocinio.service.GetById.GetByIdDipendenteService;
import com.tirocinio.service.GetById.GetByIdMuseoService;
import com.tirocinio.service.GetById.GetByIdOperaService;
import com.tirocinio.service.GetById.GetByIdPoiService;
import com.tirocinio.service.Insert.CreateAbbonamentoService;
import com.tirocinio.service.Insert.CreateAbbonamentoToBigliettoService;
import com.tirocinio.service.Insert.CreateArtistaService;
import com.tirocinio.service.Insert.CreateAudioService;
import com.tirocinio.service.Insert.CreateBiglietteriaService;
import com.tirocinio.service.Insert.CreateBigliettoService;
import com.tirocinio.service.Insert.CreateCityService;
import com.tirocinio.service.Insert.CreateClienteService;
import com.tirocinio.service.Insert.CreateClienteToAbbonamentoService;
import com.tirocinio.service.Insert.CreateDipendenteService;
import com.tirocinio.service.Insert.CreateMuseumService;
import com.tirocinio.service.Insert.CreateOperaService;
import com.tirocinio.service.Insert.CreatePoiService;
import com.tirocinio.service.Search.SearchAbbonamentiForBiglietteriaService;
import com.tirocinio.service.Search.SearchAbbonamentiForClientiService;
import com.tirocinio.service.Search.SearchAbbonamentoService;
import com.tirocinio.service.Search.SearchArtistaService;
import com.tirocinio.service.Search.SearchAudioService;
import com.tirocinio.service.Search.SearchBiglietteriaService;
import com.tirocinio.service.Search.SearchBiglietterieForAbbonamentoService;
import com.tirocinio.service.Search.SearchBigliettoService;
import com.tirocinio.service.Search.SearchCityService;
import com.tirocinio.service.Search.SearchClienteService;
import com.tirocinio.service.Search.SearchClientiForAbbonamento;
import com.tirocinio.service.Search.SearchDipendenteService;
import com.tirocinio.service.Search.SearchMuseumService;
import com.tirocinio.service.Search.SearchOperaService;
import com.tirocinio.service.Search.SearchPoiService;
import com.tirocinio.service.Update.UpdateAbbonamentoService;
import com.tirocinio.service.Update.UpdateArtistaService;
import com.tirocinio.service.Update.UpdateAudioService;
import com.tirocinio.service.Update.UpdateBiglietteriaService;
import com.tirocinio.service.Update.UpdateBigliettoService;
import com.tirocinio.service.Update.UpdateCityService;
import com.tirocinio.service.Update.UpdateAbbonamentoBiglietterieService;
import com.tirocinio.service.Update.UpdateAbbonamentoForClienteService;
import com.tirocinio.service.Update.UpdateClienteService;
import com.tirocinio.service.Update.UpdateDipendenteService;
import com.tirocinio.service.Update.UpdateMuseumService;
import com.tirocinio.service.Update.UpdateOperaService;
import com.tirocinio.service.Update.UpdatePoiService;

public class Main 
{
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException 
    {
        //TODO GESTIRE LE EXCEPTION, INTEGRARE GESTIONE LOG4J 
        //Inizializzo la connessione
        System.out.println(System.getProperty("user.dir"));
        Logger logger=LogManager.getLogger(Main.class);
        Connection connection=null;
        try
        {
        connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        }
        catch(SQLException e)
        {
            logger.error("Impossibile stabilire una connession con il database");
        } 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int scelta=0;
        int subscleta=0;
        String arr[] = {"Audio","POI","Museo","Citta","Cliente","Dipendente","Artista","Opere","Biglietteria","Biglietto","Abbonamento","Abbonamento_Biglietteria","Cliente_Abbonamento"};

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
            System.out.println("12)Abbonamento_Biglietteria");
            System.out.println("13)Cliente_Abbonamento");
            System.out.println("14)Esci");

            scelta= Integer.parseInt(in.readLine());
            subscleta=0;
            switch (scelta) 
            {
    
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

                                Audio audio = new Audio();
                                List<Audio> audioList = new ArrayList<>();
                                

                                subscleta= Integer.parseInt(in.readLine());
                                switch (subscleta) {
                                    case 1:
                                            
                                        System.out.println("Inserisci la URL dell'audio");
                                        audio.setUrl(in.readLine());

                                        CreateAudioService cr= new CreateAudioService();

                                        try {
                                            cr.execute(audio);
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    break;

                                    case 2:

                                        System.out.println("Inserisci la URL modificata");
                                        audio.setUrl(in.readLine());
                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        UpdateAudioService up= new UpdateAudioService();
                                        try {
                                            up.execute(audio);
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    break;

                                    case 3:

                                        System.out.println("Inserisci il codice dell'audio per la cancellazione");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        DeleteAudioService dl= new DeleteAudioService();
                                        try {
                                            dl.execute(audio.getCodAu());
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice dell'audio per la ricerca");
                                        audio.setCodAu(Integer.parseInt(in.readLine()));

                                        try {
                                            GetByIdAudiosService audiosService = new GetByIdAudiosService();
                                            audiosService.execute(audio.getCodAu());
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        System.out.println("Audio.URL: " + audio.getUrl());
                                    break;

                                    case 5:
                                        GetAllAudiosService allAudiosService = new GetAllAudiosService();
                                        
                                        try {
                                            audioList=allAudiosService.execute();
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Audio aud : audioList)
                                        {
                                            System.out.println("Audio.Cod_Au:"+aud.getCodAu()+"  Audio.Url:"+aud.getUrl());
                                        }
                                    break;

                                    case 6:
                                        SearchAudioService searchAudioService = new SearchAudioService();
                                        
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la URL)");
                                        audio.setUrl(in.readLine());
                                        try {
                                            audioList= searchAudioService.execute(audio);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

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

                                        try {
                                            createPoiService.execute(poi);
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 2:
                                        System.out.println("Inserisci la Descrizione del POI modificata");
                                        poi.setDescrizione(in.readLine());
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        UpdatePoiService updatePoiService = new UpdatePoiService();
                                        try {
                                            updatePoiService.execute(poi);
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del POI per la cancellazione");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        DeletePoiService deletePoiService = new DeletePoiService();
                                        try {
                                            deletePoiService.execute(poi.getCodPoi());
                                        } catch ( ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del POI per la ricerca");
                                        poi.setCodPoi(Integer.parseInt(in.readLine()));

                                        try {
                                            GetByIdPoiService getByIdPoiService= new GetByIdPoiService();
                                            getByIdPoiService.execute(poi.getCodPoi());
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 5:
                                        GetAllPoisService getAllPoisService = new GetAllPoisService();

                                        try {
                                            poiList = getAllPoisService.execute();
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        for(Poi poi2 : poiList)
                                        {
                                            System.out.println("Poi.CodPoi:"+poi2.getCodPoi()+"  Poi.Descrizione:"+poi2.getDescrizione());
                                        }
                                        
                                    break;

                                    case 6:
                                        SearchPoiService searchPoiService = new SearchPoiService();
                                        System.out.println("Inserisci la stringa che funge da criterio (si cerca l'occorrenza della stringa dentro la Descrizione)");

                                        poi.setDescrizione(in.readLine());

                                        try {
                                            poiList= searchPoiService.execute(poi);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

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
                                        try {
                                            createMuseumService.execute(museo);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 2:
                                        System.out.println("Inserisci il nome sostituitivo del Museo ");
                                        museo.setNome(in.readLine());
                                        System.out.println("Inserisci la via sostituitiva del Museo se ne sei a conoscenza");
                                        museo.setVia(in.readLine());

                                        UpdateMuseumService updateMuseumService = new UpdateMuseumService();
                                        try {
                                            updateMuseumService.execute(museo);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 3:
                                        System.out.println("Inserisci il codice del Museo per la cancellazione");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        DeleteMuseumService deleteMuseumService = new DeleteMuseumService();
                                        try {
                                            deleteMuseumService.execute(museo.getCodM());
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    break;

                                    case 4:
                                        System.out.println("Inserisci il codice del Museo per la ricerca");
                                        museo.setCodM(Integer.parseInt(in.readLine()));

                                        try {
                                            GetByIdMuseoService getByIdMuseoService = new GetByIdMuseoService();
                                            getByIdMuseoService.execute(museo.getCodM());
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        System.out.println("Museo.CodM:" + museo.getCodM()+"Museo.Nome:"+museo.getNome()+"Museo.Via:"+museo.getVia());
                                    break;

                                    case 5:
                                        GetAllMuseumsService getAllMuseumsService = new GetAllMuseumsService();

                                        try {
                                            museoList = getAllMuseumsService.execute();
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

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

                                        try {
                                            museoList = searchMuseumService.execute(museo);
                                        } catch (ServiceException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

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
                                            try {
                                                createCityService.execute(citta);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 2:
                                            System.out.println("Inserisci il nome sostituitivo della Città");
                                            citta.setNome(in.readLine());
                                            System.out.println("Inserisci true se la Città è una provincia");
                                            citta.setProvincia(Boolean.parseBoolean(in.readLine())); //inserire true nella stringa

                                            UpdateCityService updateCityService = new UpdateCityService();
                                            try {
                                                updateCityService.execute(citta);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Città per la cancellazione");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            DeleteCityService deleteCityService = new DeleteCityService();
                                            try {
                                                deleteCityService.execute(citta.getCodCi());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Città per la ricerca");
                                            citta.setCodCi(Integer.parseInt(in.readLine()));
    
                                            try {
                                                GetByIdCityService getByIdCityService = new GetByIdCityService();
                                                getByIdCityService.execute(citta.getCodCi());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            } //TODO CREARE SERVICE
                                            System.out.println("Citta.CodCi:" + citta.getCodCi()+"Citta.Nome:"+citta.getNome()+"Citta.Provincia:"+citta.isProvincia());
                                        break;
    
                                        case 5:
                                            GetAllCitiesService getAllCitiesService = new GetAllCitiesService();
    
                                            try {
                                                cittaList = getAllCitiesService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
    
                                            try {
                                                cittaList = searchCityService.execute(citta);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createClienteService.execute(cliente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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
                                            try {
                                                updateClienteService.execute(cliente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Cliente per la cancellazione");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));
    
                                            DeleteClienteService deleteClienteService = new DeleteClienteService();
                                            try {
                                                deleteClienteService.execute(cliente.getCodCli());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Cliente per la ricerca");
                                            cliente.setCodCli(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdClienteService getByIdClienteService = new GetByIdClienteService();
                                                getByIdClienteService.execute(cliente.getCodCli());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Cliente.CodCli:" + cliente.getCodCli()+" Cliente.Nome:"+cliente.getNome()+" Cliente.Cognome:"+cliente.getCognome()+"Cliente.Cellulare:"+cliente.getCellulare()+"Cliente.Mail:"+cliente.getMail());
                                        break;
    
                                        case 5:
                                            GetAllClientiService getAllClientiService = new GetAllClientiService();
    
                                            try {
                                                clientList = getAllClientiService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
    
                                            try {
                                                clientList = searchClienteService.execute(cliente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createDipendenteService.execute(dipendente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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
                                            try {
                                                updetaDipendenteService.execute(dipendente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Dipendente per la cancellazione");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));
    
                                            DeleteDipendenteService deleteDipendenteService = new DeleteDipendenteService();
                                            try {
                                                deleteDipendenteService.execute(dipendente.getCodD());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Dipendente");
                                            dipendente.setCodD(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdDipendenteService getByIdDipendenteService = new GetByIdDipendenteService();
                                                getByIdDipendenteService.execute(dipendente.getCodD());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Dipedente.CodCli:" + dipendente.getCodD()+" Dipedente.Nome:"+dipendente.getNome()+" Dipedente.CodiceFiscale:"+dipendente.getCodiceFiscale()+"Dipedente.Cellulare:"+dipendente.getCellulare()+"Dipedente.Data_Di_Nascita:"+dipendente.getDataNascita());

                                        break;
    
                                        case 5:
                                            GetAllDipendentiService getAllDipendentiService = new GetAllDipendentiService();
    
                                            try {
                                                dipList = getAllDipendentiService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
    
                                            try {
                                                dipList = searchDipendenteService.execute(dipendente);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createArtistaService.execute(artista);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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
                                            try {
                                                updateArtistaService.execute(artista);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Artista per la cancellazione");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));
    
                                            DeleteArtistaService deleteArtistaService = new DeleteArtistaService();
                                            try {
                                                deleteArtistaService.execute(artista.getCodAr());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice del Artista per la ricerca");
                                            artista.setCodAr(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdArtistiService getByIdArtistiService = new GetByIdArtistiService();
                                                getByIdArtistiService.execute(artista.getCodAr());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Artista.CodAr:" + artista.getCodAr()+" Artista.Nome:"+artista.getNome()+" Artista.Cognome:"+artista.getCognome()+"Artista.In_Vita:"+artista.isInVita()+"Artista.Data_Nascita:"+artista.getDataNascita());
                                        break;
    
                                        case 5:
                                            GetAllArtistiService getAllArtistiService = new GetAllArtistiService();
    
                                            try {
                                                artList = getAllArtistiService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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
    
                                            try {
                                                artList = searchArtistaService.execute(artista);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createOperaService.execute(opera);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il nome sostituitivo dell'Opera");
                                            opera.setNome(in.readLine());
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Opera");
                                            opera.setDescrizione(in.readLine());
                                            
                                            UpdateOperaService updateOperaService = new UpdateOperaService();
                                            try {
                                                updateOperaService.execute(opera);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Opera  per la cancellazione");
                                            opera.setCodO(Integer.parseInt(in.readLine()));
    
                                            DeleteOperaService deleteOperaService = new DeleteOperaService();
                                            try {
                                                deleteOperaService.execute(opera.getCodO());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Opera per la ricerca");
                                            opera.setCodO(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdOperaService getByIdOperaService = new GetByIdOperaService();
                                                getByIdOperaService.execute(opera.getCodO());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                            System.out.println("Opera.CodO:" + opera.getCodO()+" Opera.Nome:"+opera.getNome()+" Opera.Descrizione:"+opera.getDescrizione());
                                        break;
    
                                        case 5:
                                            GetAllOpereService getAllOpereService = new GetAllOpereService();
    
                                            try {
                                                operList = getAllOpereService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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
    
                                            try {
                                                operList = searchOperaService.execute(opera);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createBiglietteriaService.execute(biglietteria);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la modalita di pagamento sostituitiva della Biglietteria");
                                            biglietteria.setModPag(Biglietteria.ModalitaPagamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di apertura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraApertura(Time.valueOf(in.readLine()));
                                            System.out.println("Inserisci l'orario di chiusura sostituitiva della Biglietteria in hh-mm-ss");
                                            biglietteria.setOraChiusura(Time.valueOf(in.readLine()));
                                            
                                            UpdateBiglietteriaService updateBiglietteriaService = new UpdateBiglietteriaService();
                                            try {
                                                updateBiglietteriaService.execute(biglietteria);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice della Biglietteria per la cancellazione");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));
    
                                            DeleteBiglietteriaService deleteBiglietteriaService = new DeleteBiglietteriaService();
                                            try {
                                                deleteBiglietteriaService.execute(biglietteria.getCodB());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietteria.setCodB(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdBiglietterieService getByIdBiglietterieService = new GetByIdBiglietterieService();
                                                getByIdBiglietterieService.execute(biglietteria.getCodB());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        
                                            System.out.println("Biglietteria.CodB:" + biglietteria.getCodB()+" Biglietteria.Modalità_Pagamento:"+biglietteria.getModPag()+" Biglietteria.Ora_Apertura:"+biglietteria.getOraApertura()+" Biglietteria.Ora_Chiusura:"+biglietteria.getOraChiusura());
                                        break;
    
                                        case 5:
                                            GetAllBiglietterieService getAllBiglietterieService = new GetAllBiglietterieService();
    
                                            try {
                                                biglietteriaList = getAllBiglietterieService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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

                                            try {
                                                biglietteriaList = searchBiglietteriaService.execute(biglietteria);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createBigliettoService.execute(biglietto);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci la data di acquisto sostituitiva del Biglietto");
                                            biglietto.setData(Date.valueOf(in.readLine()));
                                            System.out.println("Inserisci Il prezzo sostituitivo del biglietto");
                                            biglietto.setPrezzo(Float.parseFloat(in.readLine()));
                                            System.out.println("Inserisci il tipo sostituitivo del biglietto "); //inserire i tipi disponibili per ricordare
                                            biglietto.setTipo(Biglietto.TipoBiglietto.valueOf(in.readLine()));
                                            
                                            UpdateBigliettoService updateBigliettoService = new UpdateBigliettoService();
                                            try {
                                                updateBigliettoService.execute(biglietto);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice del Biglietto ");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));
    
                                            DeleteBigliettoService deleteBigliettoService = new DeleteBigliettoService();
                                            try {
                                                deleteBigliettoService.execute(biglietto.getCodBi());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice della Biglietteria per la ricerca");
                                            biglietto.setCodBi(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdBigliettoService getByIdBigliettoService = new GetByIdBigliettoService();
                                                getByIdBigliettoService.execute(biglietto.getCodBi());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        
                                            System.out.println("Biglietto.CodBi:" + biglietto.getCodBi()+" Biglietto.Tipo_Biglietto:"+biglietto.getTipo()+" Biglietto.Prezzo:"+biglietto.getPrezzo()+" Biglietto.Data:"+biglietto.getData());
                                        break;
    
                                        case 5:
                                            GetAllBigliettiService getAllBigliettiService = new GetAllBigliettiService();
    
                                            try {
                                                biglList = getAllBigliettiService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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

                                            try {
                                                biglList = searchBigliettoService.execute(biglietto);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                                            try {
                                                createAbbonamentoService.execute(abbonamento);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                            System.out.println("Inserisci il tipo sostituitivo dell'Abbonamento");
                                            abbonamento.setTipo(Abbonamento.TipoAbbonamento.valueOf(in.readLine()));
                                            System.out.println("Inserisci la descrizione sostituitiva dell'Abbonamento");
                                            abbonamento.setDescrizione(in.readLine());
                                            System.out.println("Inserisci il prezzo sostituitivo dell'Abbonamento "); //inserire i tipi disponibili per ricordare
                                            abbonamento.setPrezzo(Float.parseFloat(in.readLine()));
                                                
                                            UpdateAbbonamentoService updateAbbonamentoService = new UpdateAbbonamentoService();
                                            try {
                                                updateAbbonamentoService.execute(abbonamento);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la cancellazione");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));
    
                                            DeleteAbbonamentoService deleteAbbonamentoService = new DeleteAbbonamentoService();
                                            try {
                                                deleteAbbonamentoService.execute(abbonamento.getCodAb());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        break;
    
                                        case 4:
                                            System.out.println("Inserisci il codice dell'Abbonamento per la ricerca ");
                                            abbonamento.setCodAb(Integer.parseInt(in.readLine()));

                                            try {
                                                GetByIdAbbonamentoService getByIdAbbonamentoService = new GetByIdAbbonamentoService();
                                                getByIdAbbonamentoService.execute(abbonamento.getCodAb());
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        
                                            System.out.println("Abbonamento.CodAb:" + abbonamento.getCodAb()+" Abbonamento.Tipo_Biglietto:"+abbonamento.getTipo()+" Abbonamento.Prezzo:"+abbonamento.getPrezzo());
                                        break;
    
                                        case 5:
                                            GetAllAbbonamentiService getAllAbbonamentiService = new GetAllAbbonamentiService();
    
                                            try {
                                                abbList = getAllAbbonamentiService.execute();
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
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

                                            try {
                                                abbList = searchAbbonamentoService.execute(abbonamento);
                                            } catch (ServiceException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
    
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
                    {   
                        int Cod_Ab,Cod_Bi;
                        do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete   "+ arr[scelta-1]);
                                    System.out.println("4)Get All Biglietterie per Abbonamento  ");
                                    System.out.println("5)Get All Abbonamenti per Biglietteria ");
                                    System.out.println("6)Esci  ");

                                    
                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:
                                            System.out.println("Inserisci il codice abbonamento da associare");
                                            Cod_Ab = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria da associare");
                                            Cod_Bi = Integer.parseInt(in.readLine());

                                            CreateAbbonamentoToBigliettoService createAbbonamentoToBigliettoService = new CreateAbbonamentoToBigliettoService();
                                            
                                            try 
                                            {
                                                createAbbonamentoToBigliettoService.execute(null);
                                            } 
                                            catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                        System.out.println("Inserisci il codice abbonamento da modificare");
                                            Cod_Ab = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria da modificare");
                                            Cod_Bi = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice abbonamento di rimpiazzo");
                                            int Cod_Abmod = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria di rimpiazzo");
                                            int Cod_Bimod = Integer.parseInt(in.readLine());

                                            UpdateAbbonamentoBiglietterieService updateAbbonamentoBiglietterieService = new UpdateAbbonamentoBiglietterieService();
                                            
                                            try 
                                            {
                                                updateAbbonamentoBiglietterieService.execute(null);
                                            } 
                                            catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:

                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                            Cod_Ab = Integer.parseInt(in.readLine());
                                            System.out.println("Inserisci il codice della biglietteria da cancellare");
                                            Cod_Bi = Integer.parseInt(in.readLine());

                                            DeleteAbbonamentoBiglietterieService deleteAbbonamentoBiglietterieService = new DeleteAbbonamentoBiglietterieService();
                                            
                                            try 
                                            {
                                                deleteAbbonamentoBiglietterieService.execute(null);
                                            } 
                                            catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                        break;
    
                                        case 4:

                                        
                                        System.out.println("Inserisci il codice della biglietteria");
                                        Cod_Bi = Integer.parseInt(in.readLine());

                                        SearchAbbonamentiForBiglietteriaService searchAbbonamentiForBiglietteriaService = new SearchAbbonamentiForBiglietteriaService();
                                        
                                        try 
                                        {
                                            searchAbbonamentiForBiglietteriaService.execute(null);
                                        } 
                                        catch (ServiceException e) 
                                        {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                            
                                        break;
    
                                        case 5:

                                        System.out.println("Inserisci il codice della biglietteria");
                                        Cod_Bi = Integer.parseInt(in.readLine());

                                        SearchBiglietterieForAbbonamentoService searchBiglietterieForAbbonamentoService = new SearchBiglietterieForAbbonamentoService();
                                        
                                        try 
                                        {
                                            searchBiglietterieForAbbonamentoService.execute(null);
                                        } 
                                        catch (ServiceException e) 
                                        {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                             
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 6);
                    }

                    case 13:
                    {
                        //TODO inserire le map come input
                        int Cod_Cli,Cod_Ab;
                        do 
                                {
                                    System.out.println("1)Insert  "+ arr[scelta-1]);
                                    System.out.println("2)Update  "+ arr[scelta-1]);
                                    System.out.println("3)Delete   "+ arr[scelta-1]);
                                    System.out.println("4)Get All Clienti per Abbonamento  ");
                                    System.out.println("5)Get All Abbonamenti per Cliente ");
                                    System.out.println("6)Esci  ");

                                    
                                    subscleta= Integer.parseInt(in.readLine());
                                    switch (subscleta) 
                                    {
                                        case 1:

                                        System.out.println("Inserisci il codice abbonamento da associare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente da associare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                        CreateClienteToAbbonamentoService cService = new CreateClienteToAbbonamentoService();

                                            try 
                                            {
                                                cService.execute(null);
                                            } catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 2:

                                        System.out.println("Inserisci il codice abbonamento da modificare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il nuovo codice abbonamento da associare");
                                        int Cod_AbNuovo = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                        UpdateAbbonamentoForClienteService cService2 = new UpdateAbbonamentoForClienteService();

                                            try 
                                            {
                                                cService2.execute(null);
                                            } catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 3:
                                            
                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                        Cod_Ab = Integer.parseInt(in.readLine());
                                        System.out.println("Inserisci il codice del cliente da cancellare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                        DeleteClienteAbbonamentoService cService3 = new DeleteClienteAbbonamentoService();

                                            try 
                                            {
                                                cService3.execute(null);
                                            } catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                        break;
    
                                        case 4:

                                        System.out.println("Inserisci il codice abbonamento da cancellare");
                                        Cod_Ab = Integer.parseInt(in.readLine());

                                        SearchAbbonamentiForClientiService cService4 = new SearchAbbonamentiForClientiService();

                                            try 
                                            {
                                                cService4.execute(null);
                                            } catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            
                                        break;
    
                                        case 5:

                                        
                                        System.out.println("Inserisci il codice del cliente da cancellare");
                                        Cod_Cli = Integer.parseInt(in.readLine());

                                        SearchClientiForAbbonamento cService5 = new SearchClientiForAbbonamento();

                                            try 
                                            {
                                                cService5.execute(null);
                                            } catch (ServiceException e) 
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                             
                                        break;
                                    
                                        default:
    
                                        break;
                                    }
                                    
                                } while (subscleta < 6);
                    }

                    case 14:
                                System.out.println("Quitting");
                    break;

                    default:
                    System.out.println("Scelta non valida");
                    break;
            }

        }while(scelta <14);
    }
}

