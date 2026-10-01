const API_URL = "http://localhost:8181/v1/pokedex";

async function carregarPokemon() {
    try {
        const resposta = await fetch(`${API_URL}/listar`);

        if (!resposta.ok) {
            throw new Error(`Erro no servidor: ${resposta.status}`);
        }

        const pokemons = await resposta.json();

        console.log("Pokémons carregados:", pokemons);

        const container = document.getElementById("pokemon-container");

        pokemons.forEach(pokemon => {

            const card = document.createElement
        });

    } catch (erro) {
        console.error("Erro ao buscar pokémons na linha do fetch:", erro);
    }
}

carregarPokemon();
