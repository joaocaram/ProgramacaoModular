import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoEntregaTest {
    
    PedidoEntrega ped;
    Pizza pizzacomDoisIngredientes;

    @BeforeEach
    public void setUp(){
        pizzacomDoisIngredientes = new Pizza(2);
    }

    @Test
    public void pedidoSemTaxa(){
        ped = new PedidoEntrega(1);
        ped.adicionar(pizzacomDoisIngredientes);
        assertEquals(39d, ped.precoAPagar(), 0.01);
    }

    @Test
    public void pedidoComTaxaBaixa(){
        ped = new PedidoEntrega(5);
        ped.adicionar(pizzacomDoisIngredientes);
        assertEquals(44d, ped.precoAPagar(), 0.01);
    }

    @Test
    public void pedidoComTaxaAlta(){
        ped = new PedidoEntrega(640);
        ped.adicionar(pizzacomDoisIngredientes);
        assertEquals(47d, ped.precoAPagar(), 0.01);
    }

     @Test
    public void naoPodeUltrapassar8Pizzas(){
        ped = new PedidoEntrega(640);
        for (int i = 0; i < 8; i++) {
            ped.adicionar(pizzacomDoisIngredientes);    
        }
        assertEquals(8, ped.adicionar(pizzacomDoisIngredientes));
    }
}
