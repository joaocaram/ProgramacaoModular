import java.time.LocalDate;

public class Aluno extends Pessoa{

    private static double horaAula = 28.5;
    private double valorMatricula;

    public Aluno(String nome, LocalDate nascimento, String doc, double mat){
        super(nome, nascimento, doc);
        if(mat >0)
            this.valorMatricula = mat;
    }

    public double valorMensalidade(){
        return ((horaAula * cargaHoraria) - valorMatricula) / 6.0;
    }
    
    @Override
    public String dadosPessoa(){
        return super.dadosPessoa() + " Mensalidade: R$ "+valorMensalidade();
    }
}
