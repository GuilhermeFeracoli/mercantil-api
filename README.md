# Mercantil Nova Estrela API

API REST para gerenciamento de produtos, clientes e vendas, desenvolvida com Java e Spring Boot.

## Sobre o projeto

O Mercantil Nova Estrela é um projeto de backend que simula operações de um estabelecimento comercial. O objetivo é aplicar conceitos de desenvolvimento de APIs REST, persistência de dados, validação de informações e organização em camadas.

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Bean Validation

## Funcionalidades

* Cadastro, consulta, atualização e exclusão de produtos.
* Cadastro, consulta, atualização e exclusão de clientes.
* Gerenciamento de vendas.
* Validação de dados recebidos pela API.
* Tratamento centralizado de exceções.
* Persistência de dados em banco MySQL.
* Transferência de dados por meio de DTOs.

## Arquitetura

O projeto utiliza uma organização em camadas, separando responsabilidades:

* **Controller:** recebe e responde às requisições HTTP.
* **Service:** concentra as regras de negócio.
* **Repository:** realiza operações de persistência.
* **Model:** representa as entidades do sistema.
* **DTO:** define os dados recebidos e retornados pela API.
* **Exception:** reúne o tratamento de erros.
* **Validation:** contém validações específicas.

## Configuração e execução

### Pré-requisitos

* JDK compatível com a versão Java definida no projeto.
* MySQL instalado e em execução.
* Git.

### 1. Clone o repositório

```bash
git clone https://github.com/GuilhermeFeracoli/mercantil-api.git
cd mercantil-api
```

### 2. Crie o banco de dados

No MySQL, execute:

```sql
CREATE DATABASE mercantil;
```

### 3. Configure as credenciais

Crie ou configure o arquivo `src/main/resources/application.properties` localmente com as propriedades necessárias para a conexão com o MySQL.

Por segurança, esse arquivo não é versionado. Configure a senha por meio da variável de ambiente `DB_PASSWORD`.

### 4. Execute a aplicação

No Windows, pelo terminal PowerShell:

```powershell
$securePassword = Read-Host -Prompt "Digite a senha do MySQL" -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $securePassword).Password
.\mvnw.cmd spring-boot:run
```

Por padrão, a aplicação é iniciada na porta `8080`.

## Testes

Para executar os testes automatizados:

```powershell
.\mvnw.cmd test
```

## Endpoints principais

| Método | Endpoint         | Descrição           |
| ------ | ---------------- | ------------------- |
| GET    | `/produtos`      | Lista produtos      |
| GET    | `/produtos/{id}` | Consulta um produto |
| POST   | `/produtos`      | Cadastra um produto |
| PUT    | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Exclui um produto   |
| GET    | `/clientes`      | Lista clientes      |
| GET    | `/clientes/{id}` | Consulta um cliente |
| POST   | `/clientes`      | Cadastra um cliente |
| PUT    | `/clientes/{id}` | Atualiza um cliente |
| DELETE | `/clientes/{id}` | Exclui um cliente   |

## Objetivo de aprendizado

Este projeto faz parte da minha evolução em desenvolvimento backend com Java, com foco em APIs REST, persistência de dados, validação, tratamento de exceções e boas práticas de organização de código.
