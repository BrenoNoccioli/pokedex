package br.com.brenonoccioli.pokedex.treinador.controller.dto;

import br.com.brenonoccioli.pokedex.treinador.model.Treinador;

public class TreinadorResponse {

    private String nome;

    private Integer idade;

    public TreinadorResponse(Treinador treinador){
        this.nome = treinador.getNome();
        this.idade = treinador.getIdade();
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }
}
