package br.com.brenonoccioli.pokedex.pokemon.controller;

import br.com.brenonoccioli.pokedex.pokemon.controller.dto.PokemonRequest;
import br.com.brenonoccioli.pokedex.pokemon.controller.dto.PokemonResponse;
import br.com.brenonoccioli.pokedex.pokemon.model.Pokemon;
import br.com.brenonoccioli.pokedex.pokemon.model.Tipo;
import br.com.brenonoccioli.pokedex.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokemonRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<PokemonResponse> cadastraPokemon(@RequestBody @Valid PokemonRequest request, UriComponentsBuilder uriBuilder){
        Pokemon pokemon = request.toModel(repository);
        repository.save(pokemon);

        URI uri = uriBuilder.path("/pokemon/{id}").buildAndExpand(pokemon.getId()).toUri();
        PokemonResponse response = new PokemonResponse(pokemon);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/todos")
    public ResponseEntity<Set<PokemonResponse>> listaTodos(){
        List<Pokemon> lista = repository.findAll();
        Set<PokemonResponse> listaResponse = new HashSet<>();
        lista.forEach(pokemon -> listaResponse.add(new PokemonResponse(pokemon)));
        return ResponseEntity.ok(listaResponse);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<PokemonResponse> detalhaPorNome(@PathVariable String nome){
        Optional<Pokemon> pokemonOptional = repository.findByNome(nome);

        if(pokemonOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Pokemon pokemon = pokemonOptional.get();
        PokemonResponse response = new PokemonResponse(pokemon);

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/atualiza/{nomePokemon}")
    public ResponseEntity<?> atualiza(@PathVariable String nomePokemon,
                                      @RequestParam(defaultValue = "") String novaEvolucao,
                                      @RequestParam(defaultValue = "") String novoNome,
                                      @RequestParam(defaultValue = "") String tipo){

        Optional<Pokemon> pokemonOptional = repository.findByNome(nomePokemon);
        if(pokemonOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Pokemon pokemon = pokemonOptional.get();

        if(!novaEvolucao.isBlank()){
            Optional<Pokemon> evolucao = repository.findByNome(novaEvolucao);
            if(evolucao.isEmpty()){
                return ResponseEntity.status(404).body("Evolu????o ainda n??o cadastrada");
            }
            pokemon.atualizaEvoluiPara(evolucao.get());
        }

        if(!novoNome.isBlank()){
            pokemon.atualizaNome(novoNome);
        }

        if(!tipo.toString().isBlank()){
            pokemon.getTipo().clear();
            pokemon.getTipo().add(Tipo.valueOf(tipo));
        }

        return ResponseEntity.ok(new PokemonResponse(pokemon));
    }
}
