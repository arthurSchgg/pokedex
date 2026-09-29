package com.br.pokedex.service;

import com.br.pokedex.dto.PokemonRequestDTO;
import com.br.pokedex.dto.PokemonRequestUpdateDTO;
import com.br.pokedex.dto.PokemonResponseDTO;
import com.br.pokedex.mapper.PokemonMapper;
import com.br.pokedex.model.Pokemon;
import com.br.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço com as regras de negócio de Pokémon.
 * <p>
 * Orquestra o {@link PokemonRepository} (persistência) e o {@link PokemonMapper}
 * (conversão entre entidade e DTO).
 */
@Service
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonMapper mapper;

    /**
     * Cria o serviço com suas dependências.
     *
     * @param repository repositório de persistência de Pokémon
     * @param mapper     conversor entre entidade e DTOs
     */
    public PokemonService(PokemonRepository repository, PokemonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Cria e persiste um novo Pokémon.
     *
     * @param requestDTO dados do Pokémon a cadastrar
     * @return DTO do Pokémon salvo, já com o id gerado
     */
    public PokemonResponseDTO criarPokemon(PokemonRequestDTO requestDTO){
        Pokemon pokemon = mapper.toEntity(requestDTO);
        Pokemon salvo = repository.save(pokemon);

        return mapper.toResponseDTO(salvo);
    }

    /**
     * Lista todos os Pokémon cadastrados.
     *
     * @return lista de DTOs (vazia se não houver registros)
     */
    public List<PokemonResponseDTO> listar(){
        List<Pokemon> pokemons = repository.findAll();
        return mapper.toResponseDTOList(pokemons);
    }

    /**
     * Busca um Pokémon pelo id.
     *
     * @param id identificador do Pokémon
     * @return DTO do Pokémon encontrado
     * @throws RuntimeException se não existir Pokémon com o id informado
     */
    public PokemonResponseDTO buscarPorId(Long id){

        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));
        return mapper.toResponseDTO(pokemon);
    }

    /**
     * Atualiza os dados de um Pokémon existente.
     *
     * @param id                identificador do Pokémon a atualizar
     * @param requestUpdateDTO  novos dados do Pokémon
     * @return DTO do Pokémon atualizado
     * @throws RuntimeException se não existir Pokémon com o id informado
     */
    public PokemonResponseDTO atualizar(Long id, PokemonRequestUpdateDTO requestUpdateDTO){
        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));

        mapper.updateEntity(requestUpdateDTO, pokemon);

        Pokemon pokemonAtulizado = repository.save(pokemon);

        return mapper.toResponseDTO(pokemonAtulizado);
    }

    /**
     * Remove um Pokémon.
     *
     * @param id identificador do Pokémon a remover
     * @throws RuntimeException se não existir Pokémon com o id informado
     */
    public void remover(Long id){
        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado com id: " + id));

        repository.delete(pokemon);
    }
}
