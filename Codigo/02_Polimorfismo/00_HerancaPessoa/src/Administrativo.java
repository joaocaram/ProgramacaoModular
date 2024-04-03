import java.time.LocalDate;

public class Administrativo extends Pessoa{
    
    private static double salarioBase = 2500.0;
    private double valorGratificacao;

    public Administrativo(String nome, LocalDate nascimento, String doc, double gratif){
        super(nome, nascimento, doc);
        if(gratif > 0)
            valorGratificacao = gratif;
    }

    public double salarioBruto(){
        return salarioBase + valorGratificacao;        
    }

    @Override
    public String dadosPessoa(){
        return super.dadosPessoa()+" Funcionário administrativo com salário de R$ " +salarioBruto();
    }
}
