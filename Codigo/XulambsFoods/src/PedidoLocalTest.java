import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoLocalTest {
    PedidoLocal pedidoLocal;
    Comida sand;

    @BeforeEach
    public void setUp(){
        pedidoLocal = new PedidoLocal();
        sand = new Sanduiche();
    }

    @Test
    public void adicionaComidaSemLimites(){
        for (int i = 0; i < 49; i++) {
            pedidoLocal.addComida(sand);
        }
        assertEquals(50, pedidoLocal.addComida(sand));
    }

    @Test
    public void calculaPrecoComServico(){
        pedidoLocal.addComida(sand);
        pedidoLocal.fecharPedido();
        assertEquals(16.5, pedidoLocal.precoFinal(),0.10);

    }
}