package com.tirocinio.model;

public class Cliente_Abbonamento {

    private Integer codPA;
    private Integer codECli; // Codice del Cliente associato alla relazione
    private Integer codEA; // Codice dell'Abbonamento associato alla relazione
    private Cliente cliente; // Riferimento all'oggetto Cliente
    private Abbonamento abbonamento; // Riferimento all'oggetto Abbonamento

    // Costruttore vuoto 
    public Cliente_Abbonamento() {}

    // Costruttore con parametri
    public Cliente_Abbonamento(Integer codECli, Integer codEA) {
        this.codECli = codECli;
        this.codEA = codEA;
    }

    // Metodi getter e setter per ogni attributo
    public Integer getCodPA() {
        return codPA;
    }

    public void setCodPA(Integer codPA) {
        this.codPA = codPA;
    }

    public Integer getCodECli() {
        return codECli;
    }

    public void setCodECli(Integer codECli) {
        this.codECli = codECli;
    }

    public Integer getCodEA() {
        return codEA;
    }

    public void setCodEA(Integer codEA) {
        this.codEA = codEA;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }


}
