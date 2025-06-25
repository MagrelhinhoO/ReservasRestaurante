# Sistema de Reservas de Restaurante

Este repositório contém um sistema completo de reservas para restaurantes, desenvolvido como projeto integrador do curso de Análise e Desenvolvimento de Sistemas. O objetivo do sistema é permitir o gerenciamento de reservas, clientes, mesas e funcionários, com persistência de dados em banco relacional.

## 🎯Objetivo do Projeto

Aplicar os principais conceitos da Programação Orientada a Objetos (POO), mapeamento objeto-relacional com JPA/Hibernate, uso de banco de dados relacional (PostgreSQL), e arquitetura em camadas (Model, Repository, Service e Controller), com entrada e saída de dados via console.

## 📌Funcionalidades Implementadas

- CRUD completo para as entidades:
  - Cliente
  - Mesa
  - Funcionário
  - Reserva
- Relacionamentos entre entidades utilizando anotações JPA:
  - Reserva possui associações com Cliente, Mesa e Funcionário
- Consultas personalizadas usando HQL e JPQL, incluindo:
  - Filtro de reservas por intervalo de datas
  - Listagem de reservas com JOIN nas entidades relacionadas
  - Contagem total de reservas
  - Listagem por status
- Sistema de menu interativo no console, com entrada do usuário
- Populador automático de dados fictícios para testes

## 🛠️Tecnologias Utilizadas

- Linguagem: Java 17
- ORM: Hibernate (com JPA)
- Banco de Dados: PostgreSQL
- Gerenciador de Dependências: Maven
- IDE: IntelliJ IDEA
- Ferramenta de Banco de Dados: DBeaver

## 📁Estrutura do Projeto

src/
├── main/
│ ├── java/
│ │ └── com.restaurante/
│ │ ├── model/ → Entidades JPA (Cliente, Mesa, Reserva, Funcionario, Administrador, Gerente)
│ │ ├── repository/ → Repositórios com consultas personalizadas
│ │ ├── service/ → Camada de regras de negócio
│ │ ├── controller/ → Menu principal e controle de entrada do usuário
│ │ └── util/ → Classes auxiliares (Populador, HibernateUtil, etc.)
│ └── resources/
│ └── META-INF/
│ └── persistence.xml → Configuração do Hibernate e conexão com PostgreSQL

## Requisitos para Execução

1. Ter o PostgreSQL instalado e em execução
2. Criar o banco de dados com o nome especificado no persistence.xml
3. Atualizar as credenciais do banco de dados (usuário e senha) no arquivo persistence.xml
4. Executar a aplicação pela classe Main.java no pacote controller

> Observação: Ao iniciar o sistema, o populador insere dados fictícios automaticamente, caso o banco esteja vazio.

Todos os diagramas foram elaborados com ferramentas como Lucidchart e baseados nos requisitos levantados através das histórias de usuário (seguindo a técnica INVEST).

## Arquitetura Aplicada

O sistema está organizado de forma modular utilizando os seguintes pacotes:

- *model*: Entidades JPA que representam as tabelas do banco
- *repository*: Responsável pela persistência e consultas aos dados
- *service*: Contém a lógica de negócio e operações específicas
- *controller*: Responsável pelo controle do fluxo da aplicação via console
- *util*: Configurações auxiliares, como conexão com o banco e gerador de dados

## 👨‍💻Autores

- Nome: Murilo Eberhardt, Arthur Fernandes de Souza  
- Curso: Análise e Desenvolvimento de Sistemas  
- Instituição: UniAmérica Descomplica  
- Período: 3º período (noturno)
