package com.br.pokedex.dto;

public record PokemonRequestUpdateDTO (
        String nome,
        String tipo1,
        String tipo2,
        Integer hp,
        Integer ataque,
        Integer defesa,
        Integer velocidade,
        String imagemUrl
){}
