package com.tirocinio.model;

public class Museo {

    private Integer codM;
    private String nome;
    private String via;
    private Integer codECi; // Codice della città a cui il museo appartiene
    private Citta citta; // Riferimento all'oggetto Citta

    // Costruttore vuoto
    public Museo() {}

    // Costruttore con parametri
    public Museo(String nome, String via, Integer codECi) {
        this.nome = nome;
        this.via = via;
        this.codECi = codECi;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodM() {
        return codM;
    }

    public void setCodM(Integer codM) {
        this.codM = codM;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
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
