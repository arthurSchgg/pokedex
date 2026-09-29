package com.br.pokedex.mapper;

import org.springframework.stereotype.Component;

/**
 * Converte entre a entidade {@link Pokemon} e seus DTOs.
 * <p>
 * Mantém a entidade isolada da camada web: o controller só trabalha com DTOs.
 */

@Component
public class PokemonMapper {

    /**
     * Converte uma entidade em DTO de resposta.
     *
     * @param pokemon entidade a converter
     * @return DTO com os dados do Pokémon, incluindo o id
     */
    public PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        return new PokemonResponseDTO(
                pokemon.getId(),
                pokemon.getNome(),
                pokemon.getTipo1(),
                pokemon.getTipo2(),
                pokemon.getHp(),
                pokemon.getAtaque(),
                pokemon.getDefesa(),
                pokemon.getVelocidade(),
                pokemon.getImagemUrl()
        );
    }

    /**
     * Converte o DTO de cadastro em uma nova entidade (sem id).
     *
     * @param dto dados recebidos na requisição de cadastro
     * @return nova entidade pronta para ser persistida
     */
    public Pokemon toEntity(PokemonRequestDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setNome(dto.nome());
        pokemon.setTipo1(dto.tipo1());
        pokemon.setTipo2(dto.tipo2());
        pokemon.setHp(dto.hp());
        pokemon.setAtaque(dto.ataque());
        pokemon.setDefesa(dto.defesa());
        pokemon.setVelocidade(dto.velocidade());
        pokemon.setImagemUrl(dto.imagemUrl());
        return pokemon;
    }

    /**
     * Converte uma lista de entidades em uma lista de DTOs de resposta.
     *
     * @param pokemons entidades a converter
     * @return lista imutável de DTOs, na mesma ordem da entrada
     */
    public List<PokemonResponseDTO> toResponseDTOList(List<Pokemon> pokemons) {
        return pokemons.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    /**
     * Copia os dados do DTO de atualização para uma entidade já existente.
     * <p>
     * Todos os campos são sobrescritos. Atenção: como {@code hp}, {@code ataque},
     * {@code defesa} e {@code velocidade} chegam como {@link Integer} e a entidade
     * usa {@code int}, um valor {@code null} causa {@link NullPointerException}.
     *
     * @param dto     novos dados recebidos na requisição
     * @param pokemon entidade que será modificada (alterada in-place)
     */
    public void updateEntity(PokemonRequestDTO dto, Pokemon pokemon) {
        pokemon.setNome(dto.nome());
        pokemon.setTipo1(dto.tipo1());
        pokemon.setTipo2(dto.tipo2());
        pokemon.setHp(dto.hp());
        pokemon.setAtaque(dto.ataque());
        pokemon.setDefesa(dto.defesa());
        pokemon.setVelocidade(dto.velocidade());
        pokemon.setImagemUrl(dto.imagemUrl());
    }
}
