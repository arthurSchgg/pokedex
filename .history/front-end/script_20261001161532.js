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

                <p>ID: ${pokemon.id}</p>

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


//Cadastra um novo pokémon
const formulario = document.getElementById("pokemon-form");

formulario.addEventListener("submit", async (event) => {

    event.preventDefault();

    const pokemon = {

        nome: document.getElementById("nome").value,

        tipo1: document.getElementById("tipo1").value,

        tipo2: document.getElementById("tipo2").value,

        hp: Number(document.getElementById("hp").value),

        ataque: Number(document.getElementById("ataque").value),

        defesa: Number(document.getElementById("defesa").value),

        velocidade: Number(document.getElementById("velocidade").value),

        imagemUrl: document.getElementById("imagem_url").value
    }

    try {
        const resposta = await fetch(
            "http://localhost:8181/v1/pokedex/cadastrar",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(pokemon)
            }
        );

        if (!resposta.ok) {
            throw new Error("Erro ao cadastrar Pokémon");
        }

        const pokemonCadastrado = await resposta.json();

        console.log("Pokémon cadastrado: " + pokemonCadastrado);

        alert("Pokémon cadastrado com sucesso!");

        formulario.reset();
    } catch (erro) {
        console.error("Erro: ", erro);

        alert("Não foi possível cadastrar o Pokémon.")
    }
});

//Busca um pokémon pelo seu ID

const botaoBuscar = document.getElementById("buscar-pokemon");

botaoBuscar.addEventListener("click", async () => {

    const id = document.getElementById("id").value;

    if (!id) {
        alert("Digite um ID.");
        return;
    }

    try {
        const resposta = await fetch(`${API_URL}/${id}`);

        if (!resposta.ok) {
            throw new Error(`Pokémon não encontrado. Status: ${resposta.status}`);
        }

        const pokemon = await resposta.json();

        console.log("Pokémon encontrado: ", pokemon);

        const container = document.getElementById("pokemon-buscado");

        container.innerHTML = `
            <div class="card-pokemon">

                <h2>${pokemon.nome}</h2>

                <img
                    src="${pokemon.imagemUrl}"
                    alt="${pokemon.nome}"
                >

                <p>Tipo 1: ${pokemon.tipo1}</p>
                <p>Tipo 2: ${pokemon.tipo2 || "Nenhum"}</p>

                <p>HP: ${pokemon.hp}</p>
                <p>Ataque: ${pokemon.ataque}</p>
                <p>Defesa: ${pokemon.defesa}</p>
                <p>Velocidade: ${pokemon.velocidade}</p>

            </div>
        `;
    } catch (erro) {
        console.error("Erro ao buscar o Pokémon: ", erro);

        alert("Pokémon não encontrado");
    }

});


//Atualiza o pokémon pelo ID
const formularioUpdate = document.getElementById("pokemon-update-form");

formularioUpdate.addEventListener("submit", async () => {

    event.preventDefault();

    const id = document.getElementById("update-id").value;

    if(!id){
        alert("Digite o ID do pokémon");
        return;
    }

    const pokemonAtualizado = {

        nome: document.getElementById("update-nome").value,

        tipo1: document.getElementById("update-tipo1").value,

        tipo2: document.getElementById("update-tipo2").value,

        hp: Number(document.getElementById("update-hp").value),

        ataque: Number(document.getElementById("update-ataque").value),

        defesa: Number(document.getElementById("update-defesa").value),

        velocidade: Number(
            document.getElementById("update-velocidade").value
        ),

        imagemUrl: document.getElementById("update-imagem_url").value
    };

    try{
        const resposta = await fetch(`${API_URL}/${id}`, {
        method: "PUT",

        headers: {
            "Content-Type:": "application/json"
        },

        body: JSON.stringify(pokemonAtualizado)
    });
    } catch(!)
});