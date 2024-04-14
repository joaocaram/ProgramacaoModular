import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoDeliveryTest {
 
    PedidoDelivery entrega;

    @BeforeEach
    public void setUp(){
        entrega = new PedidoDelivery(4);
    }

    @Test
    public void adicionaComidasAoPedidoAteOLimite(){
        Comida sanduiche = new Sanduiche();
        for (int i = 0; i < 9; i++) {
            entrega.addComida(sanduiche);
        }
        assertEquals(10, entrega.addComida(sanduiche));
    }

    @Test
    public void adicionaComidasSemEstourarLimite(){
        Comida sanduiche = new Sanduiche();
        for (int i = 0; i < 11; i++) {
            entrega.addComida(sanduiche);
        }
        assertEquals(10, entrega.addComida(sanduiche));
    }

    @Test
    public void taxaDistanciaCurta(){
        assertEquals(0, entrega.taxa(),0.01);
    }

    @Test
    public void taxaDistanciaLonga(){
        entrega = new PedidoDelivery(13.5);
        assertEquals(8, entrega.taxa(),0.01);
    }
}
