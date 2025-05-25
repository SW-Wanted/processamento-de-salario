package isptec.pii_tp2.grupo4;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.Scanner;

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

    //Validação para o Email
    public static boolean isEmailValido(String email){
        //formato do email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email != null && email.matches(regex);
    }
    //Métodos de operação de colaboradores
    
    //Recebe o Scanner como parâmetro para evitar muitas instâncias  
    public static boolean Cadastrar(Scanner sc) {
        Colaborador novo = new Colaborador();

        novo.setNumero(contador++);

        System.out.print("Informe o nome: ");
        novo.setNome(sc.nextLine());
        
        String email;
        do{
            System.out.print("Informe o email: ");
            email = sc.nextLine();
            if(!isEmailValido(email)){
                System.out.println("Formato de email invalido! Tente novamente.");
            }else{
                //Verifica se o email já existe
                if(Pesquisar(email)!= null){
                    System.out.println("Este email ja esta cadastrado para outro colaborador. Tente novamente.");
                    email = null;
                }
            }
        }while(!isEmailValido(email) || email == null);
        
        novo.setEmail(email);
        
        System.out.print("Informe a morada: ");
        novo.setMorada(sc.nextLine());

        System.out.print("Informe a data de nascimento(YYYY-MM-DD): ");
        try {
            LocalDate dataNasc = LocalDate.parse(sc.nextLine());
            //Validação da data de nascimento
            if(dataNasc.isAfter(LocalDate.now())){
                System.out.println("Data de nascimento nao pode ser no futuro! Cadastro cancelado.");
                return false;
            }
            novo.setDataNascimento(dataNasc);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data invalido! Use YYYY-MM-DD. Cadastro cancelado.");
            return false;
        }

        Funcao.Imprimir();
        System.out.print("Escolhe uma Funcao (Codigo): ");
        int codigoFuncao;
        try {
            codigoFuncao = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada invalida para o codigo da funcao. Cadastro cancelado.");
            sc.nextLine(); // Limpa o buffer
            return false;
        }
        sc.nextLine(); // Limpar buffer

        Funcao f = Funcao.Pesquisar(codigoFuncao);

        if (f == null) {
            System.out.println("Funcao com o codigo " + codigoFuncao + " nao encontrada! Cadastro cancelado.");
            return false;
        }

        novo.setFuncao(f);
        // dataAdmissao e activo já são definidos no construtor
        
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
            if (email.isEmpty()) { // Usuário não quer alterar o email
                break;
            }
            if (!isEmailValido(email)) {
                System.out.println("Formato de email invalido! Tente novamente ou pressione Enter para manter o atual.");
            } else {
                // Verifica se o email já existe, exceto se for o próprio email do colaborador
                Colaborador cExistente = Pesquisar(email);
                if (cExistente != null && cExistente.getNumero() != colaborador.getNumero()) {
                    System.out.println("Este email ja esta cadastrado para outro colaborador. Tente novamente ou pressione Enter para manter o atual.");
                    email = ""; // Para repetir o loop ou sair se for vazio
                } else {
                    colaborador.setEmail(email);
                    break; // Sai do loop se for válido e único
                }
            }

        }while(true);
        
        System.out.print("Nova morada (" + colaborador.morada + "): ");
        String morada = sc.nextLine();
        if (!morada.isEmpty()) colaborador.setMorada(morada);

        System.out.print("Nova data de nascimento (" + (colaborador.getDataNascimento() != null ? colaborador.getDataNascimento().toString() : "N/A") + "): ");
        String dataStr = sc.nextLine();
        if (!dataStr.isEmpty()) {
            try {
                LocalDate dataNasc = LocalDate.parse(dataStr);
                if (dataNasc.isAfter(LocalDate.now())) {
                    System.out.println("Data de nascimento nao pode ser no futuro! Mantendo a anterior.");
                } else {
                    colaborador.setDataNascimento(dataNasc);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido! Use YYYY-MM-DD. Mantendo a anterior.");
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
                System.out.println("Código da funcao invalido! Mantendo a anterior.");
            }
        }

        return colaborador;
    }

    public static void Desactivar(Scanner sc) {
        Imprimir();
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
    
    public static void Imprimir() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n----------------------------- COLABORADORES ATIVOS -----------------------------");
        System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s%n",
                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        boolean algumAtivo = false;
        for (Colaborador c : colaboradores) {
            if (c.isActivo()) {
                algumAtivo = true;
                System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s%n",
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
            System.out.println("Nenhum colaborador cadastrado para o relatório.");
            return;
        }

        // Cria uma cópia da lista para ordenar sem alterar a original
        ArrayList<Colaborador> colaboradoresOrdenados = new ArrayList<>(colaboradores);

        // Ordena a lista pela data de admissão
        // Colocamos Comparator.nullsLast(Comparator.naturalOrder()) para lidar com casos de dataAdmissao nula
        colaboradoresOrdenados.sort(Comparator.comparing(Colaborador::getDataAdmissao, Comparator.nullsLast(Comparator.naturalOrder())));

        System.out.println("\n----------------------------- RELATORIO: COLABORADORES POR ORDEM DE ADMISSAO -----------------------------");
        System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s | %-10s%n",
                "ID", "Nome", "Email", "Morada", "Nascimento", "Funcao", "Admissao", "Ativo");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");


        for (Colaborador c : colaboradoresOrdenados) {
            System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s | %-10s%n",
                    c.getNumero(),
                    c.getNome(),
                    c.getEmail(),
                    c.getMorada(),
                    c.getDataNascimento() != null ? c.getDataNascimento().toString() : "N/A",
                    c.getFuncao() != null ? c.getFuncao().getNome() : "N/A",
                    c.getDataAdmissao() != null ? c.getDataAdmissao().toString() : "N/A",
                    c.isActivo() ? "Sim" : "Não" // Indica se está ativo ou não
            );
        }
    }
}
