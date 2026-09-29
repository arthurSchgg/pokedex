package com.br.pokedex.repository;

import com.br.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositório Spring Data JPA da entidade {@link Pokemon}.
 * <p>
 * Herda de {@link JpaRepository} os métodos de CRUD ({@code save},
 * {@code findById}, {@code findAll}, {@code delete}, ...). As consultas abaixo
 * são derivadas do nome do método pelo Spring Data.
 */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    /**
     * Busca os Pokémon cujo tipo primário é exatamente o informado.
     *
     * @param tipo tipo primário a filtrar
     * @return Pokémon do tipo informado (lista vazia se não houver)
     */
    List<Pokemon> findByTipo1(String tipo);

    /**
     * Busca Pokémon cujo nome contém o texto informado, ignorando maiúsculas e
     * minúsculas.
     *
     * @param nome trecho do nome a procurar
     * @return Pokémon encontrados (lista vazia se não houver)
     */
    List<Pokemon> findByNomeContainingIgnoreCase(String nome);
}
