import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SanduicheTest {
    
    Sanduiche sanduiche;

    @BeforeEach
    public void setUp(){
        sanduiche = new Sanduiche();
    }

    @Test
    public void calculaPrecoSemAdicionais(){
        assertEquals(15.0, sanduiche.precoFinal(), 0.01);
    }

    @Test
    public void calculaPrecoComAdicionais(){
        sanduiche.adicionarIngredientes(4);
        assertEquals(25.0, sanduiche.precoFinal(), 0.01);
    }
}
