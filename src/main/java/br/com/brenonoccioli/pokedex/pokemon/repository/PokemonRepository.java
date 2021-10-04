package br.com.brenonoccioli.pokedex.pokemon.repository;

import br.com.brenonoccioli.pokedex.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByNome(String nome);
}
