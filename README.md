# Mercantil Nova Estrela API

API REST desenvolvida com **Java 17 e Spring Boot** para simular as operações de um estabelecimento comercial, incluindo gerenciamento de clientes, produtos, vendas e estoque.

O projeto foi desenvolvido com foco no aprendizado de desenvolvimento backend, aplicando arquitetura em camadas, regras de negócio, persistência de dados, validação, autenticação JWT, testes automatizados, conteinerização e implantação local com Kubernetes.

## Tecnologias utilizadas

### Backend
- **Java 17** — linguagem de programação.
- **Spring Boot** — configuração e execução da aplicação.
- **Spring Web MVC** — criação dos endpoints REST.
- **Spring Data JPA** — persistência e consultas ao banco de dados.
- **Hibernate** — mapeamento objeto-relacional (ORM).
- **Spring Security** — autenticação e proteção dos endpoints.
- **JWT (JSON Web Token)** — autenticação baseada em tokens.
- **BCrypt** — hash de senhas.
- **Bean Validation** — validação dos dados recebidos pela API.
- **Maven** — gerenciamento de dependências e build.

### Banco de dados
- **MySQL 8.4** — banco de dados principal.
- **H2 Database** — banco em memória utilizado nos testes automatizados.

### Testes
- **JUnit** — execução de testes automatizados.
- **Mockito** — criação de mocks para testes isolados.

### DevOps e ferramentas
- **Docker** — conteinerização da aplicação.
- **Docker Compose** — execução integrada da API e do banco de dados.
- **Kubernetes** — gerenciamento dos recursos da aplicação em cluster.
- **Minikube** — execução local do Kubernetes.
- **kubectl** — gerenciamento dos recursos Kubernetes.
- **Git e GitHub** — controle de versão e hospedagem do código.
- **GitHub Actions** — automação de build e testes.
- **GitHub Container Registry (GHCR)** — publicação de imagens Docker, conforme configurado no pipeline do projeto.

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de produtos.
- Cadastro, consulta, atualização e exclusão de clientes.
- Cadastro e consulta de vendas.
- Validação dos dados recebidos nas requisições.
- Tratamento de exceções.
- Persistência de dados com MySQL.
- Utilização de DTOs para organizar dados de entrada e saída.
- Regras de negócio relacionadas a vendas e estoque.
- Autenticação com Spring Security e JWT.
- Armazenamento de senhas usando BCrypt.
- Testes automatizados com JUnit, Mockito e H2.
- Execução da aplicação com Docker Compose.
- Manifests para implantação local com Kubernetes.
- Pipeline de CI/CD com GitHub Actions.

## Arquitetura

A aplicação utiliza arquitetura em camadas para separar responsabilidades e facilitar a manutenção do código.

- **Controller:** recebe as requisições HTTP e retorna as respostas.
- **Service:** concentra as regras de negócio.
- **Repository:** realiza o acesso e a persistência dos dados.
- **Model:** representa as entidades do sistema.
- **DTO:** organiza os dados de entrada e saída da API.
- **Exception:** reúne o tratamento de erros.
- **Validation:** contém validações específicas.

## Como executar com Docker Compose

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

Substitua os valores por credenciais locais adequadas. Não compartilhe esse arquivo nem o versione no GitHub.

O segredo JWT precisa atender aos requisitos de tamanho e algoritmo configurados na aplicação.

### 3. Inicie os serviços

```bash
docker compose up -d --build
```

O Docker Compose iniciará o MySQL e a API, com a dependência de saúde do banco de dados configurada no Compose.

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

Os dados do MySQL são armazenados em um volume persistente. O comando `docker compose down -v` também remove o volume e os dados locais.

## Como executar os testes

No Windows, abra o PowerShell na raiz do projeto e execute:

```powershell
.\mvnw.cmd --batch-mode clean verify
```

O comando compila a aplicação e executa os testes automatizados. O ambiente de testes utiliza H2, reduzindo a dependência de uma instância local do MySQL.

## Autenticação com JWT

A API utiliza Spring Security e JWT para proteger os endpoints. As senhas dos usuários são armazenadas como hashes BCrypt.

### Login

**POST** `/auth/login`

Exemplo de requisição:

```json
{
  "username": "seu_usuario",
  "password": "sua_senha"
}
```

O usuário precisa estar previamente configurado no banco de dados. A API não disponibiliza um endpoint público para cadastro de usuários.

Após autenticar, utilize o token retornado no cabeçalho das requisições protegidas:

```http
Authorization: Bearer SEU_TOKEN_JWT
```

O token possui validade configurada na aplicação. Não compartilhe tokens reais ou credenciais.

## Endpoints da API

URL base padrão:

`http://localhost:8080`

Ao executar pelo port-forward do Kubernetes descrito abaixo, utilize `http://localhost:8082`.

