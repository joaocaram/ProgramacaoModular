import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Pessoa {
    static Random sorteio = new Random(42);
    protected int id;
    protected String nome;
    protected String documento;
    protected LocalDate dataNasc;
    protected String email;
    protected int cargaHoraria;

    public Pessoa(String nome, LocalDate nascimento, String documento) {
        this.nome = nome;
        this.dataNasc = nascimento;
        this.documento = documento;
        this.id = sorteio.nextInt() % 1_000_000;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria > 0)
            this.cargaHoraria = cargaHoraria;
    }

    public int idade() {
        LocalDate hoje = LocalDate.now();

        Period intervalo = dataNasc.until(hoje);
        
        return intervalo.getYears();
    }

    public void enviarEmail(String texto) {
        // fingindo que estou usando um serviço bacanudo para enviar emails
    }

    
}
