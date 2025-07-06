/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;

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

    // Métodos equals() e hashCode() para garantir comparações corretas 
    public boolean equals(Object obj) {
        // Se o objeto for a própria instância, são iguais
        if (this == obj) {
            return true;
        }
        // Se o objeto for nulo ou de uma classe diferente, não são iguais
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Converte o objeto para Funcao
        Funcao outraFuncao = (Funcao) obj;
        // Compara as funções com base em seus códigos
        return this.codigo == outraFuncao.codigo;
    }
    
    public int hashCode() {
        // Se dois objetos são iguais (equals retorna true), eles DEVEM ter o mesmo hashCode.
        return Objects.hash(codigo); 
    }
    
    public static boolean Criar(Scanner sc) {
        Funcao f = new Funcao();

        f.setCodigo(funcoes.isEmpty() ? 1 : funcoes.get(funcoes.size() - 1).getCodigo() + 1);

        String nomeFuncao;
        do {
            System.out.print("Informe o nome da funcao: ");
            nomeFuncao = sc.nextLine();
            if (!Validator.hasContent(nomeFuncao)) {
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
        } while (!Validator.hasContent(nomeFuncao));
        f.setNome(nomeFuncao);

        // Campo obrigatorio: salario base > 0
        double salarioBase = -1;
        do {
            System.out.print("Informe o salario base (kz): ");
            try {
                salarioBase = sc.nextDouble();
                if (!Validator.isValorPositivo(salarioBase)) {
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
            System.out.print("Informe o bonus (kz): ");
            try {
                bonus = sc.nextDouble();
                if (!Validator.isValorPositivo(bonus)) {
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
        System.out.print("Informe o codigo da funcao a eliminar: ");
        int codigo;
        try {
            codigo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada invalida para o codigo da funcao.");
            return false;
        }
        finally{
            sc.nextLine();
        }

        Funcao funcaoParaRemover = Pesquisar(codigo);
        if (funcaoParaRemover == null) {
            System.out.println("Funcao nao encontrada.");
            return false;
        }

        // Verifica se a função está associada a algum colaborador
        for (Colaborador c : Colaborador.colaboradores) {
            // Usa o método equals de Funcao para comparar objetos
            if (c.getFuncao() != null && c.getFuncao().equals(funcaoParaRemover)) {
                System.out.println("Nao eh possível eliminar a funcao. Esta associada ao colaborador: " + c.getNome());
                return false;
            }
        }
        // Se não estiver associada a nenhum colaborador, pode remover
        funcoes.remove(funcaoParaRemover);
        System.out.println("Funcao eliminada com sucesso!");
        return true;
    }

    public static void Imprimir() {
        if (funcoes.isEmpty()) {
            System.out.println("Nenhuma funcao cadastrada.");
            return;
        }

        System.out.println();
        System.out.println("======================================= LISTA DE FUNCOES =======================================");
        System.out.printf("%-8s | %-42s | %-20s | %-15s |\n", "Codigo", "Nome", "Salario Base (kz)", "Bonus (kz)");
        System.out.println("-------- | ------------------------------------------ | -------------------- | --------------- |");

        for (Funcao f : funcoes) {
            System.out.printf("%-8d | %-42s | %-20.2f | %-15.2f |\n",
                f.getCodigo(),
                f.getNome(),
                f.getSalarioBase(),
                f.getBonus());
        }
    }
    
    // Mostrar listas de funcoes numa pasta de ficheiros disponiveis para importacao
    private static boolean ListarArquivosFuncoesDisponiveis(String folderName) {
        File pasta = new File(folderName);
        File[] arquivos = pasta.listFiles((_, name) -> name.toLowerCase().endsWith(".csv"));
        System.out.println("\n======= Arquivos de Funcoes Disponiveis para Importacao =======");
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo CSV de funcoes encontrado na pasta atual.");
            return false;
        }
        for (File arquivo : arquivos) {
            System.out.println("- " + arquivo.getName());
        }
        return true;
    }

    // Adicionar uma funcao para importar funcoes de um arquivo csv
    public static void ImportarFuncoes(Scanner sc) {
        String folderName = "exports/funcoes/";
        if (!ListarArquivosFuncoesDisponiveis(folderName))
            return;
        System.out.print("Informe o nome do arquivo para importar funcoes: ");
        String caminhoArquivo = sc.nextLine().trim();
        if (!Validator.isNomeArquivoValido(caminhoArquivo)) {
            System.out.println("Caminho do arquivo invalido.");
            return;
        }
        caminhoArquivo = folderName + caminhoArquivo;
        if (!caminhoArquivo.toLowerCase().endsWith(".csv")) {
            caminhoArquivo += ".csv";
        }
        try {
            File arquivo = new File(caminhoArquivo);
            if (!arquivo.exists()) {
                System.out.println("Arquivo nao encontrado: " + caminhoArquivo);
                return;
            }
            
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                
                if (partes.length != 4) {
                    System.out.println("Linha invalida no arquivo: " + linha);
                    continue;
                }
                
                Funcao funcao = new Funcao();
                funcao.setCodigo(Integer.parseInt(partes[0].trim()));
                funcao.setNome(partes[1].trim());
                funcao.setSalarioBase(Double.parseDouble(partes[2].trim()));
                funcao.setBonus(Double.parseDouble(partes[3].trim()));
                
                funcoes.add(funcao);
            }
            scanner.close();
            System.out.println("Funcoes importadas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados do arquivo: " + e.getMessage());
        }
    }
 
    // Adicionar uma funcao para exportar funcoes para um arquivo csv
    public static void ExportarFuncoes(Scanner sc) {
        if (funcoes.isEmpty()) {
            System.out.println("Nenhuma funcao cadastrada para exportar.");
            return;
        }
        String folderName = "exports/funcoes/";
        File pasta = new File(folderName);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        System.out.print("Informe o nome do arquivo para exportar funcoes: ");
        String caminhoArquivo = sc.nextLine().trim();
        if (!Validator.isNomeArquivoValido(caminhoArquivo)) {
            System.out.println("Caminho do arquivo invalido.");
            return;
        }
        caminhoArquivo = folderName + caminhoArquivo;
        if (!caminhoArquivo.toLowerCase().endsWith(".csv")) {
            caminhoArquivo += ".csv";
        }
        File arquivo = new File(caminhoArquivo);
        if (arquivo.exists()) {
            System.out.print("Arquivo ja existe. Deseja sobrescrever? (s/n): ");
            String resposta = sc.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                System.out.println("Exportacao cancelada.");
                return;
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Funcao f : funcoes) {
                writer.printf("%d;%s;%.2f;%.2f%n", f.getCodigo(), f.getNome(), f.getSalarioBase(), f.getBonus());
            }
            System.out.println("Funcoes exportadas com sucesso para " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
    public static void Actualizar(Scanner sc) {
        Imprimir();
        System.out.print("Informe o codigo da funcao a atualizar: ");
        int codigo;
        try {
            codigo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada invalida para o codigo da funcao.");
            sc.nextLine();
            return;
        }
        sc.nextLine();
        Funcao funcao = Pesquisar(codigo);
        if (funcao == null) {
            System.out.println("Funcao nao encontrada.");
            return;
        }
        System.out.println("Atualizando funcao: " + funcao.getNome());
        System.out.print("Novo nome (" + funcao.getNome() + "): ");
        String nome = sc.nextLine();
        if (Validator.hasContent(nome)) {
            funcao.setNome(nome);
        }
        System.out.print("Novo salario base (" + funcao.getSalarioBase() + "): ");
        String salarioStr = sc.nextLine();
        if (Validator.hasContent(salarioStr)) {
            try {
                double salario = Double.parseDouble(salarioStr);
                if (Validator.isValorPositivo(salario)) {
                    funcao.setSalarioBase(salario);
                } else {
                    System.out.println("Salario base deve ser maior que zero. Mantendo valor anterior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Mantendo valor anterior.");
            }
        }
        System.out.print("Novo bonus (" + funcao.getBonus() + "): ");
        String bonusStr = sc.nextLine();
        if (Validator.hasContent(bonusStr)) {
            try {
                double bonus = Double.parseDouble(bonusStr);
                if (Validator.isValorPositivo(bonus)) {
                    funcao.setBonus(bonus);
                } else {
                    System.out.println("Bonus deve ser maior que zero. Mantendo valor anterior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Mantendo valor anterior.");
            }
        }
        System.out.println("Funcao atualizada com sucesso!");
    }
}
