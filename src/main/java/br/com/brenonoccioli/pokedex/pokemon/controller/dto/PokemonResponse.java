package br.com.brenonoccioli.pokedex.pokemon.controller.dto;

import br.com.brenonoccioli.pokedex.pokemon.model.Pokemon;
import br.com.brenonoccioli.pokedex.pokemon.model.Tipo;

import java.util.Set;

public class PokemonResponse {

    private String nome;

    private Set<Tipo> tipo;

    private PokemonResponse evoluiPara;

    public PokemonResponse(Pokemon pokemon){
        nome = pokemon.getNome();
        tipo = pokemon.getTipo();

        //No momento não encontrei forma melhor retornar o response da evolução
        //sem entrar em loop infinito ou nullPointer
        if(pokemon.getEvoluiPara() != null){
            evoluiPara = adicionaEvolução(new PokemonResponse(pokemon.getEvoluiPara()));
        }
    }

    public String getNome() {
        return nome;
    }

    public Set<Tipo> getTipo() {
        return tipo;
    }

    public PokemonResponse getEvoluiPara() {
        return evoluiPara;
    }

    public PokemonResponse adicionaEvolução(PokemonResponse evoluiPara) {
        return this.evoluiPara = evoluiPara;
    }
}
