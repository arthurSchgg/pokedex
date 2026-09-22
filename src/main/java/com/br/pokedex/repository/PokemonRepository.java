package com.br.pokedex.repository;

import com.br.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByTipo1(String tipo);

    List<Pokemon> findeByNomeContainingIgnoreCase(String nome);
}
