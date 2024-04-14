import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PizzaTest {
    
    Pizza simples;
    Pizza comBorda;

    @BeforeEach
    public void config(){
        simples = new Pizza();
        comBorda = new Pizza(0, true);
    }

    @Test
    public void valorDaPizzaSimples(){
        assertEquals(29.0, simples.precoFinal(), 0.01);
    }

    @Test
    public void valorDaPizzaComBorda(){
        assertEquals(34.5, comBorda.precoFinal(), 0.01);
    }

    

    @Test
    public void calculaIngredientesSemDesconto(){
        simples.adicionarIngredientes(5);
        assertEquals(49.0, simples.precoFinal(), 0.01);
    }

    @Test
    public void calculaIngredientesComDesconto(){
        simples.adicionarIngredientes(6);
        assertEquals(51.0, simples.precoFinal(), 0.01);
    }

    @Test
    public void calculaPizzaComBordaEComDesconto(){
        comBorda.adicionarIngredientes(6);
        assertEquals(56.5, comBorda.precoFinal(), 0.01);
    }
}
