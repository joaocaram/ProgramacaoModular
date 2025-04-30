import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PizzaTest {

    @Test
    public void calculaPrecoComIngredientesCorretamente(){
        Pizza pizza = new Pizza(4);
        assertEquals(49d, pizza.valorFinal(), 0.01);
    }
}
