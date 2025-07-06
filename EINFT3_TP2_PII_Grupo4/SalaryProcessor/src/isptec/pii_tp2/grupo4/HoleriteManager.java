/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class HoleriteManager {
    // Lista para armazenar todos os holerites gerados
    private ArrayList<Holerite> holeritesGerados;

    public HoleriteManager() {
        this.holeritesGerados = new ArrayList<>();
    }
    
    public void gerarHolerite(Scanner sc) {
        System.out.println("\n--- GERAR HOLERITE PARA COLABORADOR ---");
        if (Colaborador.colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado. Cadastre um colaborador primeiro.");
            return;
        }
        Colaborador.ListarColaboradores();
        System.out.print("Digite o ID do colaborador para gerar o holerite: ");
        int idColaborador;
        try {
            idColaborador = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("ID invalido! Por favor, digite um numero inteiro.");
            sc.nextLine();
            return;
        }

        Colaborador colaboradorEncontrado = Colaborador.colaboradores.stream()
                .filter(c -> c.getNumero() == idColaborador && c.isActivo()) // Apenas colaboradores ativos
                .findFirst()
                .orElse(null);

        if (colaboradorEncontrado == null) {
            System.out.println("Colaborador com ID " + idColaborador + " nao encontrado ou inativo.");
            return;
        }

        if (colaboradorEncontrado.getFuncao() == null) {
            System.out.println("Colaborador " + colaboradorEncontrado.getNome() + " nao tem uma funcao atribuida. Nao eh possivel gerar holerite.");
            return;
        }

        // --- Período de Referência ---
        LocalDate periodoReferencia = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite o mes de referencia (MM/YYYY) para o holerite: ");
            String mesAnoStr = sc.nextLine();
            try {
                String[] partes = mesAnoStr.split("/");
                if (partes.length == 2) {
                    int mes = Integer.parseInt(partes[0]);
                    int ano = Integer.parseInt(partes[1]);
                    if (mes >= 1 && mes <= 12 && ano >= 1900 && ano <= LocalDate.now().getYear()) {
                        periodoReferencia = LocalDate.of(ano, mes, 1);
                        dataValida = true;
                    } else {
                        System.out.println("Mes ou ano invalidos. Use MM/YYYY.");
                    }
                } else {
                    System.out.println("Formato invalido. Use MM/YYYY (ex: 06/2025).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato numerico invalido para o mes/ano. Use MM/YYYY.");
            } catch (Exception e) {
                System.out.println("Erro ao validar a data. Tente novamente.");
            }
        }
        
        // Verificar se já existe um holerite para este colaborador e período
        for (Holerite h : this.holeritesGerados) { 
            if (h.getColaborador().getNumero() == idColaborador &&
                h.getPeriodoReferencia().getMonth() == periodoReferencia.getMonth() &&
                h.getPeriodoReferencia().getYear() == periodoReferencia.getYear()) {
                System.out.println("Ja existe um holerite gerado para este colaborador e periodo de referencia.");
                System.out.println("Holerite existente:");
                System.out.println(h.toString());
                return;
            }
        }

        // --- Obter dados da Função no momento do processamento ---
        double salarioBaseColaborador = colaboradorEncontrado.getFuncao().getSalarioBase();
        double bonusColaborador = colaboradorEncontrado.getFuncao().getBonus();

        // --- Calcular Salário Bruto ---
        double salarioBruto = SalaryCalculator.calcularSalarioBruto(salarioBaseColaborador, bonusColaborador);

        // --- Calcular Deduções ---
        double valorIRT = SalaryCalculator.calcularIRT(salarioBruto);
        double valorINSS = SalaryCalculator.calcularINSS(salarioBruto);

        // --- Calcular Salário Líquido ---
        double salarioLiquido = salarioBruto - valorIRT - valorINSS;

        // --- Criar e Armazenar o Holerite ---
        Holerite novoHolerite = new Holerite(
                colaboradorEncontrado,
                periodoReferencia,
                salarioBaseColaborador,
                bonusColaborador,
                salarioBruto,
                valorIRT,
                valorINSS,
                salarioLiquido,
                LocalDate.now() // Data de processamento é a data atual
        );

        this.holeritesGerados.add(novoHolerite); 
        System.out.println("\nHolerite gerado com sucesso para " + colaboradorEncontrado.getNome() + "!");
        System.out.println(novoHolerite.toString());
    }

    /*
     Lista todos os holerites gerados ou holerites de um colaborador especifico.
     */
    public void listarHolerites(Scanner sc) {
        System.out.println("\n--- LISTA DE HOLERITES GERADOS ---");
        if (holeritesGerados.isEmpty()) {
            System.out.println("Nenhum holerite foi gerado ainda.");
            return;
        }

        System.out.print("Deseja listar holerites de um colaborador especifico? (s/n): ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            Colaborador.ListarColaboradores();
            System.out.print("Digite o ID do colaborador: ");
            int idColaborador;
            try {
                idColaborador = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ID invalido! Por favor, digite um numero inteiro.");
                sc.nextLine();
                return;
            }

            boolean encontrado = false;
            for (Holerite h : holeritesGerados) { 
                if (h.getColaborador().getNumero() == idColaborador) {
                    System.out.println(h.toString());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Nenhum holerite encontrado para o colaborador com ID " + idColaborador + ".");
            }
        } else {
            ArrayList<Holerite> holeritesOrdenados = new ArrayList<>(holeritesGerados);
            holeritesOrdenados.sort((h1, h2) -> {
                int comparacaoColaborador = Integer.compare(h1.getColaborador().getNumero(), h2.getColaborador().getNumero());
                if (comparacaoColaborador != 0) {
                    return comparacaoColaborador;
                }
                return h1.getPeriodoReferencia().compareTo(h2.getPeriodoReferencia());
            });

            for (Holerite h : holeritesOrdenados) {
                System.out.println(h.toString());
            }
        }
    }
    
    public void exportarHoleritesParaTXT(Scanner sc) {
        System.out.println("\n--- EXPORTAR RELATORIO DE HOLERITES PARA TXT ---");
        if (holeritesGerados.isEmpty()) {
            System.out.println("Nenhum holerite gerado para exportar.");
            return;
        }

        String dataAtual = java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH'h'mm"));
        String pasta = "reports/holerites/";
        String ext = ".txt";
        String nomeArquivo = pasta + "holerite_" + dataAtual + ext;

        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        ArrayList<Holerite> holeritesAExportar = new ArrayList<>();
        boolean filtrarPorColaborador = false;
        int idColaborador = -1;

        System.out.print("Deseja exportar holerites de um colaborador especifico? (s/n): ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            filtrarPorColaborador = true;
            Colaborador.ListarColaboradores();
            System.out.print("Digite o ID do colaborador: ");
            try {
                idColaborador = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ID invalido! Por favor, digite um numero inteiro. Exportando todos os holerites.");
                sc.nextLine();
                filtrarPorColaborador = false; // Desativa o filtro se a entrada for inválida
            }
        }

        // Filtra e ordena os holerites
        if (filtrarPorColaborador) {
            for (Holerite h : holeritesGerados) {
                if (h.getColaborador().getNumero() == idColaborador) {
                    holeritesAExportar.add(h);
                }
            }
            if (holeritesAExportar.isEmpty()) {
                System.out.println("Nenhum holerite encontrado para o colaborador com ID " + idColaborador + ". Nao foi gerado arquivo.");
                return;
            }
        } else {
            holeritesAExportar.addAll(holeritesGerados);
        }

        // Ordena os holerites para a exportação
        holeritesAExportar.sort((h1, h2) -> {
            int comparacaoColaborador = Integer.compare(h1.getColaborador().getNumero(), h2.getColaborador().getNumero());
            if (comparacaoColaborador != 0) {
                return comparacaoColaborador;
            }
            return h1.getPeriodoReferencia().compareTo(h2.getPeriodoReferencia());
        });

        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("=================================== RELATORIO: HOLERITES GERADOS ====================================");
            if (filtrarPorColaborador) {
                writer.println("Holerites para o Colaborador ID: " + idColaborador + " - " + holeritesAExportar.get(0).getColaborador().getNome());
            } else {
                writer.println("Todos os Holerites");
            }
            writer.println("--------------------------------------------------------------------------------------------------------------------------");
            writer.printf("%-4s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-12s |\n",
                    "ID", "Colaborador", "Periodo", "Base", "Bonus", "Bruto", "IRT", "INSS", "Liquido");
            writer.println("-----|----------------------|------------|------------|------------|------------|------------|------------|--------------|");

            for (Holerite h : holeritesAExportar) {
                writer.printf("%-4d | %-20s | %-10s | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-12.2f |\n",
                        h.getColaborador().getNumero(),
                        h.getColaborador().getNome(),
                        h.getPeriodoReferencia().format(monthYearFormatter),
                        h.getSalarioBase(),
                        h.getBonus(),
                        h.getSalarioBruto(),
                        h.getValorIRT(),
                        h.getValorINSS(),
                        h.getSalarioLiquido());
            }
            writer.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Relatorio de holerites exportado com sucesso para " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            System.err.println("Verifique as permissoes de escrita ou se o caminho esta correto.");
        }
    }
}
