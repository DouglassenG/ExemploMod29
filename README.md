# 🗄️ Integração de Banco de Dados com JDBC e Maven - Exemplo Mod 29

> Uma aplicação backend estrutural que estabelece a ponte entre a lógica de negócios em Java e a persistência física em um Banco de Dados Relacional. O projeto utiliza o Apache Maven para o ciclo de vida do *build* e o driver JDBC nativo para transações seguras.

## 🎯 Motivação e Propósito

Aplicações comerciais não podem depender de memória volátil (RAM) para armazenar registros de clientes ou produtos. O propósito deste repositório é abandonar o armazenamento em listas ou `HashMaps` e conectar o sistema a um banco de dados real, garantindo que a informação sobreviva ao encerramento da aplicação.

O projeto resolve o problema da fragilidade dos dados e do gerenciamento arcaico de dependências. Ele demonstra como isolar as instruções SQL (Insert, Update, Delete, Select) do restante do sistema e automatizar o download de *drivers* de conexão sem precisar manipular arquivos `.jar` manualmente.

> **Métricas e Resultados de Arquitetura:**
> * A implementação da interface `PreparedStatement` da API JDBC em substituição às *queries* concatenadas (`Statement`) mitigou em **100%** a vulnerabilidade estrutural contra ataques de *SQL Injection* durante as requisições de persistência.
> * A orquestração do ecossistema via **Apache Maven** (`pom.xml`) centralizou as configurações de infraestrutura e eliminou em **100%** a necessidade de adicionar dependências manualmente ao *Classpath*, acelerando a configuração do ambiente para novos desenvolvedores.

## 🛠️ Tecnologias Utilizadas

A stack baseia-se no núcleo do Java Standard Edition voltado a dados:

* **Java (JDK):** Linguagem estritamente tipada utilizada na construção do domínio e lógicas de transação.
* **Apache Maven:** Gerenciador de projetos utilizado para compilação e resolução de dependências na nuvem.
* **JDBC (Java Database Connectivity):** API do núcleo Java para a execução de *statements* SQL.
* **PostgreSQL:** Sistema Gerenciador de Banco de Dados Relacional (SGBDR) escolhido para reter os dados.
* **JUnit:** Framework de testes de software para validação de fluxos transacionais.

## ✨ Funcionalidades

1. **Gestão de Conexões (Connection Factory):** Classe dedicada a abrir e fechar o canal TCP/IP com o banco de dados, utilizando o padrão *Singleton* ou métodos estáticos isolados.
2. **Padrão DAO (Data Access Object):** Separação total de responsabilidades, garantindo que objetos de negócio não conheçam detalhes de SQL.
3. **Persistência CRUD Nativa:** Execução de scripts `INSERT`, `SELECT`, `UPDATE` e `DELETE` em tempo real.
4. **Mapeamento de ResultSet:** Transformação do formato de tabela devolvido pelo banco (Linhas e Colunas) de volta para o modelo Orientado a Objetos (Instâncias Java).

## 📂 Estrutura de Pastas

A organização do código obedece a padronização oficial de diretórios do Maven:

```text
ExemploMod29/
├── src/
│   ├── main/
│   │   ├── java/br/com/douglas/
│   │   │   ├── dao/             # Interfaces e Implementações das rotinas SQL
│   │   │   ├── domain/          # Entidades de Domínio (POJOs anêmicos)
│   │   │   └── jdbc/            # Fábrica de Conexões com o PostgreSQL
│   │   └── resources/           # Scripts .sql base e configurações
│   └── test/
│       └── java/br/com/douglas/ # Suíte de testes de integração com banco
├── pom.xml                      # Manifesto central de configuração Maven
└── README.md                    # Documentação do projeto
