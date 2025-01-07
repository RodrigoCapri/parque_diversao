# API Web para Sistema de Parque de Diversões

## Visão Geral

Esta é uma API desenvolvida em **Spring Boot** para gerenciar as operações de um sistema de parque de diversões. A API abrange funcionalidades como:

- Controle de PDV (Ponto de Venda) para vendas de ingressos e produtos.
- Gerenciamento de estacionamento (entrada, saída e disponibilidade de vagas).
- Emissão de Notas Fiscais (NF) e Notas Fiscais de Serviço (NFS).

## Funcionalidades Principais

### PDV (Ponto de Venda)
- Venda de ingressos para o parque.
- Cadastro e gerenciamento de produtos (alimentos, bebidas, souvenirs).
- Consulta de relatórios de vendas diárias, semanais e mensais.

### Controle de Estacionamento
- Registro de entrada e saída de veículos.
- Consulta de vagas disponíveis.
- Cálculo automático de tarifas com base no tempo de permanência.

### Emissão de Notas Fiscais
- Geração automática de NF para vendas de produtos.
- Emissão de NFS para serviços prestados.
- Integração com serviços fiscais para validação de notas.

## Tecnologias Utilizadas

- **Java 21**: Linguagem principal para desenvolvimento.
- **Spring Boot**: Framework para criação da aplicação.
- **Spring Data JPA**: Gerenciamento de persistência de dados.
- **Spring Security**: Controle de autenticação e autorização.
- **MySQL**: Banco de dados relacional para armazenamento de dados.
- **Hibernate**: ORM para interação com o banco de dados.
- **Swagger/OpenAPI**: Documentação interativa da API.

## Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   ├── com.example.parque/
│   │       ├── utils/    # Utilidade e recursos para o sistema
│   │       ├── controller/    # Controladores REST
│   │       ├── service/        # Serviços e regras de negócio
│   │       ├── repository/     # Interfaces de persistência
│   │       ├── entities/          # Classes de modelo/entidade
│   │       └── config/         # Configurações do Spring
│   └── resources/
│       ├── application.yml   # Configurações da aplicação
│       └── static/           # Arquivos estáticos (se necessário)
```

## Endpoints Principais

### PDV
- **POST** `/api/pdv/vendas` - Registrar uma venda.
- **GET** `/api/pdv/relatorios` - Obter relatórios de vendas.

### Estacionamento
- **POST** `/api/estacionamento/entrada` - Registrar entrada de veículo.
- **POST** `/api/estacionamento/saida` - Registrar saída de veículo e calcular tarifa.
- **GET** `/api/estacionamento/vagas` - Consultar vagas disponíveis.

### Nota Fiscal
- **POST** `/api/notas/emissao` - Emitir uma NF ou NFS.
- **GET** `/api/notas/{id}` - Consultar detalhes de uma nota fiscal.

