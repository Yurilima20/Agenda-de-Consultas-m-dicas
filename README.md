# 🏥 Spring Boot API - Consultas e Agendamentos

Este projeto é uma **API REST** desenvolvida em **Spring Boot** com foco em **boas práticas de desenvolvimento**.  
A aplicação permite o cadastro, atualização e gerenciamento de **médicos**, **pacientes** e **consultas**, incluindo regras de negócio para manter a consistência dos dados.

---

## 🚀 Tecnologias e bibliotecas utilizadas

- **Java + Spring Boot**
- **Spring Data JPA** → persistência e acesso ao banco de dados
- **Spring Security + JWT** → autenticação e autorização
- **Bean Validation** → validação de dados obrigatórios e regras de consistência
- **Postman** → testes da API
- **JPA/Hibernate** → mapeamento objeto-relacional
- **JUnit + Mockito** → testes unitários isolados

---

## 📌 Funcionalidades principais

- **Cadastro e atualização** de médicos e pacientes.
- **Agendamento de consultas** com controle de horários disponíveis.
- **Regras de negócio automatizadas**, garantindo consistência nos agendamentos.
- **Validações de segurança (JWT)**: apenas usuários autenticados podem criar, excluir ou atualizar registros.
- **Relações entre entidades**: exclusão de consultas, médicos ou pacientes respeita as regras definidas no domínio.

---

## 🛠️ Arquitetura e boas práticas aplicadas

- **Controllers** → expõem os endpoints da API de forma clara e objetiva.  
- **Services** → centralizam a lógica de negócios e coordenam os fluxos da aplicação.  
- **Repositories** → responsáveis pela persistência com **Spring Data JPA**.  
- **Security** → controle de autenticação/autorização utilizando **Spring Security + JWT**.  
- **Bean Validation** → garante dados não nulos, formatos válidos e consistência antes da persistência.  

---

## ✅ Regras de Negócio e Validações

Foi implementado um padrão de **validação flexível** através de uma interface `ValidadorDeAgendas`.  
- Essa interface contém o método `validar`, que é implementado por diferentes validadores.  
- No **Service**, é injetada uma **lista de validadores**, que são chamados em um loop (`forEach`) sempre que uma operação de agendamento é executada.  
- Vantagens:
  - Fácil manutenção: adicionar ou remover uma regra não exige alterações no Service.
  - Flexibilidade: cada regra de negócio fica isolada em sua própria classe.
  - Testabilidade: cada validador pode ser testado de forma independente.

---

## 🔐 Segurança

- Implementação de **JWT (JSON Web Token)** para autenticação.
- Apenas usuários autenticados podem:
  - Criar médicos, pacientes ou consultas
  - Atualizar ou excluir registros
- Usuários não autenticados possuem acesso restrito.

---

## 🧪 Testes

Os testes foram realizados em dois níveis:  

1. **Testes de API com Postman**  
   - Verificação do comportamento dos endpoints em cenários de sucesso e erro.  
   - Exemplos: cadastro de médicos, pacientes e consultas, autenticação com JWT, exclusão de consultas inválidas, etc.  

2. **Testes unitários com JUnit + Mockito**  
   - Testes **isolados** (não integrados) dos **Controllers** e **Services**.  
   - Uso de mocks para simular dependências como `Repositories` e validar o comportamento esperado.  
   - Verificação do retorno de **códigos HTTP corretos** em diferentes situações.  
   - Benefícios:
     - Independência do banco de dados ou de outros serviços externos.
     - Execução rápida e confiável.
     - Foco em regras de negócio específicas.

---

## 📂 Estrutura do Projeto

**src/main/java**

**└── com.example.consultas**

**├── controller # Endpoints REST**

**├── service # Regras de negócio**

**├── repository # Acesso ao banco (Spring Data JPA)**

**├── security # Configurações de autenticação/autorizações**

**├── validation # Regras de validação isoladas**

**└── model # Entidades (Médico, Paciente, Consulta)**

