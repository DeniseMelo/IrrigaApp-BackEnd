# IrrigaApp Backend

Bem-vindo ao repositório do **IrrigaApp**, um sistema de gerenciamento inteligente de irrigação para canteiros urbanos. Este é o backend da aplicação, desenvolvido em **Spring Boot** com integração ao banco de dados **MongoDB**.

## Índice

- [Pré-requisitos](#pré-requisitos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Rodar A Aplicação](#como-rodar-a-aplicação)
- [Acesse o Swagger UI](#acesse-o-swagger-UI)

## Pré-requisitos

Antes de começar, você vai precisar ter os seguintes componentes instalados:

- Docker e Docker Compose
- Java 17+
- Maven 3.6+

## Tecnologias Utilizadas

- **Java 17+**: Linguagem de programação
- **Spring Boot**: Framework para criação de aplicações Java
- **MongoDB**: Banco de dados NoSQL utilizado na aplicação
- **Docker**: Containerização da aplicação e do MongoDB
- **Swagger**: Documentação automática dos endpoints


A aplicação utiliza **Docker** para rodar tanto o backend quanto o banco de dados MongoDB. Todos os serviços são configurados no arquivo `docker-compose.yml`.

```yaml
version: '3'
services:
  mongo:
    image: mongo:4.4
    container_name: mongo
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: password
      MONGO_INITDB_DATABASE: irrigaappbd
    volumes:
      - ./data/db:/data/db

  irrigaapp:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mongo
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://admin:password@mongo:27017/irrigaappbd?authSource=admin
      - PROFILE=prd
```

## Como Rodar a Aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/irrigaapp-backend.git
   ```
2. **Entre na pasta do projeto:** 
   ```bash
   cd irrigaapp-backend/IrrigaApp
   ```
   
3. **Compile a aplicação com o Maven:**
   ```bash
   mvn clean package
    ```
4. **Inicie os containers com o Docker Compose:**
   ```bash
   docker-compose up --build
   ```
## Acesse o Swagger UI

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Você verá a documentação da API e poderá testar os endpoints diretamente da interface gráfica.



   
