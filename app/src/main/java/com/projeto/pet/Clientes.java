package com.projeto.pet;

import java.io.Serializable;

public class Clientes implements Serializable{
    private int id;
    private String nome;
    private String usuario;
    private String telefone;
    private String endereco;
    private String nome_animal;
    private String especie;
    private String raca;

    public Clientes(int id, String nome, String usuario, String telefone, String endereco, String nomeanimal, String especie, String raca) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nome_animal = nomeanimal;
        this.especie = especie;
        this.raca = raca;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome_animal() {
        return nome_animal;
    }

    public void setNomeanimal(String nomeanimal) {
        this.nome_animal = nomeanimal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
}
