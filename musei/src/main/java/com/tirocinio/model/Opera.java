package com.tirocinio.model;

public class Opera {

    private Integer codO;
    private String nome;
    private String descrizione;
    private Integer codEAr; // Codice dell'Artista associato all'Opera
    private Integer codEM; // Codice del Museo associato all'Opera
    private Artista artista; // Riferimento all'oggetto Artista
    private Museo museo; // Riferimento all'oggetto Museo

    // Costruttore vuoto 
    public Opera() {}

    // Costruttore con parametri
    public Opera(String nome, String descrizione, Integer codEAr, Integer codEM) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.codEAr = codEAr;
        this.codEM = codEM;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodO() {
        return codO;
    }

    public void setCodO(Integer codO) {
        this.codO = codO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getCodEAr() {
        return codEAr;
    }

    public void setCodEAr(Integer codEAr) {
        this.codEAr = codEAr;
    }

    public Integer getCodEM() {
        return codEM;
    }

    public void setCodEM(Integer codEM) {
        this.codEM = codEM;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

}
