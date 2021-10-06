package br.com.brenonoccioli.pokedex.treinador.repository;

import br.com.brenonoccioli.pokedex.treinador.model.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
}
