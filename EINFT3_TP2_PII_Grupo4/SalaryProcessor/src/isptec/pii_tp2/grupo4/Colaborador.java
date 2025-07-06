package isptec.pii_tp2.grupo4;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.time.format.DateTimeFormatter; 

public class Colaborador {

    private int numero;
    private String nome;
    private String morada;
    private String email;
    private LocalDate dataNascimento;
    private Funcao funcao;
    private boolean activo;
    private LocalDate dataAdmissao;

    //Lista estática para armazenar todos os colaboradores
    public static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static int contador = 1;
    //Construtor
    public Colaborador(){
        this.activo = true;
        this.dataAdmissao = LocalDate.now();
    }
    
    //Getters e Setters. Adicionados para acessar atributos privados
    public int getNumero(){
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    //Métodos de operação de colaboradores
    
    //Recebe o Scanner como parâmetro para evitar muitas instâncias  
    public static boolean Cadastrar(Scanner sc) {
        Colaborador novo = new Colaborador();

        novo.setNumero(contador++);

        // Campo obrigatorio: nome
        String nome;
        do {
            System.out.print("Informe o nome: ");
            nome = sc.nextLine();
            if (!Validator.hasContent(nome)) {
                System.out.println("O nome nao pode ser vazio.");
            }
        } while (!Validator.hasContent(nome));
        novo.setNome(nome);

        // Email
        String email;
        do{
            System.out.print("Informe o email: ");
            email = sc.nextLine();
            if(!Validator.isEmailValido(email)){
                System.out.println("Formato de email invalido! Tente novamente.");
            }else{
                if(Pesquisar(email)!= null){
                    System.out.println("Este email ja esta cadastrado para outro colaborador. Tente novamente.");
                    email = null;
                }
            }
        }while(!Validator.isEmailValido(email) || email == null);
        novo.setEmail(email);

        // Morada
        String morada;
        do {
            System.out.print("Informe a morada: ");
            morada = sc.nextLine();
            if (!Validator.hasContent(morada)) {
                System.out.println("A morada nao pode ser vazia.");
            }
        } while (!Validator.hasContent(morada));
        novo.setMorada(morada);

        // Data de nascimento
        LocalDate dataNasc = null;
        boolean dataValida = false;
        do {
            System.out.print("Informe a data de nascimento (YYYY-MM-DD): ");
            String dataStr = sc.nextLine();
            dataNasc = Validator.validarData(dataStr);
            if (dataNasc == null) {
                System.out.println("Data de nascimento invalida ou no futuro! Tente novamente.");
            } else {
                dataValida = true;
            }
        } while (!dataValida);
        novo.setDataNascimento(dataNasc);

        // Funcao
        Funcao f = null;
        boolean cancelar = false;
        do {
            Funcao.Imprimir();
            System.out.print("Escolha uma Funcao (Codigo) ou digite 0 para cancelar o cadastro: ");
            int codigoFuncao;
            try {
                codigoFuncao = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada invalida para o codigo da funcao. Tente novamente.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            if (codigoFuncao == 0) {
                System.out.println("Cadastro de colaborador cancelado pelo usuario.");
                cancelar = true;
                break;
            }

            f = Funcao.Pesquisar(codigoFuncao);

            if (f == null) {
                System.out.println("Funcao com o codigo " + codigoFuncao + " nao encontrada! Tente novamente ou digite 0 para cancelar.");
            }
        } while (f == null && !cancelar);

        if (cancelar) {
            return false;
        }

        novo.setFuncao(f);
        return colaboradores.add(novo);
    }

    public static Colaborador Actualizar(Scanner sc, Colaborador colaborador) {
        if (colaborador == null) return null;
        System.out.println("[Manter alteracoes]: apenas pressione Enter\n");

        System.out.println("Atualizando colaborador: " + colaborador.getNome());
        System.out.print("Novo nome (" + colaborador.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) colaborador.setNome(nome);
        
        String email;
        do{
            System.out.print("Novo email (" + colaborador.getEmail() + "): ");
            email = sc.nextLine();
            if (email.isEmpty()) { // Usuario nao quer alterar o email
                break;
            }
            if (!Validator.isEmailValido(email)) {
                System.out.println("Formato de email invalido! Tente novamente ou pressione Enter para manter o atual.");
            } else {
                // Verifica se o email ja existe, exceto se for o proprio email do colaborador
                Colaborador cExistente = Pesquisar(email);
                if (cExistente != null && cExistente.getNumero() != colaborador.getNumero()) {
                    System.out.println("Este email ja esta cadastrado para outro colaborador. Tente novamente ou pressione Enter para manter o atual.");
                    email = ""; // Para repetir o loop ou sair se for vazio
                } else {
                    colaborador.setEmail(email);
                    break; // Sai do loop se for valido e unico
                }
            }

        }while(true);
        
        System.out.print("Nova morada (" + colaborador.morada + "): ");
        String morada = sc.nextLine();
        if (!morada.isEmpty()) colaborador.setMorada(morada);

        System.out.print("Nova data de nascimento (" + (colaborador.getDataNascimento() != null ? colaborador.getDataNascimento().toString() : "N/A") + "): ");
        String dataStr = sc.nextLine();
        if (!dataStr.isEmpty()) {
            LocalDate dataNasc = Validator.validarData(dataStr);
            if (dataNasc == null) {
                System.out.println("Data de nascimento invalida ou no futuro! Mantendo a anterior.");
            } else {
                colaborador.setDataNascimento(dataNasc);
            }
        }

        Funcao.Imprimir();
        System.out.print("Nova Funcao (Codigo, atual: " + (colaborador.getFuncao() != null ? colaborador.getFuncao().getNome() : "N/A") + "): ");
        String funcaoIdStr = sc.nextLine();
        if (!funcaoIdStr.isEmpty()) {
            try {
                int funcaoId = Integer.parseInt(funcaoIdStr);
                Funcao f = Funcao.Pesquisar(funcaoId);
                if (f != null) {
                    colaborador.setFuncao(f);
                } else {
                    System.out.println("Funcao com o codigo " + funcaoId + " nao encontrada! Mantendo a anterior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Codigo da funcao invalido! Mantendo a anterior.");
            }
        }

        return colaborador;
    }

    public static void Desactivar(Scanner sc) {
        ListarColaboradores();
        System.out.print("Informe o numero do colaborador a desativar: ");
        int numero;
        try {
            numero = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada invalida para o numero do colaborador.");
            sc.nextLine(); // Limpa o buffer
            return;
        }
        sc.nextLine(); // Limpar buffer

        for (Colaborador c : colaboradores) {
            if (c.getNumero() == numero && c.isActivo()) {
                c.setActivo(false);
                System.out.println("Colaborador desativado com sucesso!");
                return;
            }
        }
        System.out.println("Colaborador nao encontrado ou ja esta desativado.");
    }
    
    public static Colaborador Pesquisar(int numero) {
        for (Colaborador c : colaboradores) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    // Método Pesquisar para pesquisar por email
    public static Colaborador Pesquisar(String email) {
        for (Colaborador c : colaboradores) {
            // Verifica se o email não é nulo e ignora maiúsculas/minúsculas na comparação
            if (c.getEmail() != null && c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }
    
    public static void ListarColaboradores() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        System.out.println();
        System.out.println("============================================================== COLABORADORES ATIVOS ==============================================================");
        System.out.printf("%-4s | %-25s | %-30s | %-15s | %-12s | %-30s | %-10s |\n",
                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

        boolean algumAtivo = false;
        for (Colaborador c : colaboradores) {
            if (c.isActivo()) {
                algumAtivo = true;
                System.out.printf("%-4d | %-25s | %-30s | %-15s | %-12s | %-30s | %-10s |\n",
                        c.getNumero(),
                        c.getNome(),
                        c.getEmail(),
                        c.getMorada(),
                        c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                        c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                        c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A"
                );
            }
        }
        if (!algumAtivo) {
            System.out.println("Nenhum colaborador ativo.");
        }
    }

    public static void ImprimirPorOrdemAdmissao() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para o relatorio.");
            return;
        }

        ArrayList<Colaborador> colaboradoresOrdenados = new ArrayList<>(colaboradores);
        colaboradoresOrdenados.sort(Comparator.comparing(Colaborador::getDataAdmissao, Comparator.nullsLast(Comparator.naturalOrder())));

        System.out.println();
        System.out.println("===================================== RELATORIO: COLABORADORES POR ORDEM DE ADMISSAO ============================================");
        System.out.printf("%-4s | %-20s | %-25s | %-15s | %-12s | %-15s | %-10s | %-5s |\n",
                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao", "Ativo");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        for (Colaborador c : colaboradoresOrdenados) {
            System.out.printf("%-4d | %-20s | %-25s | %-15s | %-12s | %-15s | %-10s | %-5s |\n",
                    c.getNumero(),
                    c.getNome(),
                    c.getEmail(),
                    c.getMorada(),
                    c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                    c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                    c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A",
                    c.isActivo() ? "Sim" : "Nao"
            );
        }
    }
    
    public static void ImprimirColaboradores(Scanner sc) {
        System.out.println("\n--- EXPORTAR RELATORIO DE COLABORADORES PARA TXT ---");
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para exportar.");
            return;
        }

        String dataAtual = java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH'h'mm"));
        String pasta = "reports/colaboradores/";
        String nomeArquivo = pasta + "colaboradores_" + dataAtual + ".txt";


        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Ordena os colaboradores antes de escrever 
        ArrayList<Colaborador> colaboradoresOrdenados = new ArrayList<>(colaboradores);
        colaboradoresOrdenados.sort(Comparator.comparing(Colaborador::getDataAdmissao, Comparator.nullsLast(Comparator.naturalOrder())));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("================================================ RELATORIO: COLABORADORES POR ORDEM DE ADMISSAO ==========================================================");
            writer.printf("%-4s | %-25s | %-30s | %-15s | %-12s | %-30s | %-10s | %-5s |\n",
                    "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao", "Ativo");
            writer.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Colaborador c : colaboradoresOrdenados) {
                writer.printf("%-4d | %-25s | %-30s | %-15s | %-12s | %-30s | %-10s | %-5s |\n",
                        c.getNumero(),
                        c.getNome(),
                        c.getEmail(),
                        c.getMorada(),
                        c.getDataNascimento() != null ? c.getDataNascimento().format(formatter) : "N/A",
                        c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                        c.getDataAdmissao() != null ? c.getDataAdmissao().format(formatter) : "N/A",
                        c.isActivo() ? "Sim" : "Nao");
            }
            System.out.println("Relatorio de colaboradores exportado com sucesso para " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            System.err.println("Verifique as permissoes de escrita ou se o caminho esta correto.");
        }
    }

    // Listar colaboradores disponieis para importar
    private static boolean ListarArquivosColaboradoresDisponiveis(String folderName) {
        File dir = new File(folderName);

        File[] arquivos = dir.listFiles((_, nome) -> nome.toLowerCase().endsWith(".csv"));
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo CSV de colaboradores encontrado na pasta: " + dir.getAbsolutePath());
            return false;
        }

        System.out.println("Arquivos CSV de colaboradores disponiveis:");
        for (File arquivo : arquivos) {
            System.out.println("- " + arquivo.getName());
        }
        return true;
    }
    
    // Adicionar uma funcao para importar colaboradores de um arquivo CSV
    public static void ImportarColaboradores(Scanner sc) {
        String folderName = "exports/colaboradores/";
        if (!ListarArquivosColaboradoresDisponiveis(folderName))
            return;
        System.out.print("Informe o nome do arquivo para importar colaboradores: ");
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
        if (!arquivo.exists()) {
            System.out.println("Arquivo nao encontrado: " + caminhoArquivo);
            return;
        }
        try (Scanner scanner = new Scanner(arquivo)) {
            boolean primeiraLinha = true;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                // Ignora cabeçalho se existir
                if (primeiraLinha && linha.toLowerCase().contains("nome;email;morada")) {
                    primeiraLinha = false;
                    continue;
                }
                primeiraLinha = false;

                String[] campos = linha.split(";");
                if (campos.length < 10) {
                    System.out.println("Linha invalida no arquivo: " + linha);
                    continue;
                }

                Colaborador colaborador = new Colaborador();
                colaborador.setNumero(contador++);
                colaborador.setNome(campos[0].trim());
                colaborador.setEmail(campos[1].trim());
                colaborador.setMorada(campos[2].trim());
                colaborador.setDataNascimento(LocalDate.parse(campos[3].trim()));

                // Dados da função
                int funcaoCodigo = Integer.parseInt(campos[4].trim());
                String funcaoNome = campos[5].trim();
                double funcaoSalarioBase = Double.parseDouble(campos[6].trim());
                double funcaoBonus = Double.parseDouble(campos[7].trim());

                // Procura função existente pelo código
                Funcao funcao = Funcao.Pesquisar(funcaoCodigo);
                if (funcao == null) {
                    funcao = new Funcao();
                    funcao.setCodigo(funcaoCodigo);
                    funcao.setNome(funcaoNome);
                    funcao.setSalarioBase(funcaoSalarioBase);
                    funcao.setBonus(funcaoBonus);
                    Funcao.funcoes.add(funcao);
                } else {
                    // Atualiza dados da função se necessário
                    funcao.setNome(funcaoNome);
                    funcao.setSalarioBase(funcaoSalarioBase);
                    funcao.setBonus(funcaoBonus);
                }
                colaborador.setFuncao(funcao);

                colaborador.setDataAdmissao(LocalDate.parse(campos[8].trim()));
                colaborador.setActivo("Sim".equalsIgnoreCase(campos[9].trim()));

                colaboradores.add(colaborador);
            }
            System.out.println("Colaboradores importados com sucesso do arquivo: " + caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao importar colaboradores: " + e.getMessage());
        }
    }

    // Adicionar uma funcao para exportar colaboradores para um arquivo CSV
    public static void ExportarColaboradores(Scanner sc) {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para exportar.");
            return;
        }
        String folderName = "exports/colaboradores/";
        File pasta = new File(folderName);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        System.out.print("Informe o nome do arquivo para exportar colaboradores: ");
        String caminhoArquivo = sc.nextLine().trim();
        if (!Validator.isNomeArquivoValido(caminhoArquivo)) {
            System.out.println("Caminho do arquivo invalido.");
            return;
        }
        caminhoArquivo = folderName + caminhoArquivo;
        if (!caminhoArquivo.toLowerCase().endsWith(".csv")) {
            caminhoArquivo += ".csv";
        }

        if (new File(caminhoArquivo).exists()) {
            System.out.print("O arquivo ja existe. Deseja sobrescrever? (s/n): ");
            String resposta = sc.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                System.out.println("Exportacao cancelada pelo usuario.");
                return;
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            // Cabeçalho para clareza
            writer.println("Nome;Email;Morada;DataNascimento;FuncaoCodigo;FuncaoNome;FuncaoSalarioBase;FuncaoBonus;DataAdmissao;Ativo");
            for (Colaborador c : colaboradores) {
                if (c.isActivo()) { // Exporta apenas colaboradores ativos
                    Funcao f = c.getFuncao();
                    writer.printf("%s;%s;%s;%s;%d;%s;%.2f;%.2f;%s;%s\n",
                            c.getNome(),
                            c.getEmail(),
                            c.getMorada(),
                            c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                            f != null ? f.getCodigo() : 0,
                            f != null ? f.getNome() : "N/A",
                            f != null ? f.getSalarioBase() : 0.0,
                            f != null ? f.getBonus() : 0.0,
                            c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A",
                            c.isActivo() ? "Sim" : "Nao");
                }
            }
            System.out.println("Colaboradores exportados com sucesso para o arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}