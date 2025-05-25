package isptec.pii_tp2.grupo4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Colaborador {

    public int numero;
    public String nome;
    public String morada;
    public String email;
    public LocalDate dataNascimento;
    public Funcao funcao;
    public boolean activo;
    public LocalDate dataAdmissao;

    public static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static int contador = 1;

    public static boolean Cadastrar() {
        Scanner sc = new Scanner(System.in);
        Colaborador novo = new Colaborador();

        novo.numero = contador++;

        System.out.print("Informe o nome: ");
        novo.nome = sc.nextLine();

        System.out.print("Informe o email: ");
        novo.email = sc.nextLine();

        System.out.print("Informe a morada: ");
        novo.morada = sc.nextLine();

        System.out.print("Informe a data de nascimento(YYYY-MM-DD): ");
        try {
            novo.dataNascimento = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Data invalida!");
            return false;
        }

        Funcao.Imprimir();
        System.out.print("Escolhe uma Funcao (Codigo): ");
        Funcao f = Funcao.Pesquisar(sc.nextInt());
        sc.nextLine(); // Limpar buffer

        if (f == null) {
            System.out.println("Nao eh possivel adicionar este colaborador!");
            return false;
        }

        novo.funcao = f;
        novo.dataAdmissao = LocalDate.now();
        novo.activo = true;

        return colaboradores.add(novo);
    }

    public static Colaborador Actualizar(Colaborador colaborador) {
        if (colaborador == null) return null;
        System.out.println("[Manter alteracoes]: apenas pressione Enter\n");
        Scanner sc = new Scanner(System.in);

        System.out.println("Atualizando colaborador: " + colaborador.nome);
        System.out.print("Novo nome (" + colaborador.nome + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) colaborador.nome = nome;

        System.out.print("Novo email (" + colaborador.email + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) colaborador.email = email;

        System.out.print("Nova morada (" + colaborador.morada + "): ");
        String morada = sc.nextLine();
        if (!morada.isEmpty()) colaborador.morada = morada;

        System.out.print("Nova data de nascimento (" + colaborador.dataNascimento + "): ");
        String data = sc.nextLine();
        if (!data.isEmpty()) {
            try {
                colaborador.dataNascimento = LocalDate.parse(data);
            } catch (Exception e) {
                System.out.println("Data invalida! Mantendo a anterior.");
            }
        }

        Funcao.Imprimir();
        System.out.print("Nova Funcao (Codigo, atual: " + (colaborador.funcao != null ? colaborador.funcao.nome : "N/A") + "): ");
        String funcaoId = sc.nextLine();
        if (!funcaoId.isEmpty()) {
            try {
                Funcao f = Funcao.Pesquisar(Integer.parseInt(funcaoId));
                if (f != null) colaborador.funcao = f;
            } catch (Exception e) {
                System.out.println("Funcao invalida! Mantendo a anterior.");
            }
        }

        return colaborador;
    }

    public static void Desactivar() {
        Scanner sc = new Scanner(System.in);
        Imprimir();
        System.out.print("Informe o numero do colaborador a desativar: ");
        int numero = sc.nextInt();

        for (Colaborador c : colaboradores) {
            if (c.numero == numero && c.activo) {
                c.activo = false;
                System.out.println("Colaborador desativado com sucesso!");
                return;
            }
        }
        System.out.println("Colaborador nao encontrado ou ja esta desativado.");
    }
    
    public static Colaborador Pesquisar(int numero) {
        for (Colaborador c : colaboradores) {
            if (c.numero == numero) return c;
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

        int i = 1;
        boolean algumAtivo = false;
        for (Colaborador c : colaboradores) {
            if (c.activo) {
                algumAtivo = true;
                System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-20s | %-15s%n",
                        c.numero,
                        c.nome,
                        c.email,
                        c.morada,
                        c.dataNascimento != null ? c.dataNascimento.toString() : "N/A",
                        c.funcao != null ? c.funcao.nome : "N/A",
                        c.dataAdmissao != null ? c.dataAdmissao.toString() : "N/A"
                );
            }
        }
        if (!algumAtivo) {
            System.out.println("Nenhum colaborador ativo.");
        }
    }
}
