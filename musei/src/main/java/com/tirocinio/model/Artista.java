package com.tirocinio.model;

import java.sql.Date;

public class Artista {

    private Integer codAr;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private boolean inVita;
    private Integer codECi; // Codice della Città associata all'Artista
    private Citta citta; // Riferimento all'oggetto Citta

    // Costruttore vuoto
    public Artista() {}

    // Costruttore con parametri
    public Artista(String nome, String cognome, Date dataNascita, boolean inVita, Integer codECi) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.inVita = inVita;
        this.codECi = codECi;
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

    public Integer getCodECi() {
        return codECi;
    }

    public void setCodECi(Integer codECi) {
        this.codECi = codECi;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

}
