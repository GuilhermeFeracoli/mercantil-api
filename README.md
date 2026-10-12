# Mercantil Nova Estrela API

API REST para gerenciamento de produtos, clientes e vendas, desenvolvida com Java e Spring Boot. O projeto simula operações de um estabelecimento comercial e aplica conceitos de desenvolvimento backend, persistência de dados, validação e testes automatizados.

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA e Hibernate
* MySQL 8.4
* Maven
* Bean Validation
* JUnit e Mockito
* H2 para testes
* Docker e Docker Compose
* Git e GitHub Actions

## Funcionalidades

* Cadastro, consulta, atualização e exclusão de produtos.
* Cadastro, consulta, atualização e exclusão de clientes.
* Cadastro e consulta de vendas.
* Validação de dados recebidos pela API.
* Tratamento de exceções.
* Persistência de dados com MySQL.
* Uso de DTOs para organizar requisições e respostas.
* Testes automatizados de regras de estoque.
* Integração contínua com GitHub Actions para compilação e testes.

## Arquitetura

O projeto utiliza uma organização em camadas para separar responsabilidades:

* **Controller:** recebe as requisições HTTP.
* **Service:** concentra as regras de negócio.
* **Repository:** acessa os dados persistidos.
* **Model:** representa as entidades do sistema.
* **DTO:** organiza os dados de entrada e saída.
* **Exception:** reúne classes relacionadas ao tratamento de erros.
* **Validation:** contém validações específicas.

## Pré-requisitos

Para executar a aplicação com Docker:

* Docker Desktop instalado e em execução.
* Git.

Para executar os testes localmente, utilize também o Java 17.

## Como executar com Docker Compose

### 1. Clone o repositório

```bash
git clone https://github.com/GuilhermeFeracoli/mercantil-api.git
cd mercantil-api
```

### 2. Configure a variável de ambiente

Crie um arquivo `.env` na raiz do projeto com a seguinte variável:

```dotenv
DB_PASSWORD=sua_senha_local
```

Substitua `sua_senha_local` por uma senha de sua escolha. Não compartilhe esse arquivo nem envie credenciais para o GitHub.

### 3. Inicie os serviços

```bash
docker compose up -d --build
```

O Docker Compose iniciará o MySQL e a API, aguardando a disponibilidade do banco antes de iniciar a aplicação.

### 4. Verifique os serviços

```bash
docker compose ps
```

Para consultar os logs da API:

```bash
docker compose logs -f api
```

### 5. Teste a API

Acesse o endereço abaixo no navegador ou utilize um cliente HTTP:

`http://localhost:8080/produtos`

Para parar os serviços:

```bash
docker compose down
```

Os dados do MySQL ficam armazenados em um volume persistente. O comando `docker compose down -v` também remove esse volume e os dados armazenados nele; utilize-o somente se realmente quiser apagar os dados.

## Executar os testes

No Windows, pelo PowerShell:

```powershell
.\mvnw.cmd --batch-mode clean verify
```

Esse comando compila a aplicação e executa os testes automatizados.

O projeto também possui um workflow do GitHub Actions configurado para executar o build e os testes em eventos de push e pull request.

## Endpoints da API

A URL base local é `http://localhost:8080`.

### Produtos

| Método | Endpoint         | Descrição           |
| ------ | ---------------- | ------------------- |
| GET    | `/produtos`      | Lista os produtos   |
| GET    | `/produtos/{id}` | Consulta um produto |
| POST   | `/produtos`      | Cadastra um produto |
| PUT    | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Exclui um produto   |

Exemplo de cadastro:

**POST** `/produtos`

```json
{
  "nome": "Arroz Tipo 1 - 5kg",
  "preco": 22.50,
  "precoConsumidor": 29.90,
  "estoque": 20.0
}
```

### Clientes

| Método | Endpoint         | Descrição           |
| ------ | ---------------- | ------------------- |
| GET    | `/clientes`      | Lista os clientes   |
| GET    | `/clientes/{id}` | Consulta um cliente |
| POST   | `/clientes`      | Cadastra um cliente |
| PUT    | `/clientes/{id}` | Atualiza um cliente |
| DELETE | `/clientes/{id}` | Exclui um cliente   |

Os campos do cliente incluem nome, endereço, telefone, e-mail e documentos. CPF, CNPJ e inscrição estadual dependem das regras de validação implementadas no projeto.

### Vendas

| Método | Endpoint       | Descrição          |
| ------ | -------------- | ------------------ |
| GET    | `/vendas`      | Lista as vendas    |
| GET    | `/vendas/{id}` | Consulta uma venda |
| POST   | `/vendas`      | Cadastra uma venda |

Exemplo de cadastro:

**POST** `/vendas`

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

**Observações:**

* O cliente informado deve existir.
* Os produtos informados devem existir.
* A lista `itens` não pode estar vazia.
* A quantidade de cada item deve ser positiva.

Os IDs utilizados são apenas exemplos. Utilize IDs existentes no banco de dados.

## Segurança e configuração

As credenciais do banco são fornecidas por variáveis de ambiente. Arquivos locais com configurações e segredos não devem ser versionados.

Este projeto é educacional e está em evolução. Antes de utilizá-lo em produção, são necessárias avaliações adicionais de segurança, autorização, tratamento de erros, observabilidade e configuração de ambiente.

## Objetivo de aprendizado

Este projeto faz parte da minha evolução em desenvolvimento backend com Java, com foco em APIs REST, persistência de dados, regras de negócio, testes automatizados, conteinerização e boas práticas de desenvolvimento.
