/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.util.Scanner;

/**
 *
 * @author Emanuel
 */
public class SalaryProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Colaboradores");
            System.out.println("2. Funcoes");
            System.out.println("3. Relatorios");
            System.out.println("4. Sair");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opcao: ");
            try {
                op = sc.nextInt();
                sc.nextLine(); // Limpa buffer
            } catch (Exception e) {
                System.out.println("Entrada invalida!");
                sc.nextLine();
                op = 0;
            }
            switch (op) {
                case 1 -> submenuColaboradores(sc);
                case 2 -> submenuFuncoes(sc);
                case 3 -> submenuRelatorios(sc);
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao Invalida!");
            }
        } while (op != 4);
        sc.close();
    }

    private static void submenuColaboradores(Scanner sc) {
        int op1;
        boolean sair = false;
        while (!sair) {
            System.out.println("SUBMENU COLABORADORES");
            System.out.println("1. Cadastrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Desactivar");
            System.out.println("4. Pesquisar");
            System.out.println("5. Imprimir");
            System.out.println("6. Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opcao: ");
            try {
                op1 = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada invalida!");
                sc.nextLine();
                op1 = 0;
            }
            switch (op1) {
                case 1 -> {
                    if (Colaborador.Cadastrar())
                        System.out.println("Colaborador cadastrado com sucesso!");
                }
                case 2 -> {
                    Colaborador.Imprimir();
                    System.out.print("Informe o numero do colaborador a actualizar: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    Colaborador c = Colaborador.Pesquisar(numero);
                    if (c != null) {
                        Colaborador.Actualizar(c);
                        System.out.println("Colaborador actualizado com sucesso!");
                    } else
                        System.out.println("Colaborador nao encontrado.");
                }
                case 3 -> Colaborador.Desactivar();
                case 4 -> {
                    System.out.print("Informe o numero do colaborador a pesquisar: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    Colaborador c = Colaborador.Pesquisar(numero);
                    if (c != null && c.activo) {
                        System.out.println("Colaborador encontrado:");
                        Colaborador.Imprimir();
                    } else {
                        System.out.println("Colaborador nao encontrado ou inativo.");
                    }
                }
                case 5 -> Colaborador.Imprimir();
                case 6 -> sair = true;
                default -> System.out.println("Opcao invalida!");
            }
        }
    }

    private static void submenuFuncoes(Scanner sc) {
        int op1;
        boolean sair = false;
        while (!sair) {
            System.out.println("SUBMENU FUNCOES");
            System.out.println("1. Criar");
            System.out.println("2. Eliminar");
            System.out.println("3. Imprimir");
            System.out.println("4. Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opcao: ");
            try {
                op1 = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada invalida!");
                sc.nextLine();
                op1 = 0;
            }
            switch (op1) {
                case 1 -> {
                    if (Funcao.Criar())
                        System.out.println("Funcao criada com sucesso!");
                }
                case 2 -> {
                    Funcao.Imprimir();
                    System.out.print("Informe o codigo da funcao a eliminar: ");
                    int codigo = 0;
                    try {
                        codigo = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Codigo invalido!");
                        sc.nextLine();
                        break;
                    }
                    Funcao f = Funcao.Pesquisar(codigo);
                    if (f != null) {
                        Funcao.funcoes.remove(f);
                        System.out.println("Funcao eliminada com sucesso!");
                    } else {
                        System.out.println("Funcao nao encontrada.");
                    }
                }
                case 3 -> Funcao.Imprimir();
                case 4 -> sair = true;
                default -> System.out.println("Opcao invalida!");
            }
        }
    }

    private static void submenuRelatorios(Scanner sc) {
        int op1;
        boolean sair = false;
        while (!sair) {
            System.out.println("SUBMENU RELATORIOS");
            System.out.println("1. Lista de Colaboradores (por ordem de admissao)");
            System.out.println("2. Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opcao: ");
            try {
                op1 = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada invalida!");
                sc.nextLine();
                op1 = 0;
            }
            switch (op1) {
                case 1 -> Colaborador.Imprimir();
                case 2 -> sair = true;
                default -> System.out.println("Opcao invalida!");
            }
        }
    }
}