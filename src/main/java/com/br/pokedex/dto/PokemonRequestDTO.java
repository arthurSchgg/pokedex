package com.br.pokedex.dto;

public record PokemonRequestDTO (
        String nome,
        String tipo1,
        String tipo2,
        int hp,
        int ataque,
        int defesa,
        int velocidade,
        String imagemUrl
){}
