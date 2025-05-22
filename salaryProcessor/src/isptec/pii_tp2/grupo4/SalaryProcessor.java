/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.util.Scanner;
/**
 *
 * @author hp
 */
public class SalaryProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu();
    }
    
    public static void Menu()
    {
        Scanner input = new Scanner(System.in);
        int op = 0;
        do
        {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Colaboradores");
            System.out.println("2. Funcoes");
            System.out.println("3. Relatorios");
            System.out.println("4. Sair");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opcao: ");
            
            op = input.nextInt();
            switch (op) {
                case 1:
                    Submenu(op);
                    break;
                case 2:
                    Submenu(op);
                    break;
                case 3:
                    Submenu(op);
                    break;
                default:
                    System.out.println("Opcao Invalida!");
            }
        } while (op != 4);
    }
    
    public static void Submenu(int op)
    {
        Scanner input = new Scanner(System.in);
        int op1 = 0;
        do
        {
                switch (op) {
                case 1:
                    System.out.println("1. Cadastrar");
                    System.out.println("2. Actualizar");
                    System.out.println("3. Desactivar");
                    System.out.println("4. Pesquisar");
                    System.out.println("5. Imprimir");
                    System.out.println("6. Voltar");
                    System.out.println("------------------------------");
                    System.out.print("Escolha uma opcao: ");
                    op1 = input.nextInt();
                    objeto.Cadastrar(Usuario);
                    break;
                case 2:
                    System.out.println("1. Criar");
                    System.out.println("2. Eliminar");
                    System.out.println("3. Imprimir");
                    System.out.println("4. Voltar");
                    System.out.println("------------------------------");
                    System.out.print("Escolha uma opcao: ");
                    op1 = input.nextInt();
                    break;
                case 3:
                    System.out.println("1. Lista de Colaboradores (por ordem de admissao)");
                    System.out.println("2. Voltar");
                    System.out.println("------------------------------");
                    System.out.print("Escolha uma opcao: ");
                    op1 = input.nextInt();
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while ((op != 1 && op1 != 6) && (op != 2 && op1 != 4) && (op != 3 && op1 != 2));
    }
    
}
