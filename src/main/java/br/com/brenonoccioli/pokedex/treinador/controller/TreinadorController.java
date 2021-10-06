package br.com.brenonoccioli.pokedex.treinador.controller;

import br.com.brenonoccioli.pokedex.treinador.controller.dto.TreinadorRequest;
import br.com.brenonoccioli.pokedex.treinador.controller.dto.TreinadorResponse;
import br.com.brenonoccioli.pokedex.treinador.model.Treinador;
import br.com.brenonoccioli.pokedex.treinador.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/treinador")
public class TreinadorController {

    @Autowired
    TreinadorRepository repository;

    @PostMapping("/cadastra")
    public ResponseEntity<TreinadorResponse> cadastraTreinador(@RequestBody @Valid TreinadorRequest request, UriComponentsBuilder uriBuilder){
        Treinador treinador = request.toModel();
        repository.save(treinador);

        URI uri = uriBuilder.path("/treinador/id").buildAndExpand(treinador.getId()).toUri();
        TreinadorResponse response = new TreinadorResponse(treinador);
        return ResponseEntity.created(uri).body(response);
    }

}
