package com.br.pokedex.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dados de saída de um Pokémon, devolvidos pela API.
 *
 * @param id          identificador gerado pelo banco
 * @param nome        nome do Pokémon
 * @param tipo1       tipo primário
 * @param tipo2       tipo secundário (pode ser {@code null})
 * @param hp          pontos de vida
 * @param ataque      valor de ataque
 * @param defesa      valor de defesa
 * @param velocidade  valor de velocidade
 * @param imagemUrl   URL da imagem (pode ser {@code null})
 */
@Schema(description = "Pokémon retornado pela API")
public record PokemonResponseDTO (
        @Schema(
                description = "Identificador do Pokémon",
                example = "25"
        )
        Long id,

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
                description = "Tipo secundário",
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
                description = "URL da imagem",
                example = "https://exemplo.com/pikachu.png",
                nullable = true
        )
        String imagemUrl
){}