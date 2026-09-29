package com.br.pokedex.service;

import com.br.pokedex.dto.PokemonRequestDTO;
import com.br.pokedex.dto.PokemonRequestUpdateDTO;
import com.br.pokedex.dto.PokemonResponseDTO;
import com.br.pokedex.mapper.PokemonMapper;
import com.br.pokedex.model.Pokemon;
import com.br.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonMapper mapper;

    public PokemonService(PokemonRepository repository, PokemonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PokemonResponseDTO criarPokemon(PokemonRequestDTO requestDTO){
        Pokemon pokemon = mapper.toEntity(requestDTO);
        Pokemon salvo = repository.save(pokemon);

        return mapper.toResponseDTO(salvo);
    }

    public List<PokemonResponseDTO> listar(){
        List<Pokemon> pokemons = repository.findAll();
        return mapper.toResponseDTOList(pokemons);
    }

    public PokemonResponseDTO buscarPorId(Long id){

        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));
        return mapper.toResponseDTO(pokemon);
    }

    public PokemonResponseDTO atualizar(Long id, PokemonRequestUpdateDTO requestUpdateDTO){
        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));

        mapper.updateEntity(requestUpdateDTO, pokemon);

        Pokemon pokemonAtulizado = repository.save(pokemon);

        return mapper.toResponseDTO(pokemonAtulizado);
    }

    public void remover(Long id){
        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));

        repository.delete(pokemon);
    }
}
