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

        // Campo obrigatorio: nome da funcao
        String nomeFuncao;
        do {
            System.out.print("Informe o nome da funcao: ");
            nomeFuncao = sc.nextLine();
            if (!Validador.hasContent(nomeFuncao)) {
                System.out.println("O nome da funcao nao pode ser vazio.");
            } else {
                boolean existe = false;
                for(Funcao existente : funcoes) {
                    if (existente.getNome().equalsIgnoreCase(nomeFuncao)) {
                        System.out.println("Ja existe uma funcao com este nome.");
                        existe = true;
                        break;
                    }
                }
                if (existe) nomeFuncao = "";
            }
        } while (!Validador.hasContent(nomeFuncao));
        f.setNome(nomeFuncao);

        // Campo obrigatorio: salario base > 0
        double salarioBase = -1;
        do {
            System.out.print("Informe o salario base: ");
            try {
                salarioBase = sc.nextDouble();
                if (!Validador.isValorPositivo(salarioBase)) {
                    System.out.println("Salario base deve ser maior que zero.");
                    salarioBase = -1;
                }
            } catch (Exception e) {
                System.out.println("Entrada invalida para o salario base.");
                sc.nextLine();
                salarioBase = -1;
            }
        } while (salarioBase <= 0);
        f.setSalarioBase(salarioBase);

        // Campo obrigatorio: bonus > 0
        double bonus = -1;
        do {
            System.out.print("Informe o bonus: ");
            try {
                bonus = sc.nextDouble();
                if (!Validador.isValorPositivo(bonus)) {
                    System.out.println("Bonus deve ser maior que zero.");
                    bonus = -1;
                }
            } catch (Exception e) {
                System.out.println("Entrada invalida para o bonus.");
                sc.nextLine();
                bonus = -1;
            }
        } while (bonus <= 0);
        f.setBonus(bonus);

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