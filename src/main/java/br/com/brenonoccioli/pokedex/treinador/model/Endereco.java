package br.com.brenonoccioli.pokedex.treinador.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Positive;

@Embeddable
public class Endereco {
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    @Positive
    private Integer numero;

    @Column(nullable = false)
    private String cep;


    @Deprecated
    public Endereco(){}

    public Endereco(String logradouro, Integer numero, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
