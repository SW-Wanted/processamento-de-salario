# 💼 Salary Processor

**2ª Prova Parcial – Programação II**  
Este projeto simula um sistema de **processamento de salários** para uma empresa, utilizando a linguagem Java. Desenvolvido no contexto académico da disciplina de Programação II, o sistema aplica os fundamentos da linguagem Java, como estruturas de controle, uso de métodos, arrays e listas, além da organização modular por meio de pacotes.

---

## 🥇 Objectivo
- Solução prática em Java para gestão de colaboradores, funções e processamento salarial, incluindo importação/exportação de dados.

---

## 📚 Índice
- [📖 Introdução](#-introdução)
- [✨ Funcionalidades](#-funcionalidades)
- [📦 Estrutura do Projeto](#-estrutura-do-projeto)
- [💾 Importação/Exportação](#-importaçãoexportação)
- [🧾 Processamento de Holerite](#-processamento-de-holerite)
---

## 📖 Introdução
Neste projeto, uma empresa precisa de um sistema interno para **cadastrar colaboradores**, **gerir suas funções**, **processar salários** (holerite), **importar/exportar dados** e **gerar relatórios** organizados por ordem de admissão e processamento.

O sistema simula as seguintes operações:
- Cadastro, atualização, desativação e pesquisa de colaboradores
- Gestão completa de funções (criar, atualizar, eliminar, listar)
- Processamento de salários e geração de holerites
- Importação e exportação de colaboradores e funções em CSV
- Relatórios em TXT de colaboradores e holerites

---

## ✨ Funcionalidades

### 👥 Colaboradores
- **Cadastrar**: Número, nome, morada, nascimento, função, admissão, email único.
- **Atualizar**: Permite editar os dados de um colaborador.
- **Desativar**: Marca o colaborador como inativo (não remove).
- **Pesquisar**: Exibe os dados completos de um colaborador (por número ou email).
- **Imprimir**: Lista todos os colaboradores ativos e exporta relatório em TXT.
- **Importar/Exportar**: Permite importar/exportar colaboradores em CSV, incluindo dados completos da função associada.

### 🧑‍💼 Funções
- **Criar**: Define função com código, nome, salário base e bônus.
- **Atualizar**: Permite editar os dados de uma função existente.
- **Eliminar**: Só pode ser removida se não estiver em uso.
- **Imprimir**: Lista todas as funções existentes.
- **Importar/Exportar**: Permite importar/exportar funções em CSV.

### 🧾 Processamento de Holerite
- **Gerar Holerite**: Calcula salário bruto, descontos (IRT, INSS) e salário líquido para um colaborador em um mês/ano específico.
- **Listar Holerites**: Exibe todos os holerites gerados ou filtra por colaborador.
- **Exportar Holerites**: Gera relatório em TXT de todos os holerites ou de um colaborador específico.

### 💾 Importação/Exportação
- **Colaboradores**: Exporta e importa colaboradores em CSV, incluindo dados completos da função.
- **Funções**: Exporta e importa funções em CSV.
- **Relatórios**: Exporta relatórios de colaboradores e holerites em TXT.

---

## 📦 Estrutura do Projeto

```bash
src/
└── isptec/
    └── pii_tp2/
        └── grupo4/
            ├── Colaborador.java
            ├── Funcao.java
            ├── Holerite.java
            ├── HoleriteManager.java
            ├── SalaryCalculator.java
            ├── SalaryProcessor.java
            ├── Validador.java
```

---

## 💡 Observações

- O sistema garante integridade dos dados ao importar/exportar, associando corretamente colaboradores e funções.
- Todos os relatórios e exportações são gravados em pastas específicas (`exports/`, `reports/`).
- O menu é totalmente interativo via terminal.

---