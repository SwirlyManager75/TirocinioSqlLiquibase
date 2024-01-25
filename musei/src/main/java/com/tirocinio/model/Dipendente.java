package com.tirocinio.model;

import java.sql.Date;

public class Dipendente {

    private Integer codD;
    private String nome;
    private Date dataNascita;
    private String codiceFiscale;
    private String cellulare;
    
    private Citta citta; // Riferimento all'oggetto Citta
    private Museo museo; // Riferimento all'oggetto Museo

    // Costruttore vuoto 
    public Dipendente() {}

    // Costruttore con parametri
    public Dipendente(String nome, Date dataNascita, String codiceFiscale, String cellulare) {
        this.nome = nome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
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