Todos os endpoints listados exigem autenticação, exceto o endpoint de login.

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

As validações dos dados cadastrais dependem das regras implementadas na aplicação.

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

Regras de negócio previstas para as vendas:

- O cliente informado deve existir.
- Os produtos informados devem existir.
- A lista de itens não pode estar vazia.
- A quantidade de cada item deve ser positiva.

Os IDs são exemplos. Utilize registros existentes no banco de dados.

## Implantação com Kubernetes e Minikube

O repositório inclui manifests Kubernetes para executar a API Spring Boot e o banco MySQL em um cluster local.

### Recursos configurados

- **Deployment:** executa os containers da API e do MySQL.
- **Service:** permite a comunicação interna com o banco e o acesso encaminhado à API.
- **ConfigMap:** armazena configurações não sensíveis, como o endereço do banco.
- **Secret:** fornece a senha do banco e o segredo JWT.
- **PersistentVolumeClaim (PVC):** solicita armazenamento persistente para os dados do MySQL.

### Pré-requisitos

- Docker Desktop.
- Minikube.
- kubectl.
- Arquivo `.env` configurado na raiz do projeto.

### 1. Inicie o cluster

```powershell
minikube start --driver=docker
```

### 2. Construa a imagem da API

Na raiz do projeto:

```powershell
minikube image build -t mercantil-api:local .
```

O Deployment utiliza a imagem local `mercantil-api:local` com `imagePullPolicy: Never`, adequada a esse fluxo de desenvolvimento no Minikube.

### 3. Configure o Secret

Se o Secret `mercantil-secret` ainda não existir no cluster:

```powershell
kubectl create secret generic mercantil-secret --from-env-file=.env
```

O arquivo `.env` precisa conter as variáveis `DB_PASSWORD` e `JWT_SECRET`. Não publique os valores dessas variáveis.

Se o Secret já existir, não execute novamente o comando de criação. Atualizações de Secrets exigem cuidado, especialmente quando o banco já inicializou seu diretório de dados persistente.

### 4. Aplique os manifests

```powershell
kubectl apply -f .\k8s\mercantil-storage.yaml
kubectl apply -f .\k8s\mercantil-mysql.yaml
kubectl apply -f .\k8s\mercantil-config.yaml
kubectl apply -f .\k8s\mercantil-api.yaml
```

### 5. Verifique os recursos

```powershell
kubectl get pods
kubectl get services
kubectl get pvc
```

Aguarde os Pods ficarem prontos e confirme que o PVC está vinculado antes de testar a API.

### 6. Disponibilize a API localmente

Se o Service da API ainda não existir, crie-o:

```powershell
kubectl expose deployment mercantil-api --name=mercantil-api-service --port=8080 --target-port=8080 --type=ClusterIP
```

Depois, encaminhe a porta:

```powershell
kubectl port-forward service/mercantil-api-service 8082:8080
```

Mantenha o terminal aberto enquanto estiver utilizando o encaminhamento. A API ficará acessível em:

`http://localhost:8082`

O endpoint `/auth/login` pode ser utilizado para obter um token JWT. Envie o token no cabeçalho `Authorization: Bearer` para acessar os endpoints protegidos.

### Segurança e ambiente

Os manifests utilizam referências a um Secret em vez de armazenar credenciais diretamente nos arquivos versionados. Em ambientes de produção, é necessário avaliar também RBAC, restrições de rede, gerenciamento seguro de segredos, réplicas, probes de saúde, limites de recursos e estratégia de atualização.

Esta configuração foi preparada para aprendizado e execução local com Minikube, não como uma configuração de produção pronta.

## CI/CD com GitHub Actions

O projeto inclui um workflow de GitHub Actions para automatizar o processo de build e testes.

Conforme a configuração do workflow, o pipeline pode:

1. Executar em eventos de `push` e `pull_request`.
2. Configurar o ambiente com Java 17.
3. Compilar a aplicação e executar os testes automatizados.
4. Construir e publicar uma imagem Docker no GitHub Container Registry após um build bem-sucedido em `push` para a branch `main`.

Imagem Docker configurada:

`ghcr.io/guilhermeferacoli/mercantil-api`

A disponibilidade da imagem depende da execução bem-sucedida do workflow e das permissões de publicação configuradas no GitHub.

## Objetivo do projeto

Este projeto faz parte da minha evolução em desenvolvimento backend com Java, com foco em APIs REST, regras de negócio, persistência de dados, autenticação, testes automatizados, Docker, Kubernetes e automação de build.

O objetivo é aplicar conhecimentos técnicos em uma aplicação prática, desenvolver boas práticas de engenharia de software e evoluir continuamente por meio da implementação de novas funcionalidades e melhorias.

**Repositório:** https://github.com/GuilhermeFeracoli/mercantil-api
