import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
    Pedido pedido;

    @BeforeEach
    public void setUp(){
        pedido = new PedidoDelivery(0.1);
    }
    
    @Test
    public void criaPedidoVazio(){
        assertEquals(0.0, pedido.precoFinal(), 0.01);
    }

    @Test
    public void calculaPrecoCorretamente(){
        Comida sanduiche = new Sanduiche();
        pedido.addComida(sanduiche);
        pedido.addComida(sanduiche);
        assertEquals(30.0, pedido.precoFinal(), 0.01);
    }

    

    
}

