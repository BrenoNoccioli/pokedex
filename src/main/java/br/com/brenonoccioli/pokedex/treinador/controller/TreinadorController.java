package br.com.brenonoccioli.pokedex.treinador.controller;

import br.com.brenonoccioli.pokedex.treinador.controller.dto.TreinadorRequest;
import br.com.brenonoccioli.pokedex.treinador.controller.dto.TreinadorResponse;
import br.com.brenonoccioli.pokedex.treinador.model.Treinador;
import br.com.brenonoccioli.pokedex.treinador.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/treinador")
public class TreinadorController {

    @Autowired
    TreinadorRepository repository;

    @PostMapping("/cadastra")
    public ResponseEntity<TreinadorResponse> cadastraTreinador(@RequestBody @Valid TreinadorRequest request,
                                                               UriComponentsBuilder uriBuilder){
        Treinador treinador = request.toModel();
        repository.save(treinador);

        URI uri = uriBuilder.path("/treinador/id").buildAndExpand(treinador.getId()).toUri();
        TreinadorResponse response = new TreinadorResponse(treinador);
        return ResponseEntity.created(uri).body(response);
    }

    @Transactional
    @PutMapping("/atualiza/{id}")
    public ResponseEntity<?> atualizaTreinador(@PathVariable Long id,
                                               @RequestParam(defaultValue = "") String nome,
                                               @RequestParam(defaultValue = "") String logradouro,
                                               @RequestParam(defaultValue = "") String cep,
                                               @RequestParam(defaultValue = "") LocalDate dataNascimento){

        Optional<Treinador> treinadorOptional = repository.findById(id);
        if(treinadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Treinador treinador = treinadorOptional.get();

        if(!nome.isBlank()){
            treinador.setNome(nome);
        }

        if(!logradouro.isBlank()){
            treinador.getEndereco().setLogradouro(logradouro);
        }

        if(!cep.isBlank()){
            treinador.getEndereco().setCep(cep);
        }

        if(!dataNascimento.toString().equals("")){
            treinador.setDataNascimento(dataNascimento);
        }

        return ResponseEntity.ok(new TreinadorResponse(treinador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaTreinador(@PathVariable Long id){
        Optional<Treinador> treinadorOptional = repository.findById(id);
        if(treinadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Treinador treinador = treinadorOptional.get();
        TreinadorResponse response = new TreinadorResponse(treinador);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<?> deletaTreinador(@PathVariable Long id){
        Optional<Treinador> treinadorOptional = repository.findById(id);
        if(treinadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Treinador treinador = treinadorOptional.get();
        repository.delete(treinador);

        return ResponseEntity.ok().build();
    }

}
