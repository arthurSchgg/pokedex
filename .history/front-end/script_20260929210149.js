const API_URL = "http://localhost:8181/v1/pokedex";

//Lista os pokemons
async function carregarPokemon() {
    try {
        const resposta = await fetch(`${API_URL}/listar`);

        if (!resposta.ok) {
            throw new Error(`Erro no servidor: ${resposta.status}`);
        }

        const pokemons = await resposta.json();

        console.log("Pokémons carregados:", pokemons);

        const container = document.getElementById("container-pokemon");

        pokemons.forEach(pokemon => {

            const card = document.createElement("div");

            card.innerHTML = `
                <h2>${pokemon.nome}</h2>

                <img src="${pokemon.imagemUrl}" alt="${pokemon.nome}">

                <p>Tipo: ${pokemon.tipo1}</p>

                <p>HP: ${pokemon.hp}</p>

                <p>Ataque: ${pokemon.ataque}</p>

                <p>Defesa: ${pokemon.defesa}</p>

                <p>Velocidade: ${pokemon.velocidade}</p>
            
            `;

            container.appendChild(card);
        });

    } catch (erro) {
        console.error("Erro ao buscar pokémons na linha do fetch:", erro);
    }
}

carregarPokemon();

const formulario = document.getElementById("pokemon-form");

formulario.addEventListener("submit", async (event) => {

    event.preventDefault();


    const pokemon = {

        nome: document.getElementById("nome").value,

        tipo1: document.getElementById("tipo1").value,

        tipo2: document.getElementById("tipo2")
    }
});