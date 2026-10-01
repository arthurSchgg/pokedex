const API_URL = "http://localhost:8181/v1/pokedex";

async function carregarPokemon(){
 
    const resposta = await fetch(`${API_URL}/listar`);

    const pokemons = await resposta.json();

    con
}