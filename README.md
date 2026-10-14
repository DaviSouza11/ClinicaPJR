# ClinicaPJR

API em Java para apoiar a gestão de uma clínica médica. O projeto modela usuários, médicos, pacientes e consultas, com persistência em MySQL, separação em camadas, proteção de senhas com BCrypt e envio de código de verificação por e-mail.

O objetivo é aplicar conceitos de desenvolvimento back-end, programação orientada a objetos, JDBC, APIs REST, segurança, modelagem de dados e arquitetura em camadas.

> **Status:** projeto educacional em desenvolvimento. O cadastro de usuários está parcialmente implementado. Os módulos de médicos, pacientes e agenda possuem modelos e acesso a dados, mas ainda precisam ser expostos por endpoints REST. Consulte [Limitações atuais](#limitações-atuais).

## Funcionalidades

### Implementadas ou parcialmente implementadas

- Cadastro de usuário por API REST.
- Hash de senha com BCrypt e fator de custo configurado.
- Persistência de usuários em MySQL.
- Geração de código numérico para verificação da conta.
- Envio de código de verificação por e-mail.
- Ativação de conta na camada de acesso a dados.
- Consultas preparadas com JDBC.
- Modelos para médicos, pacientes, usuários e consultas.
- Operações de persistência para médicos, pacientes e agenda.

### Planejadas

- Confirmação do código de verificação por endpoint.
- Login e controle de acesso.
- CRUD completo de médicos.
- CRUD completo de pacientes.
- Agendamento, consulta, atualização e cancelamento de consultas.
- Validação de conflito de horários.
- Pesquisa por especialidade, médico, paciente e período.
- Documentação OpenAPI/Swagger.
- Testes automatizados.

## Tecnologias

- Java
- Spring Boot 3.2.5
- Spring Web
- Spring JDBC
- Spring Mail
- Maven
- MySQL
- JDBC
- BCrypt
- API REST

## Arquitetura

```text
Requisição HTTP
      |
      v
  Controller
      |
      v
   Service
      |
      v
     DAO
      |
      v
    MySQL
```

Responsabilidades das camadas:

- **Controller:** recebe requisições HTTP e devolve respostas da API.
- **Service:** concentra regras de negócio, geração de código e envio de e-mail.
- **DAO:** executa operações de persistência com JDBC.
- **Model:** representa usuários, pessoas, médicos, pacientes e consultas.
- **DTO:** define objetos específicos utilizados nas respostas da API.
- **Security:** concentra operações relacionadas ao hash de senhas.
- **Factory:** cria conexões JDBC com o banco de dados.

## Estrutura do projeto

```text
src/main/
|-- java/br/com/davi/clinica/
|   |-- controller/
|   |   `-- usuarioController.java
|   |-- dao/
|   |   |-- AgendaDAO.java
|   |   |-- MedicoDAO.java
|   |   |-- PacienteDAO.java
|   |   `-- UsuarioDAO.java
|   |-- dto/
|   |   `-- CadastroResultado.java
|   |-- factory/
|   |   `-- ConnectionFactory.java
|   |-- model/
|   |   |-- Agenda.java
|   |   |-- Medico.java
|   |   |-- Paciente.java
|   |   |-- Pessoa.java
|   |   `-- Usuario.java
|   |-- security/
|   |   `-- SenhaBCrypt.java
|   |-- service/
|   |   |-- EmailService.java
|   |   `-- UsuarioService.java
|   `-- ClinicaApplication.java
`-- resources/
    `-- application.properties
```

## Modelo de domínio

```text
Pessoa
|-- Médico
`-- Paciente

Usuário
|-- e-mail
|-- senha protegida por hash
|-- perfil
`-- situação da conta

Agenda
|-- médico
|-- paciente
|-- data
|-- horário
`-- status
```

## Requisitos

- Java 17 ou superior.
- Maven 3.9 ou superior.
- MySQL 8 ou superior.
- Conta de e-mail SMTP para envio do código de verificação.

## Configuração do banco de dados

Crie o banco utilizado pela aplicação:

```sql
CREATE DATABASE PRJClinica
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE PRJClinica;
```

Crie as tabelas compatíveis com o modelo atual:

```sql
CREATE TABLE usuarios (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(180) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(40) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT FALSE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE medicos (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE pacientes (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    dataNascimento DATE NOT NULL
);

CREATE TABLE agendas (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    medico_id INT UNSIGNED NOT NULL,
    paciente_id INT UNSIGNED NOT NULL,
    dataConsulta DATE NOT NULL,
    horaConsulta TIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT fk_agenda_medico
        FOREIGN KEY (medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_agenda_paciente
        FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    CONSTRAINT uk_agenda_medico_horario
        UNIQUE (medico_id, dataConsulta, horaConsulta)
);
```

O índice único de médico, data e horário impede que o mesmo profissional receba duas consultas simultâneas. A aplicação ainda deve tratar essa violação e devolver uma resposta HTTP adequada.

## Variáveis de ambiente

O arquivo `application.properties` espera as seguintes variáveis:

| Variável | Finalidade |
| --- | --- |
| `DB_USERNAME` | Usuário do MySQL |
| `DB_PASSWORD` | Senha do MySQL |
| `MAIL_USERNAME` | Conta usada para enviar o código |
| `MAIL_PASSWORD` | Senha de aplicativo do provedor de e-mail |

### PowerShell

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="sua-senha"
$env:MAIL_USERNAME="seu-email@example.com"
$env:MAIL_PASSWORD="sua-senha-de-aplicativo"
```

### Bash

```bash
export DB_USERNAME="root"
export DB_PASSWORD="sua-senha"
export MAIL_USERNAME="seu-email@example.com"
export MAIL_PASSWORD="sua-senha-de-aplicativo"
```

Nunca publique senhas reais ou credenciais SMTP no repositório.

## Instalação

Clone o repositório:

```bash
git clone https://github.com/DaviSouza11/ClinicaPJR.git
cd ClinicaPJR
```

Baixe as dependências e gere o projeto:

```bash
mvn clean package
```

## Execução

Depois de configurar o banco e as variáveis de ambiente, execute:

```bash
mvn spring-boot:run
```

Por padrão, a aplicação ficará disponível em:

```text
http://localhost:8080
```

## API

### Cadastrar usuário

```http
POST /usuarios/cadastro
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "email": "usuario@example.com",
  "senha": "senha-segura",
  "perfil": "PACIENTE"
}
```

Formato atual da resposta:

```json
{
  "idUsuario": 1,
  "codigoGerado": 123456
}
```

> **Atenção:** o código de verificação não deve ser devolvido pela API em uma versão publicada. Ele deve ser armazenado com validade limitada e enviado exclusivamente ao endereço de e-mail informado.

## Segurança

O projeto já utiliza:

- BCrypt para proteção das senhas.
- Custo de hash configurado como `12`.
- Prepared statements nas operações JDBC.
- Variáveis de ambiente para credenciais configuradas pelo Spring.
- Conta inativa durante o cadastro inicial.
- Envio de código de verificação por SMTP.

Antes de disponibilizar a aplicação publicamente, ainda será necessário:

- implementar Spring Security;
- criar autenticação e autorização por perfil;
- impedir a devolução do código de verificação na resposta;
- armazenar o código com hash, validade e limite de tentativas;
- validar e normalizar e-mail, CPF, CRM, datas e horários;
- evitar mensagens internas do banco nas respostas ao cliente;
- remover credenciais fixas da `ConnectionFactory`;
- restringir acesso a dados sensíveis.

## Limitações atuais

O repositório ainda precisa de alguns ajustes antes de compilar e executar corretamente:

- `EmailService` possui uma importação de biblioteca que não está declarada no `pom.xml`.
- O método de envio de código está declarado com uma capitalização e chamado com outra.
- Existem importações duplicadas no serviço de e-mail.
- `Usuario` precisa de um construtor vazio ou configuração equivalente para desserialização pelo Jackson.
- `UsuarioDAO` mistura o `DataSource` do Spring com uma conexão JDBC criada manualmente.
- `ConnectionFactory` mantém usuário e senha do banco no código.
- A geração do código pode produzir mais de seis dígitos.
- O código de verificação não é persistido e não possui prazo de validade.
- Ainda não existe endpoint para confirmar o código e ativar a conta.
- O login compara diretamente os valores recebidos e ainda não utiliza a verificação BCrypt.
- Médico, paciente e agenda ainda não possuem controllers REST.
- Não existem testes automatizados ou tratamento global de erros.

## Testes planejados

Casos importantes para cobertura:

- cadastro com e-mail válido;
- tentativa de cadastro com e-mail duplicado;
- armazenamento da senha somente como hash;
- confirmação com código correto, incorreto e expirado;
- login com credenciais válidas e inválidas;
- cadastro de médico com CRM duplicado;
- cadastro de paciente com CPF duplicado;
- bloqueio de consultas conflitantes;
- cancelamento e alteração de status da consulta;
- falha controlada no envio de e-mail.

## Aprendizados demonstrados

- desenvolvimento de APIs com Spring Boot;
- organização em controller, service, DAO, DTO e model;
- programação orientada a objetos e herança;
- acesso a dados com JDBC;
- consultas preparadas;
- modelagem de banco relacional;
- proteção de senhas com BCrypt;
- configuração por variáveis de ambiente;
- integração com serviço SMTP;
- construção incremental de um domínio de clínica médica.

## Autor

**Davi Belo de Souza**

- [GitHub](https://github.com/DaviSouza11)
- [LinkedIn](https://www.linkedin.com/in/davi-belo-de-souza-6bb351262)
