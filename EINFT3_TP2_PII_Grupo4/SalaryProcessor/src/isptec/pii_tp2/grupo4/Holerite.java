
package isptec.pii_tp2.grupo4;
import java.time.LocalDate;

/*
 Representa um holerite ou recibo de vencimento para um colaborador
 em um determinado período de referência.
 Armazena todos os valores calculados do salario (bruto, liquido, descontos).
*/
public class Holerite {
    
    private int idHolerite;
    
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

    public String toString() {
       
        return String.format(
            "=========================================================\n" +
            "| RECIBO DE VENCIMENTO                                  |\n" +
            "=========================================================\n" +
            "| Colaborador: %-40s |\n" +
            "| ID: %-40d          |\n" +
            "| Fucao: %-40s       |\n" +
            "| Periodo de Referencia: %-30s |\n" +
            "| Data de Processamento: %-30s |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Base:            %,15.2f KZ           |\n" +
            "| Bonus           :        %,15.2f KZ           |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Bruto:           %,15.2f KZ           |\n" +
            "| (-) IRT:                 %,15.2f KZ           |\n" +
            "| (-) Seguranca Social:    %,15.2f KZ           |\n" +
            "---------------------------------------------------------\n" +
            "| Salario Liquido:         %,15.2f KZ           |\n" +
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
