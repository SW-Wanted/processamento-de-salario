
package isptec.pii_tp2.grupo4;

import java.util.Scanner;
import java.util.InputMismatchException; 


public class SalaryProcessor {
    private static HoleriteManager holeriteManager = new HoleriteManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menuPrincipal(sc);
        sc.close();
    }

    public static void menuPrincipal(Scanner sc) {
        int op;
        boolean sair = false; 
        do {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("|           MENU PRINCIPAL              |");
            System.out.println("=========================================");
            System.out.println("|  1. Colaboradores                     |");
            System.out.println("|  2. Funcoes                           |");
            System.out.println("|  3. Processamento de Salarios         |");
             System.out.println("|  4. Relatorios                        |");
            System.out.println("|  5. Sair                              |");
            System.out.println("=========================================");
            System.out.print("Escolha uma opcao: ");
            try {
                op = sc.nextInt();
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("Entrada invalida!");
                sc.nextLine();
                op = 0;
            }
            switch (op) {
                case 1 -> submenuColaboradores(sc);
                case 2 -> submenuFuncoes(sc);
                case 3 -> submenuProcessamentoSalarios(sc);
                case 4 -> submenuRelatorios(sc);
                case 5 -> {
                    System.out.println("Saindo...");
                    sair = true;
                }
                default -> System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (!sair);
    }

    private static void submenuColaboradores(Scanner sc) {
        int op1;
        boolean sair = false;
        while (!sair) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("|        SUBMENU COLABORADORES          |");
            System.out.println("=========================================");
            System.out.println("|  1. Cadastrar                         |");
            System.out.println("|  2. Actualizar                        |");
            System.out.println("|  3. Desactivar                        |");
            System.out.println("|  4. Pesquisar                         |");
            System.out.println("|  5. Imprimir                          |");
            System.out.println("|  6. Voltar                            |");
            System.out.println("=========================================");
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
                    while (Funcao.funcoes.isEmpty()) {
                        System.out.println(
                                "Nenhuma funcao cadastrada. E necessario cadastrar pelo menos uma funcao antes de cadastrar um colaborador.");
                        System.out.print("Deseja criar uma nova funcao agora? (s/n): ");
                        String resposta = sc.nextLine().trim().toLowerCase();
                        if (resposta.equals("s")) {
                            boolean criada = Funcao.Criar(sc);
                            if (!criada) {
                                System.out.println(
                                        "Falha ao criar funcao. Nao e possivel cadastrar colaborador sem funcao.");
                                break;
                            }
                            System.out
                                    .println("Funcao criada com sucesso. Prosseguindo com o cadastro do colaborador.");
                        } else {
                            System.out.println("Nao e possivel cadastrar colaborador sem funcao.");
                            break;
                        }
                    }
                    if (!Funcao.funcoes.isEmpty()) {
                        if (Colaborador.Cadastrar(sc)) {
                            System.out.println("Colaborador cadastrado com sucesso!");
                        } else {
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
                        System.out.println("Entrada invalida para o numero do colaborador.");
                        sc.nextLine();
                        break;
                    }
                    sc.nextLine();
                    Colaborador c = Colaborador.Pesquisar(numeroAtualizar);
                    if (c != null) {
                        Colaborador.Actualizar(sc, c);
                        System.out.println("Colaborador atualizado com sucesso!");
                    } else {
                        System.out.println("Colaborador nao encontrado.");
                    }
                }
                case 3 -> Colaborador.Desactivar(sc);
                case 4 -> {
                    System.out.println();
                    System.out.println("=========================================");
                    System.out.println("|      PESQUISAR COLABORADOR            |");
                    System.out.println("=========================================");
                    System.out.println("|  1. Por Numero                        |");
                    System.out.println("|  2. Por Email                         |");
                    System.out.println("=========================================");
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
                    switch (opc) {
                        case 1 -> {
                            System.out.print("Informe o numero do colaborador a pesquisar: ");
                            int numeroPesquisar;
                            try {
                                numeroPesquisar = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Entrada invalida para o numero.");
                                sc.nextLine();
                                break;
                            }
                            sc.nextLine();
                            c = Colaborador.Pesquisar(numeroPesquisar);
                    }
                        case 2 -> {
                            System.out.print("Informe o email do colaborador a pesquisar: ");
                            String emailPesquisar = sc.nextLine();
                            c = Colaborador.Pesquisar(emailPesquisar);
                    }
                        default -> System.out.println("Opcao de pesquisa invalida.");
                    }
                    if (c != null) {
                        System.out.println(
                                "================================================= COLABORADOR ENCONTRADO ========================================================");
                        System.out.printf("%-4s | %-20s | %-25s | %-15s | %-12s | %-15s | %-10s | %-5s |\n",
                                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao", "Ativo");
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-4d | %-20s | %-25s | %-15s | %-12s | %-15s | %-10s | %-5s |\n",
                                c.getNumero(),
                                c.getNome(),
                                c.getEmail(),
                                c.getMorada(),
                                c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                                c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                                c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A",
                                c.isActivo() ? "Sim" : "Nao");
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
            System.out.println();
            System.out.println("=========================================");
            System.out.println("|           SUBMENU FUNCOES             |");
            System.out.println("=========================================");
            System.out.println("|  1. Criar                             |");
            System.out.println("|  2. Eliminar                          |");
            System.out.println("|  3. Imprimir                          |");
            System.out.println("|  4. Voltar                            |");
            System.out.println("=========================================");
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
                        System.out.println("Funcao criada com sucesso!");
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

    private static void submenuProcessamentoSalarios(Scanner sc) { 
        int op;
        boolean sair = false;
        while (!sair) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("|     SUBMENU PROCESSAMENTO SALARIAL    |");
            System.out.println("=========================================");
            System.out.println("|  1. Gerar Holerite para Colaborador   |");
            System.out.println("|  2. Listar Holerites Gerados          |");
            System.out.println("|  3. Voltar                            |");
            System.out.println("=========================================");
            System.out.print("Escolha uma opcao: ");
            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida! Por favor, digite um numero.");
                sc.nextLine();
                op = 0;
            }

            switch (op) {
                case 1 -> holeriteManager.gerarHolerite(sc);
                case 2 -> holeriteManager.listarHolerites(sc);
                case 3 -> sair = true;
                default -> System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }
    
    private static void submenuRelatorios(Scanner sc) {
        int op1;
        boolean sair = false;
        while (!sair) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("|         SUBMENU RELATORIOS            |");
            System.out.println("=========================================");
            System.out.println("|  1. Lista de Colaboradores            |");
            System.out.println("|  2. Lista de Holerites                |"); 
            System.out.println("|  3. Exportar Colaboradores (TXT)      |"); 
            System.out.println("|  4. Exportar Holerites (TXT)          |"); 
            System.out.println("|  5. Voltar                            |"); 
            System.out.println("=========================================");
            System.out.print("Escolha uma opcao: ");
            try {
                op1 = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida! Por favor, digite um numero.");
                sc.nextLine();
                op1 = 0;
            }
            switch (op1) {
                case 1 -> Colaborador.ImprimirPorOrdemAdmissao();
                case 2 -> holeriteManager.listarHolerites(sc);
                case 3 -> Colaborador.ExportarParaTXT(sc);     
                case 4 -> holeriteManager.exportarHoleritesParaTXT(sc); 
                case 5 -> sair = true;
                default -> System.out.println("Opcao invalida! Tente novamente");
            }
        }
    }
}
