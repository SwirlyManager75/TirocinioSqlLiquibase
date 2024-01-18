package com.tirocinio.model;

public class Citta {

    private Integer codCi;
    private String nome;
    private boolean provincia;

    // Costruttore vuoto 
    public Citta() {}

    // Costruttore con parametri
    public Citta(String nome, boolean provincia) {
        this.nome = nome;
        this.provincia = provincia;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodCi() {
        return codCi;
    }

    public void setCodCi(Integer codCi) {
        this.codCi = codCi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isProvincia() {
        return provincia;
    }

    public void setProvincia(boolean provincia) {
        this.provincia = provincia;
    }

}
