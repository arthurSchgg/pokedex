package com.br.pokedex.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.pokedex.dto.PokemonRequestDTO;
import com.br.pokedex.dto.PokemonRequestUpdateDTO;
import com.br.pokedex.dto.PokemonResponseDTO;
import com.br.pokedex.service.PokemonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/pokedex")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PokemonResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PokemonResponseDTO> cadastrarPokemon(@Valid @RequestBody PokemonRequestDTO requestDTO){
        PokemonResponseDTO responseDTO = service.criarPokemon(requestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PokemonRequestUpdateDTO requestUpdateDTO){
        return ResponseEntity.ok(service.atualizar(id, requestUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPokemon(@PathVariable Long id){
        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}
