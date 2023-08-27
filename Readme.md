## Data Analysis Application - Kotlin WebFLUX 📊
Codifciação em Kotlin para aplicação com uso de programação reativa construída com framework Spring WebFlux, Apache Cassandra um banco de dados NoSQL  e Apache Pulsar para fazer a parte de mensagens assíncronas para análise de dados.

 ## Funcionalidades 🚀
Análise de Dados: Processa e analisa dados em tempo real.
Persistência com Cassandra: Armazena dados em um banco de dados NoSQL altamente escalável.
Mensageria com Pulsar: Envio de mensagens assíncronas para processamento posterior.

## Tecnologias 🛠️
Linguagem: Kotlin

Framework: Spring WebFlux

Banco de Dados: Apache Cassandra

Mensageria: Apache Pulsar

Containerização: Docker

## Execução Local 🚀

Pré-requisitos
Docker e Docker Compose instalados.

## Passos
Clone o repositório:

git clone https://github.com/seu_usuario/DataAnalysis.git

cd DataAnalysis

## Construa e inicie os serviços usando Docker Compose:

docker-compose up --build

Acesse a aplicação no navegador:

http://localhost:8080

## Compile e construa o projeto de forma Local sem conteiner:

mvn clean install

## Execute a aplicação:
Se você estiver usando Spring Boot, pode usar o seguinte comando:

mvn spring-boot:run

Acesse a aplicação no navegador:

http://localhost:8080


## Endpoints 🌐
Salvar Dados no Cassandra:

- Método: POST
* Endpoint: /data-analysis/save

* Payload: json
```
{

  "Id": "ID_único_do_produto",
  
  "productName": "Nome do Produto",
  
  "productPrice": 100.0,
  
  "quantitySold": 10
  
}
```
- Enviar Produto para o Pulsar
* Método: POST

* Endpoint: /data-analysis/send

* Payload: json

{

  "Id": "ID_único_do_produto",

  "productName": "Nome do Produto",

  "productPrice": 100.0,

  "quantitySold": 10
  
}

- Analisar e Enviar Produto

* Método: POST
* Endpoint: /data-analysis/analyze-and-send

* Payload: json

{

  "Id": "ID_único_do_produto",

  "productName": "Nome do Produto",

  "productPrice": 100.0,

  "quantitySold": 10
  
}

- Obter Produto por ID

Método: GET

Endpoint: /data-analysis/{id}
Parâmetros:
id: ID do produto que você deseja recuperar.

- Atualizar Produto
* Método: PUT
* Endpoint: /data-analysis/{id}
* Parâmetros:
* id: ID do produto que você deseja atualizar.

Payload: json
{

  "productName": "Nome Atualizado do Produto",

  "productPrice": 150.0,

  "quantitySold": 15
  
}

- Deletar Produto

* Método: DELETE
* Endpoint: /data-analysis/{id}
* Parâmetros:
* id: ID do produto que você deseja deletar.

## Autor
Emerson Amorim
