package com.example.gwt;

public class Cliente {

    int codigo;
    String nome;
    String email;
    String telefone;
    String end;
    String cpf;

    public Cliente(){}

    public Cliente(int _codigo, String _nome,  String _email, String _telefone, String _end, String _cpf){
        this.codigo = _codigo;
        this.nome = _nome;
        this.email = _email;
        this.telefone = _telefone;
        this.end = _end;
        this.cpf = _cpf;
    }

    public Cliente(String _nome,  String _email, String _telefone, String _end, String _cpf){
        this.nome = _nome;
        this.email = _email;
        this.telefone = _telefone;
        this.end = _end;
        this.cpf = _cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
