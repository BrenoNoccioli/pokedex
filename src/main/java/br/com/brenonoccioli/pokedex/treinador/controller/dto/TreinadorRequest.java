package br.com.brenonoccioli.pokedex.treinador.controller.dto;

import br.com.brenonoccioli.pokedex.treinador.model.Treinador;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class TreinadorRequest {

    @NotBlank
    private String nome;

    @NotNull
    private EnderecoRequest endereco;

    @Past
    private LocalDate dataNascimento;

    public TreinadorRequest(String nome, EnderecoRequest endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public Treinador toModel() {
        return new Treinador(nome, endereco.toModel(), dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
