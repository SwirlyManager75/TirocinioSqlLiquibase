package com.tirocinio.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Artista {

    private Integer codAr;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private boolean inVita;
    
    private Citta citta; // Riferimento all'oggetto Citta
    private List<Opera> opere = new ArrayList<>();

    public List<Opera> getOpere() {
        return opere;
    }

    public void setOpere(List<Opera> opere) {
        this.opere = opere;
    }

    public void addOpera(Opera opera)
    {
        opere.add(opera);
        opera.setArtista(this);
    }

    public void removeOpera(Opera opera)
    {
        opere.remove(opera);
        opera.setArtista(null);
    }

    // Costruttore vuoto
    public Artista() {}

    // Costruttore con parametri
    public Artista(String nome, String cognome, Date dataNascita, boolean inVita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.inVita = inVita;
        
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodAr() {
        return codAr;
    }

    public void setCodAr(Integer codAr) {
        this.codAr = codAr;
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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public boolean isInVita() {
        return inVita;
    }

    public void setInVita(boolean inVita) {
        this.inVita = inVita;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

}
