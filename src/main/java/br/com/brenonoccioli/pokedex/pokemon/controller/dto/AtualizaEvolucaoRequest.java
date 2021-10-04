package br.com.brenonoccioli.pokedex.pokemon.controller.dto;

import javax.validation.constraints.NotBlank;

public class AtualizaEvolucaoRequest {
    @NotBlank
    private String nome;

    //apenas para serialização do JSON
    public AtualizaEvolucaoRequest(){
    }

    public String getNome() {
        return nome;
    }
}
