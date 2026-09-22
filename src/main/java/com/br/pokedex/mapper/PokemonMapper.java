package com.br.pokedex.mapper;

import com.br.pokedex.dto.PokemonRequestDTO;
import com.br.pokedex.dto.PokemonRequestUpdateDTO;
import com.br.pokedex.dto.PokemonResponseDTO;
import com.br.pokedex.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonMapper {

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

    public List<PokemonResponseDTO> toResponseDTOList(List<Pokemon> pokemons) {
        return pokemons.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void updateEntity(PokemonRequestUpdateDTO dto, Pokemon pokemon) {
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
