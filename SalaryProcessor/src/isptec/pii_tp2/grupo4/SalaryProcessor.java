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
        Scanner sc = new Scanner(System.in);
        menuPrincipal(sc);
        sc.close();
    }

    public static void menuPrincipal(Scanner sc) {
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
                default -> System.out.println("Opcao Invalida! Tente novamente.");
            }
        } while (op != 4);
        
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
                    // Verifica se existem funções cadastradas
                    while (Funcao.funcoes.isEmpty()) {
                        System.out.println("Nenhuma funcao cadastrada. E necessario cadastrar pelo menos uma funcao antes de cadastrar um colaborador.");
                        boolean criada = Funcao.Criar(sc);
                        if (!criada) {
                            System.out.println("Falha ao criar funcao. Nao e possivel cadastrar colaborador sem funcao.");
                            break;
                        }
                        System.out.println("Funcao criada com sucesso. Prosseguindo com o cadastro do colaborador.");
                    }
                    // Se existem funções cadastradas, prossegue com o cadastro do colaborador
                    if (!Funcao.funcoes.isEmpty()) {
                        if (Colaborador.Cadastrar(sc)){
                            System.out.println("Colaborador cadastrado com sucesso!");
                        }
                        else{
                            System.out.println("Falha ao cadastrar colaborador.");
                        }
                    }
                }
                case 2 -> {
                    Colaborador.Imprimir();
                    System.out.print("Informe o numero do colaborador a atualizar: ");
                    int numeroAtualizar;
                    try {
                        numeroAtualizar = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida para o número do colaborador.");
                        sc.nextLine(); // Limpa o buffer
                        break;
                    }
                    sc.nextLine(); // Limpa o buffer
                    Colaborador c = Colaborador.Pesquisar(numeroAtualizar);
                    if (c != null) {
                        Colaborador.Actualizar(sc, c); // Passa o Scanner
                        System.out.println("Colaborador atualizado com sucesso!");
                    } else {
                        System.out.println("Colaborador não encontrado.");
                    }
                }
                case 3 -> Colaborador.Desactivar(sc);
                case 4 -> {
                    System.out.println("Pesquisar Colaborador:");
                    System.out.println("1. Por Numero");
                    System.out.println("2. Por Email");
                    System.out.print("Escolha o metodo de pesquisa: ");
                    int opc;
                    try {
                        opc = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Entrada invalida!");
                        sc.nextLine();
                        break;
                    }

                    Colaborador c = null;
                    if (opc == 1) {
                        System.out.print("Informe o numero do colaborador a pesquisar: ");
                        int numeroPesquisar;
                        try {
                            numeroPesquisar = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Entrada inválida para o número.");
                            sc.nextLine();
                            break;
                        }
                        sc.nextLine();
                        c = Colaborador.Pesquisar(numeroPesquisar);
                    } else if (opc == 2) {
                        System.out.print("Informe o email do colaborador a pesquisar: ");
                        String emailPesquisar = sc.nextLine();
                        c = Colaborador.Pesquisar(emailPesquisar);
                    } else {
                        System.out.println("Opção de pesquisa inválida.");
                    }

                    if (c != null) {
                        System.out.println("\n--- Colaborador Encontrado ---");
                        System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s | %-10s%n",
                                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao", "Ativo");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s | %-10s%n",
                                c.getNumero(),
                                c.getNome(),
                                c.getEmail(),
                                c.getMorada(),
                                c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                                c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                                c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A",
                                c.isActivo() ? "Sim" : "Não"
                        );
                    } else {
                        System.out.println("Colaborador nao encontrado.");
                    }
                }
                case 5 -> Colaborador.Imprimir();
                case 6 -> sair = true;
                default -> System.out.println("Opcao invalida! Tente novamente");
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
                    if (Funcao.Criar(sc)) {
                        System.out.println("Função criada com sucesso!");
                    } else {
                        System.out.println("Falha ao criar funcao.");
                    }
                }
                case 2 -> Funcao.Eliminar(sc);
                case 3 -> Funcao.Imprimir();
                case 4 -> sair = true;
                default -> System.out.println("Opcao invalida! Tente novamente");
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
                System.out.println("Entrada invalida! Por favor, digite um numero.");
                sc.nextLine();
                op1 = 0;
            }
            switch (op1) {
                case 1 -> Colaborador.ImprimirPorOrdemAdmissao();
                case 2 -> sair = true;
                default -> System.out.println("Opcao invalida! Tente novamente");
            }
        }
    }
}