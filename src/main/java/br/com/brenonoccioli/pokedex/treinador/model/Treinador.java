package br.com.brenonoccioli.pokedex.treinador.model;

import br.com.brenonoccioli.pokedex.pokemon.model.Pokemon;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Integer idade;

    @Deprecated
    public Treinador(){}

    public Treinador(String nome, Endereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
    }

    private static Integer calculaIdade(LocalDate dataNascimento){
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);

        int idade = periodo.getYears();

        return idade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
