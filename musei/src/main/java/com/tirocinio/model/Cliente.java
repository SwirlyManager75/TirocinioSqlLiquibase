package com.tirocinio.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private Integer codCli;
    private String nome;
    private String cognome;
    private String mail;
    private String cellulare;
    
    private Citta citta; // Riferimento all'oggetto Citta
    private List<Biglietto> biglietti= new ArrayList<>();
    private List<Abbonamento> abbonamenti= new ArrayList<>();
    

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    // Costruttore vuoto 
    public Cliente() {}

    // Costruttore con parametri
    public Cliente(String nome, String cognome, String mail, String cellulare) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.cellulare = cellulare;
    }

    public Cliente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodCli() {
        return codCli;
    }

    public void setCodCli(Integer codCli) {
        this.codCli = codCli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }
    
    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public void addBiglietto(Biglietto biglietto)
    {
        biglietti.add(biglietto);
        biglietto.setCliente(this);
    }

    public void removeArtista(Biglietto biglietto)
    {
        biglietti.remove(biglietto);
        biglietto.setCliente(null);
    }

}
