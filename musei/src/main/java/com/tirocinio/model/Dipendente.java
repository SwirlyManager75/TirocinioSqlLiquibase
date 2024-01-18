package com.tirocinio.model;

import java.sql.Date;

public class Dipendente {

    private Integer codD;
    private String nome;
    private Date dataNascita;
    private String codiceFiscale;
    private String cellulare;
    private Integer codECi; // Codice della Città associata al Dipendente
    private Integer codEM; // Codice del Museo associato al Dipendente
    private Citta citta; // Riferimento all'oggetto Citta
    private Museo museo; // Riferimento all'oggetto Museo

    // Costruttore vuoto 
    public Dipendente() {}

    // Costruttore con parametri
    public Dipendente(String nome, Date dataNascita, String codiceFiscale, String cellulare, Integer codECi, Integer codEM) {
        this.nome = nome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
        this.codECi = codECi;
        this.codEM = codEM;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodD() {
        return codD;
    }

    public void setCodD(Integer codD) {
        this.codD = codD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public Integer getCodECi() {
        return codECi;
    }

    public void setCodECi(Integer codECi) {
        this.codECi = codECi;
    }

    public Integer getCodEM() {
        return codEM;
    }

    public void setCodEM(Integer codEM) {
        this.codEM = codEM;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }
    
}
