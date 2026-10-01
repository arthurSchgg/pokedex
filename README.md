# Pokédex

Aplicação web para cadastrar, consultar, atualizar e excluir Pokémon. O back-end é uma API REST em **Spring Boot** e o front-end é feito com **HTML, CSS e JavaScript puro**, servido pela própria aplicação.

## Funcionalidades

- Listar todos os Pokémon cadastrados
- Cadastrar um novo Pokémon
- Buscar um Pokémon pelo ID
- Atualizar os dados de um Pokémon
- Excluir um Pokémon
- Documentação interativa da API com Swagger

## Tecnologias

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1.1 (Spring Web, Spring Data JPA, Validation) |
| Banco de dados | H2 (em arquivo) |
| Documentação | springdoc-openapi (Swagger UI) |
| Front-end | HTML, CSS e JavaScript |
| Build | Maven (Maven Wrapper incluído) |

## Estrutura do projeto

```
pokedex/
├── Dockerfile
├── pom.xml
└── src/main/
    ├── java/com/br/pokedex/
    │   ├── controller/     # Endpoints REST
    │   ├── service/        # Regras de negócio
    │   ├── repository/     # Acesso ao banco (Spring Data JPA)
    │   ├── mapper/         # Conversão entre entidade e DTO
    │   ├── dto/            # Objetos de entrada e saída da API
    │   └── model/          # Entidade Pokemon
    └── resources/
        ├── application.properties
        ├── data.sql        # Pokémon de exemplo
        └── static/         # Front-end (index.html, script.js, style.css)
```

## Como rodar localmente

**Pré-requisitos:** Java 21 instalado. O Maven não precisa estar instalado, pois o projeto inclui o Maven Wrapper.

```bash
# 1. Clone o repositório
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
cd SEU-REPOSITORIO

# 2. Rode a aplicação
./mvnw spring-boot:run        # Linux / macOS
mvnw.cmd spring-boot:run      # Windows
```

Depois acesse:

| O quê | Endereço |
|---|---|
| Aplicação (front-end) | http://localhost:8181 |
| Swagger UI | http://localhost:8181/swagger-ui.html |
| API | http://localhost:8181/v1/pokedex/listar |

### Variáveis de ambiente

| Variável | Padrão | Descrição |
|---|---|---|
| `PORT` | `8181` | Porta do servidor (o Render define automaticamente) |
| `H2_CONSOLE` | `false` | Use `true` para habilitar o console do H2 em `/h2-console` |

## Endpoints da API

Base: `/v1/pokedex`

| Método | Rota | Descrição | Resposta |
|---|---|---|---|
| `GET` | `/listar` | Lista todos os Pokémon | `200` |
| `POST` | `/cadastrar` | Cadastra um Pokémon | `201` |
| `GET` | `/{id}` | Busca um Pokémon pelo ID | `200` |
| `PUT` | `/{id}` | Atualiza um Pokémon | `200` |
| `DELETE` | `/{id}` | Exclui um Pokémon | `204` |

### Exemplo de corpo (POST e PUT)

```json
{
  "nome": "Pikachu",
  "tipo1": "Elétrico",
  "tipo2": null,
  "hp": 35,
  "ataque": 55,
  "defesa": 40,
  "velocidade": 90,
  "imagemUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
}
```

| Campo | Tipo | Observação |
|---|---|---|
| `nome` | texto | Nome do Pokémon |
| `tipo1` | texto | Tipo primário |
| `tipo2` | texto | Tipo secundário (opcional) |
| `hp`, `ataque`, `defesa`, `velocidade` | número | Atributos do Pokémon |
| `imagemUrl` | texto | Link da imagem (veja a seção abaixo) |

---

## 🖼️ Como pegar a URL da imagem de um Pokémon

Ao cadastrar um Pokémon, o campo **URL da imagem** aceita qualquer link direto para uma imagem. Uma ótima fonte são os sprites do projeto **[PokeAPI/sprites](https://github.com/PokeAPI/sprites)**, um repositório público com as imagens de todos os Pokémon.

### Passo a passo

1. Descubra o **número do Pokémon na Pokédex nacional** (Bulbasaur é 1, Charizard é 6, Pikachu é 25 e assim por diante).
2. Monte a URL usando esse número no lugar de `{numero}`:

```
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{numero}.png
```

3. Cole o link no campo **URL da imagem** do formulário.

Para conferir se o link está certo, abra-o no navegador: a imagem do Pokémon deve aparecer sozinha na tela.

### Exemplos

| Pokémon | Nº | URL |
|---|---|---|
| Bulbasaur | 1 | `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png` |
| Charizard | 6 | `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png` |
| Blastoise | 9 | `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png` |
| Pikachu | 25 | `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png` |
| Gengar | 94 | `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png` |

### Outros estilos de imagem

Navegando pelo [repositório](https://github.com/PokeAPI/sprites), na pasta `sprites/pokemon/`, você encontra outros estilos além da arte oficial. Por exemplo, o sprite em pixel art fica em:

```
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{numero}.png
```

Você também pode abrir qualquer imagem no GitHub, clicar com o botão direito sobre ela e escolher **Copiar endereço da imagem**.

## Banco de dados

O projeto usa o **H2 em arquivo** (`./data/pokedex`), criado automaticamente na primeira execução. Localmente, os dados persistem entre os reinícios.

Para guardar dados de forma permanente, seria necessário trocar o H2 por um banco externo (por exemplo, PostgreSQL) e ajustar as propriedades `spring.datasource.*`.

## Créditos

- Imagens: [PokeAPI/sprites](https://github.com/PokeAPI/sprites)
- Desenvolvido por [@arthurSchgg](https://github.com/arthurSchgg)
