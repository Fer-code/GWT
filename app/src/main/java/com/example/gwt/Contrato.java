package com.example.gwt;

public class Contrato {

    int codCon;
    String nomeCon;
    String descCon;
    Double valorCon;
    String DICon;
    String DFCon;
    String ServCon;
    int fkCli;
    int fkSocio;

    public Contrato(){}


    public Contrato(int _cod, String _nome,  String _desc, Double _valor, String _DI, String _DF, String _servCon, int _fkCli/*, int _fkSocio*/){
        this.codCon = _cod;
        this.nomeCon = _nome;
        this.descCon = _desc;
        this.valorCon = _valor;
        this.DICon = _DI;
        this.DFCon = _DF;
        this.ServCon = _servCon;
        this.fkCli = _fkCli;
        //this.fkSocio = _fkSocio;
    }


    public Contrato( String _nome,  String _desc, Double _valor, String _DI, String _DF, String _servCon, int _fkCli/*, int _fkSocio*/){
        this.nomeCon = _nome;
        this.descCon = _desc;
        this.valorCon = _valor;
        this.DICon = _DI;
        this.DFCon = _DF;
        this.ServCon = _servCon;
        this.fkCli = _fkCli;
        //this.fkCli = _fkSocio;
    }

    public int getCodCon() {
        return codCon;
    }

    public void setCodCon(int codCon) {
        this.codCon = codCon;
    }

    public String getDescCon() {
        return descCon;
    }

    public void setDescCon(String descCon) {
        this.descCon = descCon;
    }

    public String getDFCon() {
        return DFCon;
    }

    public void setDFCon(String DFCon) {
        this.DFCon = DFCon;
    }

    public String getNomeCon() {
        return nomeCon;
    }

    public void setNomeCon(String nomeCon) {
        this.nomeCon = nomeCon;
    }

    public Double getValorCon() {
        return valorCon;
    }

    public void setValorCon(Double valorCon) {
        this.valorCon = valorCon;
    }

    public String getDICon() {
        return DICon;
    }

    public void setDICon(String DICon) {
        this.DICon = DICon;
    }

    public int getFkCli() {
        return fkCli;
    }

    public void setFkCli(int fkCli) {
        this.fkCli = fkCli;
    }

    public String getServCon() {
        return ServCon;
    }

    public void setServCon(String servCon) {
        ServCon = servCon;
    }

    public int getFkSocio() {
        return fkSocio;
    }

    public void setFkSocio(int fkSocio) {
        this.fkSocio = fkSocio;
    }
}
