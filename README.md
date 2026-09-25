# 🎓 Sistema de Gestão Acadêmica (SCA) - Integração RMI & Web Services (SOAP)

Este projeto consiste na evolução de um **Sistema de Gestão Acadêmica** distribuído. A arquitetura conecta um serviço legado baseado em **Java RMI** a uma camada de **Web Services SOAP (JAX-WS)** através do padrão de projeto *Adapter/Facade*.

## 🏗️ Arquitetura do Sistema

A solução foi desenvolvida em camadas para manter o desacoplamento e atender aos requisitos do sistema:

```
+---------------------+         SOAP / HTTP         +---------------------+         Java RMI          +---------------------+
|                     |  ------------------------>  |   WSServer (SOAP)   |  ---------------------> |  Servidor RMI       |
|   Cliente Externo   |   (WSDL: /wssca?wsdl)       |  (sca.WSServer)     |  (Naming.lookup)        |  (sca.Server)       |
|                     |  <------------------------  |  (Cliente RMI)      |  <--------------------- |                     |
+---------------------+                             +---------------------+                         +---------------------+

```

1. **Camada RMI (Serviço Base):** Mantém a gestão de alunos, turmas e matrículas (`AlunoService`, `TurmaService`, `SecretariaService`).

2. **Camada Web Service (`WSServer`):** Atua simultaneamente como **Servidor SOAP** (expondo os recursos via JAX-WS) e **Cliente RMI** (consumindo os dados do servidor RMI base sem alterar as interfaces existentes).

3. **Clientes Externos:** Consomem exclusivamente o contrato WSDL do Web Service (`WSSCA`), sem qualquer acesso direto à camada RMI.

## 📁 Estrutura do Projeto

```
SDI---RMI/
├── lib/                             # Dependências JAX-WS e JAXB (Java 11+)
│   ├── jakarta.jws-api-2.1.0.jar
│   ├── jakarta.xml.bind-api-2.3.3.jar
│   ├── jakarta.xml.ws-api-2.3.3.jar
│   ├── jaxb-impl-2.3.3.jar
│   └── jaxws-rt-2.3.3.jar
├── sca/                             # Pacote principal da aplicação
│   ├── Aluno.java                   # Modelo de Aluno
│   ├── AlunoService.java            # Interface RMI de Aluno
│   ├── AlunoServiceImpl.java        # Implementação RMI de Aluno
│   ├── Turma.java                   # Modelo de Turma
│   ├── TurmaService.java            # Interface RMI de Turma
│   ├── TurmaServiceImpl.java        # Implementação RMI de Turma
│   ├── SecretariaService.java       # Interface RMI de Secretaria
│   ├── SecretariaServiceImpl.java   # Implementação RMI de Secretaria
│   ├── Server.java                  # Servidor Principal RMI
│   ├── Client.java                  # Cliente RMI de testes
│   ├── WSSCA.java                   # Interface SEI do Web Service SOAP
│   ├── WSSCAImpl.java               # Implementação do Web Service (Cliente RMI)
│   └── WSServer.java                # Publicador do Endpoint SOAP
├── Makefile                         # Script de automação de compilação/execução
└── README.md                        # Documentação do projeto

```

## ⚙️ Pré-requisitos

* **JDK 11** ou superior instalado e configurado nas variáveis de ambiente.

* Utilitário **Make** (opcional, mas recomendado).

* Bibliotecas JAR na pasta `lib/` para suporte ao JAX-WS/JAXB.

## 🚀 Como Executar

### 1. Compilação do Projeto

No terminal, na raiz do projeto (`SDI---RMI`), execute:

```
make

```

*(Ou manualmente: `javac -cp "lib/*;." sca/*.java` no Windows)*

### 2. Execução dos Servidores

A ordem de execução deve ser respeitada para a correta resolução dos serviços:

#### **Passo A: Iniciar o Servidor RMI**

Em um terminal (Terminal 1), inicie o serviço RMI:

```
make run-rmi

```

*(Ou manualmente: `java -cp "lib/*;." sca.Server`)*

#### **Passo B: Iniciar o WSServer (Web Service SOAP)**

Em um segundo terminal (Terminal 2), inicie o servidor SOAP:

```
make run-ws

```

*(Ou manualmente: `java -cp "lib/*;." sca.WSServer`)*

## 🔍 Teste e Verificação do Web Service

Após iniciar o `WSServer`, você poderá verificar se o serviço está ativo acessando o WSDL gerado pelo navegador ou por ferramentas de testes API (como **SoapUI** ou **Postman**):

📍 **URL do WSDL:**
`http://localhost:8080/wssca?wsdl`

## 🛠️ Operações Disponíveis no SOAP (`WSSCA`)

| Recurso / Método | Operação SOAP | Descrição | 
 | ----- | ----- | ----- | 
| **Alunos** | `buscarAlunoPorMatricula` | Consulta um aluno específico via RMI | 
| **Alunos** | `listarAlunos` | Retorna a lista completa de alunos | 
| **Turmas** | `buscarTurmaPorCodigo` | Consulta os dados de uma turma | 
| **Turmas** | `listarTurmas` | Retorna a lista de turmas cadastradas | 
| **Secretaria** | `matricularAluno` | Realiza a matrícula de um aluno em uma turma | 

## 🧹 Limpeza de Artefatos

Para remover os arquivos `.class` gerados na compilação:

```
make clean

```