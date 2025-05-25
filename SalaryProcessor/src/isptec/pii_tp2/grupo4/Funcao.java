/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emanuel
 */
public class Funcao {

    private int codigo;
    private String nome;
    private double salarioBase;
    private double bonus;

    public static ArrayList<Funcao> funcoes = new ArrayList<>();
    
    // Construtor padrão
    public Funcao() {
    }
    //Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    
    public static boolean Criar(Scanner sc) {
        
        Funcao f = new Funcao();

        f.setCodigo(funcoes.isEmpty() ? 1 : funcoes.get(funcoes.size() - 1).getCodigo() + 1);

        System.out.print("Informe o nome da funcao: ");
        String nomeFuncao = sc.nextLine();
        // Validação para nome da função
        if (nomeFuncao.trim().isEmpty()) {
            System.out.println("O nome da função não pode ser vazio.");
            return false;
        }
        // Verifica se a função com este nome já existe
        for(Funcao existente : funcoes) {
            if (existente.getNome().equalsIgnoreCase(nomeFuncao)) {
                System.out.println("Já existe uma função com este nome.");
                return false;
            }
        }
        f.setNome(nomeFuncao);

        System.out.print("Informe o salário base: ");
        try {
            f.setSalarioBase(sc.nextDouble());
            if (f.getSalarioBase() < 0) {
                System.out.println("Salário base não pode ser negativo.");
                sc.nextLine(); // Limpa o buffer
                return false;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida para o salário base.");
            sc.nextLine(); // Limpa o buffer
            return false;
        }

        System.out.print("Informe o bónus: ");
        try {
            f.setBonus(sc.nextDouble());
            if (f.getBonus() < 0) {
                System.out.println("Bónus não pode ser negativo.");
                sc.nextLine(); // Limpa o buffer
                return false;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida para o bónus.");
            sc.nextLine(); // Limpa o buffer
            return false;
        }
        sc.nextLine(); // Limpar buffer

        return funcoes.add(f);
    }

    public static Funcao Pesquisar(int codigo) {
        for (Funcao f : funcoes) {
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    public static boolean Eliminar(Scanner sc) {
        Imprimir();
        System.out.print("Informe o código da função a eliminar: ");
        int codigo;
        try {
            codigo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida para o código da função.");
            sc.nextLine(); // Limpa o buffer
            return false;
        }
        sc.nextLine(); // Limpar buffer

        Funcao funcaoParaRemover = Pesquisar(codigo);
        if (funcaoParaRemover == null) {
            System.out.println("Função não encontrada.");
            return false;
        }

        // Verifica se a função está associada a algum colaborador
        for (Colaborador c : Colaborador.colaboradores) {
            // Usa o método equals de Funcao para comparar objetos
            if (c.getFuncao() != null && c.getFuncao().equals(funcaoParaRemover)) {
                System.out.println("Não é possível eliminar a função. Está associada ao colaborador: " + c.getNome());
                return false;
            }
        }
        // Se não estiver associada a nenhum colaborador, pode remover
        funcoes.remove(funcaoParaRemover);
        System.out.println("Função eliminada com sucesso!");
        return true;
    }

    public static void Imprimir() {
        if (funcoes.isEmpty()) {
            System.out.println("Nenhuma funcao cadastrada.");
            return;
        }

        System.out.println("\n--- LISTA DE FUNCOES ---");
        System.out.printf("%-8s | %-20s | %-15s | %-10s\n", "Codigo", "Nome", "Salario Base", "Bonus");
        System.out.println("-------- | -------------------- | --------------- | ----------");

        for (Funcao f : funcoes) {
            System.out.printf("%-8d | %-20s | %-15.2f | %-10.2f\n",
                    f.getCodigo(), f.getNome(), f.getSalarioBase(), f.getBonus());
        }
    }

}