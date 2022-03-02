# Register-Of-Address

This is a Springboot project that explore the design pattern Singleton, Strategy and Facade, registering a user with
only name and cep, and a api will be consumed for get the address and save in a database. This project also explores unit tests, integration tests, documentation and exception handling.

- [Register Of Address](#Register-Of-Address)
  - [Requirements](#requirements)
  - [Running the application](#running-the-application)
  - [API Doc](#api-doc)
  - [User characteristics](#User-characteristics)
  - [Address characteristics](#Address-characteristics)
  - [Technologies involved](#technologies-involved)
  - [Utilities](#utilities)
  - [Operations](#operations)
    - [Create user](#create-user)
    - [List users](#list-users)
    - [Find user](#find-user)
    - [Remove user](#remove-user)
    - [Update user](#update-user)

## Requirements

- PostgreSQL

  Running on localhost:5432 and a database called "RegisterOfAddressApplication".

## Running the application

To run this API you'll need to execute the following command on your terminal:

    mvn spring-boot:run

## API Doc

A Swagger document for this API can be found in:

    http://localhost:8080/starwars/swagger-ui/index.html

## User characteristics

- CPF (primary key)
- Name
- Address

## Address characteristics

- CEP (primary key)
- Logradouro
- Complemento
- Bairro
- Localidade
- Uf
- ibge
- gia
- ddd
- siafi
- houseNumber

With the CEP information, an external api is consumed to provide data linked to the cep, such as state, city, street, DDD, and others. But the house or apartment number must be provided by the user
## Technologies involved

- Spring Boot
- Swagger
- JUnit
- MockMVC
- PostgreSQL
- Feing
- OpenAPI

## Utilities

In order to help you, all routes are available in [API Doc](#api-doc) through openAPI.

## Operations

### Create User

Operation sent with HTTP method **POST** to http://{{api-host}}:{{port}}/users and the request body should follow this pattern:

```json
{
    "cpf": "458475",
    "name": "test",
    "endereco": {
      "cep": "40015-970",
      "numeroCasa": "29",
      ...
    }
}
```

As response, the user, including the address with house number should be returned.

```json
{
  "name": "test",
  "endereco": {
    "cep": "40015-970",
    "logradouro": "Praça da Inglaterra",
    "complemento": "s/n",
    "bairro": "Comércio",
    "localidade": "Salvador",
    "uf": "BA",
    "ibge": "2927408",
    "gia": "",
    "ddd": "71",
    "siafi": "3849",
    "numeroCasa": 29
  },
  "cpf": 458475
}
```
### List Users
Operation sent with HTTP method **GET** to http://{{api-host}}:{{port}}/users 

Example response:

```json
[
  {
    "name": "test",
    "endereco": {
      "cep": "40015-970",
      "logradouro": "Praça da Inglaterra",
      "complemento": "s/n",
      "bairro": "Comércio",
      "localidade": "Salvador",
      "uf": "BA",
      "ibge": "2927408",
      "gia": "",
      "ddd": "71",
      "siafi": "3849",
      "numeroCasa": 29
    },
    "cpf": 458475
  },
  {
    "name": "test2",
    "endereco": {
      "cep": "20050-000",
      "logradouro": "Rua Alexandre Herculano",
      "complemento": "",
      "bairro": "Centro",
      "localidade": "Rio de Janeiro",
      "uf": "RJ",
      "ibge": "3304557",
      "gia": "",
      "ddd": "21",
      "siafi": "6001",
      "numeroCasa": 19
    },
    "cpf": 454847415
  }
]
```

### Find User

An user can be found by the cpf parameter sent in the request

HTTP method: GET

        http://{{api-host}}:{{port}}/users/{cpf}

### Remove User

An user can be deleted by the cpf parameter sent in the request.

  HTTP method: DELETE

        http://{{api-host}}:{{port}}/users/{{cpf}}

### Update User

An user can be updated by the cpf parameter sent in the request and a request body like in *POST* route.

HTTP method: PUT

        http://{{api-host}}:{{port}}/users/{{cpf}}

```json
{
    "cpf": "458475",
    "name": "test",
    "endereco": {
      "cep": "40015-970",
      "numeroCasa": "29",
      ...
    }
}
```

But cpf is not possible to modify only the other attributes, if you intend to modify the cpf it is recommended to delete the user and then create a new one with the new data.

Example response:

```json
{
    "cpf": "458475",
    "name": "test",
    "endereco": {
      "cep": "40015-970",
      "numeroCasa": "29",
      ...
    }
}
```