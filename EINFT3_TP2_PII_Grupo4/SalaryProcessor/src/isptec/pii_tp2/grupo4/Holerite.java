/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;
import java.time.LocalDate; // Para a data do período de referência e data de pagamento

/*
 Representa um holerite ou recibo de vencimento para um colaborador
 em um determinado período de referência.
 Armazena todos os valores calculados do salario (bruto, liquido, descontos).
*/
public class Holerite {
    
    private int idHolerite;
    private static int contadorHolerite = 1;
    
    private Colaborador colaborador;
    private LocalDate periodoReferencia; 
    private double salarioBase;   
    private double valorIRT;
    private double valorINSS; 
    private double salarioLiquido;
    private LocalDate dataPagamento;   
    private double bonus;
    private double salarioBruto;

    public Holerite(Colaborador colaborador, LocalDate periodoReferencia,
                    double salarioBase, double bonus, double salarioBruto,
                    double valorIRT, double valorSegurancaSocial, double salarioLiquido,
                    LocalDate dataPagamento) {
        this.idHolerite = contadorHolerite++;
        this.colaborador = colaborador;
        this.periodoReferencia = periodoReferencia;
        this.bonus = bonus;
        this.salarioBase = salarioBase;
        this.salarioBruto = salarioBruto;
        this.valorIRT = valorIRT;
        this.valorINSS = valorSegurancaSocial;
        this.salarioLiquido = salarioLiquido;
        this.dataPagamento = dataPagamento;
    }
    
    //Getters para todos os atributos

    public int getIdHolerite() {
        return idHolerite;
    }
    
    public Colaborador getColaborador() {
        return colaborador;
    }

    public LocalDate getPeriodoReferencia() {
        return periodoReferencia;
    }

    public double getBonus() {
        return bonus;
    }
    
    public double getSalarioBase(){
        return salarioBase;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getValorIRT() {
        return valorIRT;
    }

    public double getValorINSS() {
        return valorINSS;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    /*
    Retorna uma representacao em String do Holerite para exibicao.
    Formata os valores monetarios para duas casas decimais.
    */
    
    public String toString() {
       
        return String.format(
            "=========================================================\n" +
            "| RECIBO DE VENCIMENTO                                  |\n" +
            "=========================================================\n" +
            "| Colaborador: %-35s |\n" +
            "| ID: %-42d |\n" +
            "| Fucao: %-40s |\n" +
            "| Periodo de Referencia: %-25s |\n" +
            "| Data de Processamento: %-25s |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Base:            %,15.2f KZ                    |\n" +
            "| Bonus           :        %,15.2f KZ                    |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Bruto:           %,15.2f KZ                    |\n" +
            "| (-) IRT:                 %,15.2f KZ                    |\n" +
            "| (-) Seguranca Social:    %,15.2f KZ                    |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Liquido:         %,15.2f KZ                    |\n" +
            "=========================================================\n",
            colaborador.getNome(),
            colaborador.getNumero(),
            colaborador.getFuncao() != null ? colaborador.getFuncao().getNome() : "N/A",
            periodoReferencia.getMonth().name() + "/" + periodoReferencia.getYear(),
            dataPagamento.toString(),
            salarioBase,
            bonus,
            salarioBruto,
            valorIRT,
            valorINSS,
            salarioLiquido
        );
    }
}
