package com.br.pokedex.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dados de entrada para a atualização de um Pokémon.
 * <p>
 * Os valores numéricos usam {@link Integer} (wrapper) para permitir
 * {@code null}, mas o {@code PokemonMapper#updateEntity} atualmente copia todos
 * os campos, então valores numéricos ausentes precisam ser tratados antes de
 * serem repassados à entidade.
 *
 * @param nome        novo nome
 * @param tipo1       novo tipo primário
 * @param tipo2       novo tipo secundário (pode ser {@code null})
 * @param hp          novos pontos de vida
 * @param ataque      novo valor de ataque
 * @param defesa      novo valor de defesa
 * @param velocidade  novo valor de velocidade
 * @param imagemUrl   nova URL de imagem
 */
@Schema(description = "Dados para atualizar um Pokémon")
public record PokemonRequestUpdateDTO (
        @Schema(
                description = "Nome do Pokémon",
                example = "Raichu"
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
                example = "60"
        )
        Integer hp,

        @Schema(
                description = "Valor de ataque",
                example = "90"
        )
        Integer ataque,

        @Schema(
                description = "Valor de defesa",
                example = "55"
        )
        Integer defesa,

        @Schema(
                description = "Valor de velocidade",
                example = "110"
        )
        Integer velocidade,

        @Schema(
                description = "URL da imagem (opcional)",
                example = "https://exemplo.com/raichu.png",
                nullable = true
        )
        String imagemUrl
){}