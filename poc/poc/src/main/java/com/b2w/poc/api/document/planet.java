package com.b2w.poc.api.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document
public class planet {
    @Id
    private String id;
    private String nome;
    private String clima;
    private String terreno;
    private Integer nrFilms;

    public planet(String id, String nome, String clima, String terreno, Integer nrFilms) {
        this.id = id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
        this.nrFilms = nrFilms;
    }
    public planet( String nome, String clima, String terreno, Integer nrFilms) {
        this(null,nome,clima,terreno, nrFilms);
    }
    public planet() {
        this.id = null;
        this.nome = null;
        this.clima = null;
        this.terreno = null;
        this.nrFilms = null;
    }

    public Integer getNrFilms() {
        return nrFilms;
    }

    public void setNrFilms(Integer nrFilms) {
        this.nrFilms = nrFilms;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @NotEmpty(message = "O Clima não pode estar vazio")
    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    @NotEmpty(message = "O Tipo de Terreno não pode ser vazio")
    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    @NotEmpty(message = "O Nome não pode ser vazio")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
