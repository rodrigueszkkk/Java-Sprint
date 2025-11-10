# 🏥 ConectaReab - API de Clínica Médica

Este projeto é o **backend (API Restful)** para um sistema de gestão de clínica médica.

---

## 🎯 Objetivo

O objetivo principal é **organizar, simplificar e digitalizar** o processo de atendimento em uma clínica.  
A API permite o **gerenciamento completo** do cadastro de pacientes, do corpo clínico (médicos) e do agendamento de consultas.

---

## ⚙️ Recursos da API

A API é construída em **Java** e está estruturada em **três recursos principais**:

---

### 🧍‍♀️ 1. Pacientes

Endpoints para o gerenciamento completo do cadastro de pacientes:

| Método | Endpoint | Descrição |
|:-------|:----------|:-----------|
| **POST** | `/pacientes` | Cadastra um novo paciente |
| **GET** | `/pacientes` | Lista todos os pacientes cadastrados |
| **GET** | `/pacientes/{id}` | Busca um paciente específico pelo ID |
| **PUT** | `/pacientes/{id}` | Atualiza os dados de um paciente existente |
| **DELETE** | `/pacientes/{id}` | Remove um paciente do sistema |

---

### 🩺 2. Médicos

Endpoints para o gerenciamento do cadastro de médicos e suas especialidades:

| Método | Endpoint | Descrição |
|:-------|:----------|:-----------|
| **POST** | `/medicos` | Cadastra um novo médico |
| **GET** | `/medicos` | Lista todos os médicos cadastrados |
| **GET** | `/medicos/{id}` | Busca um médico específico pelo ID |
| **PUT** | `/medicos/{id}` | Atualiza os dados de um médico existente |
| **DELETE** | `/medicos/{id}` | Remove um médico do sistema |

---

### 📅 3. Consultas

Endpoints para as operações de agendamento e gerenciamento de consultas:

| Método | Endpoint | Descrição |
|:-------|:----------|:-----------|
| **POST** | `/consultas` | Agenda (cadastra) uma nova consulta |
| **GET** | `/consultas` | Lista todas as consultas agendadas |
| **GET** | `/consultas/{id}` | Busca uma consulta específica pelo ID |
| **PUT** | `/consultas/{id}` | Atualiza os dados de uma consulta existente |
| **DELETE** | `/consultas/{id}` | Cancela (remove) uma consulta do sistema |

---

## 👨‍💻 Equipe

- **Gabriel Costa Solano** — RM562325  
- **Kaiky Pereira Rodrigues Da Silva** — RM564578  
- **Leandro Guarido** — RM561760  

---

