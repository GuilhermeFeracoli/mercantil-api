# Mercantil Nova Estrela API

API REST desenvolvida com **Java 17 e Spring Boot** para simular as operações de um estabelecimento comercial, incluindo gerenciamento de clientes, produtos e vendas.

O projeto foi desenvolvido com foco no aprendizado de desenvolvimento backend, aplicando arquitetura em camadas, regras de negócio, persistência de dados, validação, autenticação com JWT, testes automatizados, conteinerização e integração contínua.

## Tecnologias utilizadas

### Backend
- **Java 17** — linguagem de programação.
- **Spring Boot** — configuração e execução da aplicação.
- **Spring Web MVC** — criação dos endpoints REST.
- **Spring Data JPA** — persistência e consultas ao banco de dados.
- **Hibernate** — implementação do mapeamento objeto-relacional (ORM).
- **Spring Security** — autenticação e proteção dos endpoints.
- **JWT (JSON Web Token)** — autenticação baseada em tokens.
- **Bean Validation** — validação dos dados recebidos pela API.
- **Maven** — gerenciamento de dependências e build do projeto.

### Banco de dados
- **MySQL 8.4** — banco de dados principal da aplicação.
- **H2 Database** — banco em memória utilizado nos testes automatizados.

### Testes
- **JUnit** — estrutura para execução dos testes automatizados.
- **Mockito** — criação de mocks para testes isolados.

### DevOps e ferramentas
- **Docker** — conteinerização da aplicação.
- **Docker Compose** — execução integrada da API e do banco de dados.
- **Git** — controle de versão.
- **GitHub** — hospedagem do repositório.
- **GitHub Actions** — automação da compilação e dos testes.
- **GitHub Container Registry (GHCR)** — armazenamento e publicação da imagem Docker.

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de produtos.
- Cadastro, consulta, atualização e exclusão de clientes.
- Cadastro e consulta de vendas.
- Validação dos dados recebidos nas requisições.
- Tratamento de exceções.
- Persistência de dados com MySQL.
- Utilização de DTOs para organizar os dados de entrada e saída.
- Regras de negócio relacionadas às operações de venda e estoque.
- Autenticação com Spring Security e JWT.
- Testes automatizados com JUnit, Mockito e H2.
- Execução da aplicação e do banco de dados com Docker Compose.
- Pipeline de CI/CD com GitHub Actions.

## Arquitetura

A aplicação utiliza uma arquitetura em camadas para separar responsabilidades e facilitar a manutenção do código.

- **Controller:** recebe as requisições HTTP e retorna as respostas.
- **Service:** concentra as regras de negócio.
- **Repository:** realiza o acesso e a persistência dos dados.
- **Model:** representa as entidades do sistema.
- **DTO:** organiza os dados de entrada e saída da API.
- **Exception:** reúne o tratamento de erros.
- **Validation:** contém validações específicas.

## Como executar o projeto

### Pré-requisitos

- Git instalado.
- Docker Desktop instalado e em execução.

### 1. Clone o repositório

```bash
git clone https://github.com/GuilhermeFeracoli/mercantil-api.git
cd mercantil-api
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto:

```dotenv
DB_PASSWORD=sua_senha_local
JWT_SECRET=sua_chave_secreta
```

Substitua os valores pelos valores locais apropriados. Não compartilhe esse arquivo nem versione credenciais no GitHub.

O segredo JWT deve atender aos requisitos de tamanho e algoritmo utilizados pela aplicação.

### 3. Inicie os serviços

```bash
docker compose up -d --build
```

O Docker Compose iniciará o MySQL e a API, aguardando a disponibilidade do banco de dados antes de iniciar a aplicação.

### 4. Verifique os containers

```bash
docker compose ps
```

Para acompanhar os logs da API:

```bash
docker compose logs -f api
```

A API ficará disponível em:

`http://localhost:8080`

### 5. Encerre os serviços

```bash
docker compose down
```

Os dados do MySQL ficam armazenados em um volume persistente. O comando `docker compose down -v` também remove o volume e os dados locais.

## Executando os testes

Para executar os testes no Windows, utilizando PowerShell:

```powershell
.\mvnw.cmd --batch-mode clean verify
```

O comando compila a aplicação e executa os testes automatizados. O ambiente de testes utiliza H2, reduzindo a dependência de uma instância local do MySQL.

## Autenticação

A API utiliza Spring Security e JWT para proteger os endpoints.

### Login

**POST** `/auth/login`

Exemplo de requisição:

```json
{
  "username": "seu_usuario",
  "password": "sua_senha"
}
```

O usuário deve estar previamente configurado no banco de dados. A API não disponibiliza um endpoint de cadastro de usuários.

Após autenticar, utilize o token retornado pela aplicação no cabeçalho das requisições protegidas:

```http
Authorization: Bearer SEU_TOKEN_JWT
```

## Endpoints da API

URL base: `http://localhost:8080`

Os endpoints abaixo exigem autenticação, exceto o endpoint de login.

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos` | Lista os produtos |
| GET | `/produtos/{id}` | Consulta um produto |
| POST | `/produtos` | Cadastra um produto |
| PUT | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Exclui um produto |

Exemplo de cadastro:

```json
{
  "nome": "Arroz Tipo 1 - 5kg",
  "preco": 22.50,
  "precoConsumidor": 29.90,
  "estoque": 20.0
}
```

### Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/clientes` | Lista os clientes |
| GET | `/clientes/{id}` | Consulta um cliente |
| POST | `/clientes` | Cadastra um cliente |
| PUT | `/clientes/{id}` | Atualiza um cliente |
| DELETE | `/clientes/{id}` | Exclui um cliente |

Os dados cadastrais incluem informações pessoais e de contato. As validações de documentos dependem das regras implementadas na aplicação.

### Vendas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/vendas` | Lista as vendas |
| GET | `/vendas/{id}` | Consulta uma venda |
| POST | `/vendas` | Cadastra uma venda |

Exemplo de cadastro:

```json
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 2,
      "quantidade": 1
    }
  ]
}
```

Regras importantes:

- O cliente informado deve existir.
- Os produtos informados devem existir.
- A lista de itens não pode estar vazia.
- A quantidade de cada item deve ser positiva.

Os IDs são exemplos. Utilize registros existentes no banco de dados.

## CI/CD com GitHub Actions

O projeto utiliza GitHub Actions para automatizar o processo de build e testes.

O workflow está configurado para:

1. Executar em eventos de `push` e `pull_request`.
2. Configurar o ambiente com Java 17.
3. Compilar a aplicação e executar os testes automatizados.
4. Construir e publicar a imagem Docker no GitHub Container Registry após a conclusão bem-sucedida do build, quando há um `push` na branch `main`.

Imagem Docker:

`ghcr.io/guilhermeferacoli/mercantil-api`

A publicação automatizada facilita a distribuição da aplicação em formato de container.

## Objetivo do projeto

Este projeto faz parte da minha evolução em desenvolvimento backend com Java, com foco em APIs REST, regras de negócio, persistência de dados, autenticação, testes automatizados, Docker e automação de build e publicação.

O objetivo é aplicar conhecimentos técnicos em uma aplicação prática e evoluir continuamente por meio da implementação de novas funcionalidades e melhorias.