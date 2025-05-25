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

    public int codigo;
    public String nome;
    public double salarioBase;
    public double bonus;

    public static ArrayList<Funcao> funcoes = new ArrayList<>();

    public static boolean Criar() {
        Scanner sc = new Scanner(System.in);
        Funcao f = new Funcao();

        f.codigo = funcoes.size() + 1;

        System.out.print("Informe o nome: ");
        f.nome = sc.nextLine();

        System.out.print("Informe o salario base: ");
        f.salarioBase = sc.nextDouble();

        System.out.print("Informe o bonus: ");
        f.bonus = sc.nextDouble();

        return funcoes.add(f);
    }

    public static Funcao Pesquisar(int codigo) {
        for (Funcao f : funcoes) {
            if (f.codigo == codigo) {
                return f;
            }
        }
        return null;
    }

    public static boolean Eliminar(int codigo) {
        Funcao f = Pesquisar(codigo);
        if (f != null) {
            funcoes.remove(f);
            System.out.println("Funcao eliminada com sucesso!");
            return true;
        } else {
            System.out.println("Funcao não encontrada.");
            return false;
        }
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
                    f.codigo, f.nome, f.salarioBase, f.bonus);
        }
    }

}
