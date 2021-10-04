package br.com.brenonoccioli.pokedex.pokemon.controller.dto;

import br.com.brenonoccioli.pokedex.annotations.IdExists;
import br.com.brenonoccioli.pokedex.pokemon.model.Pokemon;
import br.com.brenonoccioli.pokedex.pokemon.model.Tipo;
import br.com.brenonoccioli.pokedex.pokemon.repository.PokemonRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

public class PokemonRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Set<Tipo> tipo;

    @IdExists(domainClass = Pokemon.class, field = "id")
    private Long idEvolucao;

    public PokemonRequest(String nome, Set<Tipo> tipo, Long idEvolucao) {
        this.nome = nome;
        this.tipo = tipo;
        this.idEvolucao = idEvolucao;
    }

    public Pokemon toModel(PokemonRepository repository){
        //Caso não haja registro de evolução
        if(idEvolucao == null){
            return new Pokemon (nome, tipo);
        }

        Optional<Pokemon> evolucaoOptional = repository.findById(idEvolucao);
        Pokemon evolucao = evolucaoOptional.get();
        return new Pokemon(nome, tipo, evolucao);
    }

    public String getNome() {
        return nome;
    }

    public Set<Tipo> getTipo() {
        return tipo;
    }

    public Long getIdEvolucao() {
        return idEvolucao;
    }
}
