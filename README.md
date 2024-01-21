</p>
<h1 align="center">PipocaFlix</h1>
O aplicativo PipocaFlix oferece aos usuários uma maneira fácil e conveniente de explorar o mundo do cinema. Ele utiliza dados da API do “The Movie Database”, uma base de dados de filmes amplamente reconhecida e respeitada, para criar uma lista abrangente de filmes.

Ao abrir o PipocaFlix, os usuários são recebidos com uma interface de usuário intuitiva e atraente. A página inicial apresenta uma variedade de categorias de filmes para escolher, incluindo os mais recentes lançamentos, os mais populares, e uma seleção de filmes clássicos. Cada filme na lista vem com uma imagem de capa, uma breve sinopse, a classificação do filme.

Além disso, o app também oferece recursos de personalização. Os usuários podem criar listas de filmes favoritos.

## Screenshots

Sem DarkMode  | Com DarkMode 
------------- | -------------
<img src="https://github.com/Rblemer/PipocaFlix/assets/81179721/de75d34e-fe48-4943-90ea-4c0fae88c4af"/> <img src="https://github.com/Rblemer/PipocaFlix/assets/81179721/6e9f4be4-b2f3-43a7-a27b-69f3a9f7ff62"/>|<img src="https://github.com/Rblemer/PipocaFlix/assets/81179721/2155761f-9195-499d-9ef6-db802d60e202"/> <img src="https://github.com/Rblemer/PipocaFlix/assets/81179721/c49e2e6b-eabf-45e9-9cbf-814a785ec1a8"/>  


## Tecnologias utilizadas:

- Material Design 3- Componentes e tema da aplicação
- Hilt - Injeção de dependência
- Coil - Carregamento de imagens
- Arquitetura MVVM + Clean Architecture
- Jetpack Compose - Desenvolvimento da UI
- Navigation - Para navegação entre as telas
- Kotlin KTS - Gerenciamento de dependências
- Suporte ao "Modo Escuro"
- Modularização da aplicação em "app", "domain" e "data"
- LottieAnimation - Para adicionar arquivos animados
- SharedPreferences - Armazenar dados primitivos privados em pares key-value.

## Architecture

MVVM (Model View View-Model) + Clean Architecture

## API

PipocaFlix App usa a [TheMovieDb(Tmdb) Api](https://developers.themoviedb.org/3) para construir a API RESTful.

## Pontos de melhoria
- Adicionar os contentsDescriptions para melhoria da Acessibilidade. 
- Criar a internacionalização das strings do app.
- Adicionar mais teste.
