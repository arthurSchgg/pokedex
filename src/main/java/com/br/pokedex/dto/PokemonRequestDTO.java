package com.br.pokedex.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dados de entrada para o cadastro de um Pokémon.
 *
 * @param nome        nome do Pokémon
 * @param tipo1       tipo primário
 * @param tipo2       tipo secundário (opcional, pode ser {@code null})
 * @param hp          pontos de vida
 * @param ataque      valor de ataque
 * @param defesa      valor de defesa
 * @param velocidade  valor de velocidade
 * @param imagemUrl   URL da imagem do Pokémon (opcional)
 */
@Schema(description = "Dados para cadastrar um Pokémon")
public record PokemonRequestDTO (
        @Schema(
                description = "Nome do Pokémon",
                example = "Pikachu"
        )
        String nome,

        @Schema(
                description = "Tipo primário",
                example = "Elétrico"
        )
        String tipo1,

        @Schema(
                description = "Tipo secundário (opcional)",
                example = "Voador",
                nullable = true
        )
        String tipo2,

        @Schema(
                description = "Pontos de vida",
                example = "35"
        )
        int hp,

        @Schema(
                description = "Valor de ataque",
                example = "55"
        )
        int ataque,
        @Schema(
                description = "Valor de defesa",
                example = "40"
        )
        int defesa,

        @Schema(
                description = "Valor de velocidade",
                example = "90"
        )
        int velocidade,

        @Schema(
                description = "URL da imagem (opcional)",
                example = "https://exemplo.com/pikachu.png",
                nullable = true
        )
        String imagemUrl
){}