/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;

/**
 *
 * @author hp
 */
public class CalculadoraSalario {
    public static double calcularIRT(double salarioBrutoMensal) {
    double irt = 0.0;

    // Tabela de IRT - Diario da Republica de 29 de Dezembro de 2023
    // Formula: Parcela Fixa + (Rendimento - "excesso de") * Taxa

    if (salarioBrutoMensal <= 100000.00) { // 1º Escalão: Até 100 000
        irt = 0.0;
    } else if (salarioBrutoMensal <= 150000.00) { // 2º Escalão: De 100 001 a 150 000
        // Parcela Fixa: 0.0, Taxa: 13%, Excesso de: 100 001
        irt = (salarioBrutoMensal - 100000.00) * 0.13;
    } else if (salarioBrutoMensal <= 200000.00) { // 3º Escalão: De 150 001 a 200 000
        // Parcela Fixa: 12 500, Taxa: 16%, Excesso de: 150 001
        irt = 12500.00 + (salarioBrutoMensal - 150000.00) * 0.16;
    } else if (salarioBrutoMensal <= 300000.00) { // 4º Escalão: De 200 001 a 300 000
        // Parcela Fixa: 31 250, Taxa: 18%, Excesso de: 200 001
        irt = 31250.00 + (salarioBrutoMensal - 200000.00) * 0.18;
    } else if (salarioBrutoMensal <= 500000.00) { // 5º Escalão: De 300 001 a 500 000
        // Parcela Fixa: 49 250, Taxa: 19%, Excesso de: 300 001
        irt = 49250.00 + (salarioBrutoMensal - 300000.00) * 0.19;
    } else if (salarioBrutoMensal <= 1000000.00) { // 6º Escalão: De 500 001 a 1 000 000
        // Parcela Fixa: 87 250, Taxa: 20%, Excesso de: 500 001
        irt = 87250.00 + (salarioBrutoMensal - 500000.00) * 0.20;
    } else if (salarioBrutoMensal <= 1500000.00) { // 7º Escalão: De 1 000 001 a 1 500 000
        // Parcela Fixa: 187 249, Taxa: 21%, Excesso de: 1 000 001
        irt = 187249.00 + (salarioBrutoMensal - 1000000.00) * 0.21; // CORRIGIDO AQUI
    } else if (salarioBrutoMensal <= 2000000.00) { // 8º Escalão: De 1 500 001 a 2 000 000
        // Parcela Fixa: 292 249, Taxa: 22%, Excesso de: 1 500 001
        irt = 292249.00 + (salarioBrutoMensal - 1500000.00) * 0.22;
    } else if (salarioBrutoMensal <= 2500000.00) { // 9º Escalão: De 2 000 001 a 2 500 000
        // Parcela Fixa: 402 249, Taxa: 23%, Excesso de: 2 000 001
        irt = 402249.00 + (salarioBrutoMensal - 2000000.00) * 0.23;
    } else if (salarioBrutoMensal <= 5000000.00) { // 10º Escalão: De 2 500 001 a 5 000 000
        // Parcela Fixa: 517 249, Taxa: 24%, Excesso de: 2 500 001
        irt = 517249.00 + (salarioBrutoMensal - 2500000.00) * 0.24;
    } else if (salarioBrutoMensal <= 10000000.00) { // 11º Escalão: De 5 000 001 a 10 000 000
        // Parcela Fixa: 1 117 249, Taxa: 24.5%, Excesso de: 5 000 001
        irt = 1117249.00 + (salarioBrutoMensal - 5000000.00) * 0.245;
    } else { // 12º Escalão: Acima de 10 000 000
        // Parcela Fixa: 2 342 248, Taxa: 25%, Excesso de: 10 000 000
        irt = 2342248.00 + (salarioBrutoMensal - 10000000.00) * 0.25;
    }
    return irt;
}
    
    /*
     Calcula a Contribuicao para a Seguranca Social (INSS) do colaborador em Angola.
     A taxa padrao para o trabalhador e de 3% sobre o salario base.
    */
    
    public static double calcularINSS(double salarioBruto) {
        // Taxa de INSS para o trabalhador em Angola
        final double TAXA_INSS_TRABALHADOR = 0.03;

        return salarioBruto * TAXA_INSS_TRABALHADOR;
    }
    
    public static double calcularSalarioBruto(double salarioBase, double bonus) {
        // Garantir que os valores nao sejam negativos antes de somar
        double base = Math.max(0, salarioBase);
        double bon = Math.max(0, bonus);
        return base + bon;
    }
    
}
