import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
    Pedido pedido;

    @BeforeEach
    public void setUp(){
        pedido = new Pedido();
    }
    
    @Test
    public void criaPedidoVazio(){
        assertEquals(0.0, pedido.precoFinal(), 0.01);
    }

    @Test
    public void adicionaComidasAoPedido(){
        Comida sanduiche = new Comida("Sanduiche");
        assertEquals(1,pedido.addComida(sanduiche));
    }

    @Test
    public void adicionaComidasAoPedidoAteOLimite(){
        Comida sanduiche = new Comida("Sanduiche");
        for (int i = 0; i < 9; i++) {
            pedido.addComida(sanduiche);
        }
        assertEquals(10,pedido.addComida(sanduiche));
    }

    @Test
    public void naoPodePassarDe10Comidas(){
        Comida sanduiche = new Comida("Sanduiche");
        for (int i = 0; i < 10; i++) {
            pedido.addComida(sanduiche);
        }
        assertEquals(10,pedido.addComida(sanduiche));
    }
}
