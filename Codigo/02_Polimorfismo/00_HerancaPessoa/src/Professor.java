import java.time.LocalDate;

public class Professor extends Pessoa {
    
    private static double horaAula = 35d;

    public Professor(String nome, LocalDate nasc, String doc){
        super(nome, nasc, doc);
    }

    public double salarioBruto(){
        return cargaHoraria * horaAula * 1.20;
    }

    @Override
    public String dadosPessoa(){
        return super.dadosPessoa() + " Salário bruto: R$ "+salarioBruto();
    }
}
