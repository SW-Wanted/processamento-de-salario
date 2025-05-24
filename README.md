# 💼 Salary Processor

**2ª Prova Parcial – Programação II**  
Este projeto simula um sistema de **processamento de salários** para uma empresa, utilizando a linguagem Java. Desenvolvido no contexto académico da disciplina de Programação II, o sistema aplica os fundamentos da linguagem Java, como estruturas de controle, uso de métodos, arrays e listas, além da organização modular por meio de pacotes.

---

## 🥇 Objectivo
- Solução prática em Java

---

## 📚 Índice
- [📖 Introdução](#-introdução)
- [✨ Funcionalidades](#-funcionalidades)
- [📦 Estrutura do Projeto](#-estrutura-do-projeto)
---

## 📖 Introdução
Neste projeto, uma empresa precisa de um sistema interno para **cadastrar colaboradores**, **gerir suas funções**, e **gerar relatórios** organizados por ordem de admissão.

O sistema simula as seguintes operações:
- Cadastro e atualização de colaboradores
- Atribuição de funções com salário base e bônus
- Relatórios com ordenação cronológica
- Validações essenciais como emails únicos e datas corretas

---

## ✨ Funcionalidades

### 👥 Colaboradores
- **Cadastrar**: Número, nome, morada, nascimento, função, admissão, email único.
- **Atualizar**: Permite editar os dados de um colaborador.
- **Desativar**: Marca o colaborador como inativo (não remove).
- **Pesquisar**: Exibe os dados completos de um colaborador.
- **Imprimir**: Lista todos os colaboradores ativos.

### 🧑‍💼 Funções
- **Criar**: Define função com código, nome, salário base e bônus.
- **Eliminar**: Só pode ser removida se não estiver em uso.
- **Imprimir**: Lista todas as funções existentes.

### 📋 Relatórios
- **Listagem por data de admissão**: Mostra colaboradores em ordem cronológica de entrada.

---

## 📦 Estrutura do Projeto

```bash
src/
└── isptec/
    └── pii_tp2/
        └── grupo4/
            └── salaryProcessor/
                ├── Colaborador.java
                ├── Funcao.java
                ├── SalaryProcessor.java