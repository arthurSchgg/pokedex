package com.br.pokedex.controller;

import com.br.pokedex.dto.PokemonRequestDTO;
import com.br.pokedex.dto.PokemonRequestUpdateDTO;
import com.br.pokedex.dto.PokemonResponseDTO;
import com.br.pokedex.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller REST responsável pelos endpoints de Pokémon.
 * <p>
 * Recebe as requisições HTTP em {@code /v1/pokedex}, delega as regras ao
 * {@link PokemonService} e devolve sempre DTOs (nunca a entidade diretamente).
 */
@RestController
@RequestMapping("v1/pokedex")
public class PokemonController {

    private final PokemonService service;

    /**
     * Cria o controller com suas dependências.
     *
     * @param service serviço com as regras de negócio de Pokémon
     */
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    /**
     * Lista todos os Pokémon cadastrados.
     *
     * @return {@code 200 OK} com a lista de Pokémon (vazia se não houver nenhum)
     */
    @Operation(
            summary = "Listar Pokémon",
            description = "Retorna todos os Pokémon cadastrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PokemonResponseDTO.class)))
    )
    @GetMapping("/listar")
    public ResponseEntity<List<PokemonResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Cadastra um novo Pokémon.
     *
     * @param requestDTO dados do Pokémon a ser criado
     * @return {@code 201 Created} com o Pokémon salvo e o cabeçalho {@code Location}
     *         apontando para o novo recurso
     */
    @Operation(
            summary = "Cadastrar Pokémon",
            description = "Cria um novo Pokémon e devolve o registro salvo, já com o id gerado."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Pokémon criado com sucesso",
            content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados da requisição inválidos",
            content = @Content)
    @PostMapping("/cadastrar")
    public ResponseEntity<PokemonResponseDTO> cadastrarPokemon(@Valid @RequestBody PokemonRequestDTO requestDTO) {
        PokemonResponseDTO responseDTO = service.criarPokemon(requestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    /**
     * Busca um Pokémon pelo identificador.
     *
     * @param id identificador do Pokémon
     * @return {@code 200 OK} com o Pokémon encontrado
     */
    @Operation(
            summary = "Buscar Pokémon por id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Pokémon encontrado",
            content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Pokémon não encontrado", content = @Content
    )
    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    /**
     * Atualiza os dados de um Pokémon existente.
     *
     * @param id                identificador do Pokémon a atualizar
     * @param requestUpdateDTO  novos dados do Pokémon
     * @return {@code 200 OK} com o Pokémon atualizado
     */
    @Operation(
            summary = "Atualizar Pokémon",
            description = "Substitui os dados do Pokémon informado pelo id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Pokémon atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados da requisição inválidos",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Pokémon não encontrado",
            content = @Content
    )
    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PokemonRequestUpdateDTO requestUpdateDTO) {
        return ResponseEntity.ok(service.atualizar(id, requestUpdateDTO));
    }

    /**
     * Remove um Pokémon.
     *
     * @param id identificador do Pokémon a remover
     * @return {@code 204 No Content} quando a remoção é concluída
     */
    @Operation(
            summary = "Excluir Pokémon"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Pokémon removido com sucesso",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Pokémon não encontrado",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPokemon(@PathVariable Long id) {
        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}
