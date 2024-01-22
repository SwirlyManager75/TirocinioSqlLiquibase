package com.tirocinio.model;

public class Cliente {

    private Integer codCli;
    private String nome;
    private String cognome;
    private String mail;
    private String cellulare;
    private Integer codECi; // Codice della Città associata al Cliente
    private Citta citta; // Riferimento all'oggetto Citta

    // Costruttore vuoto 
    public Cliente() {}

    // Costruttore con parametri
    public Cliente(String nome, String cognome, String mail, String cellulare, Integer codECi) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.cellulare = cellulare;
        this.codECi = codECi;
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
