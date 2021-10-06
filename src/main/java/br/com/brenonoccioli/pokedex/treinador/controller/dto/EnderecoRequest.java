package br.com.brenonoccioli.pokedex.treinador.controller.dto;

import br.com.brenonoccioli.pokedex.treinador.model.Endereco;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class EnderecoRequest {

    @NotBlank
    private String logradouro;

    @Positive
    private Integer numero;

    @NotBlank
    private String cep;

    public EnderecoRequest(String logradouro, Integer numero, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public Endereco toModel() {
        return new Endereco(logradouro, numero, cep);
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
}
