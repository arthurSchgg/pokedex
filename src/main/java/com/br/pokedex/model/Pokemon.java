package com.br.pokedex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Entidade JPA que representa um Pokémon na tabela {@code pokemon}.
 * <p>
 * Getters, setters, construtores, builder, {@code equals}/{@code hashCode} e
 * {@code toString} são gerados pelo Lombok.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Pokemon {

    /** Identificador único, gerado automaticamente pelo banco (auto incremento). */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /** Nome do Pokémon. */
    private String nome;

    /** Tipo primário. */
    private String tipo1;

    /** Tipo secundário; {@code null} quando o Pokémon tem apenas um tipo. */
    private String tipo2;

    /** Pontos de vida (HP). */
    private int hp;

    /** Valor de ataque. */
    private int ataque;

    /** Valor de defesa. */
    private int defesa;

    /** Valor de velocidade. */
    private int velocidade;

    /** URL da imagem do Pokémon. */
    private String imagemUrl;
}
