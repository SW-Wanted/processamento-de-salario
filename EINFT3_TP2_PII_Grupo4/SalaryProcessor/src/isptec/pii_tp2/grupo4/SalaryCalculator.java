
package isptec.pii_tp2.grupo4;

/**
 *
 * @author hp
 */
public class SalaryCalculator {
    public static double calcularIRT(double salarioBrutoMensal) {
        double irt = 0.0;

        // Tabela de IRT - Diario da Republica de 29 de Dezembro de 2023
        // Formula: Parcela Fixa + (Rendimento - "excesso de") * Taxa

        if (salarioBrutoMensal <= 100000.00) { // 1º Escalão: Até 100 000
            irt = 0.0;
        } else if (salarioBrutoMensal <= 150000.00) { // 2º Escalão: De 100 001 a 150 000
            irt = (salarioBrutoMensal - 100000.00) * 0.13;
        } else if (salarioBrutoMensal <= 200000.00) { // 3º Escalão: De 150 001 a 200 000
            irt = 12500.00 + (salarioBrutoMensal - 150000.00) * 0.16;
        } else if (salarioBrutoMensal <= 300000.00) { // 4º Escalão: De 200 001 a 300 000
            irt = 31250.00 + (salarioBrutoMensal - 200000.00) * 0.18;
        } else if (salarioBrutoMensal <= 500000.00) { // 5º Escalão: De 300 001 a 500 000
            irt = 49250.00 + (salarioBrutoMensal - 300000.00) * 0.19;
        } else if (salarioBrutoMensal <= 1000000.00) { // 6º Escalão: De 500 001 a 1 000 000
            irt = 87250.00 + (salarioBrutoMensal - 500000.00) * 0.20;
        } else if (salarioBrutoMensal <= 1500000.00) { // 7º Escalão: De 1 000 001 a 1 500 000
            irt = 187249.00 + (salarioBrutoMensal - 1000000.00) * 0.21;
        } else if (salarioBrutoMensal <= 2000000.00) { // 8º Escalão: De 1 500 001 a 2 000 000
            irt = 292249.00 + (salarioBrutoMensal - 1500000.00) * 0.22;
        } else if (salarioBrutoMensal <= 2500000.00) { // 9º Escalão: De 2 000 001 a 2 500 000
            irt = 402249.00 + (salarioBrutoMensal - 2000000.00) * 0.23;
        } else if (salarioBrutoMensal <= 5000000.00) { // 10º Escalão: De 2 500 001 a 5 000 000
            irt = 517249.00 + (salarioBrutoMensal - 2500000.00) * 0.24;
        } else if (salarioBrutoMensal <= 10000000.00) { // 11º Escalão: De 5 000 001 a 10 000 000
            irt = 1117249.00 + (salarioBrutoMensal - 5000000.00) * 0.245;
        } else { // 12º Escalão: Acima de 10 000 000
            irt = 2342248.00 + (salarioBrutoMensal - 10000000.00) * 0.25;
        }
        return irt;
    }
    
    public static double calcularINSS(double salarioBruto) {
        final double TAXA_INSS_TRABALHADOR = 0.03;
        return salarioBruto * TAXA_INSS_TRABALHADOR;
    }
    
    public static double calcularSalarioBruto(double salarioBase, double bonus) {
        // Garantir que os valores não sejam negativos usamos o Math.max, considera-se uma boa prática
        double base = Math.max(0, salarioBase);
        double bon = Math.max(0, bonus);
        return base + bon;
    }
    
}
