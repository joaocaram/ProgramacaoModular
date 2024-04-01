import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PessoaTest {

    static Pessoa pessoa;
    
    @BeforeAll
    public static void setUp(){
        pessoa = new Pessoa("Pessoa1", LocalDate.of(2000, 1, 1), "Doc1");
    }

    @Test
    public void calculaIdadeJaCompletada(){
        assertEquals(24, pessoa.idade());
    }

    @Test
    public void calculaIdadeIncompleta(){
        pessoa = new Pessoa("Pessoa1", LocalDate.of(2000, 6, 1), "Doc1");
        assertEquals(23, pessoa.idade());
    }

    @Test
    public void calculaIdadeHoje(){
        pessoa = new Pessoa("Pessoa1", LocalDate.of(2000, 4, 1), "Doc1");
        assertEquals(24, pessoa.idade());
    }
}
