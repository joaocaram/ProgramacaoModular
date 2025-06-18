import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    static Pedido pedido;
    static Comida pizzaCom2Ingredientes;
    static Comida pizzaSemIngredientes;

    @BeforeAll
    static public void criarPizzas() {
        pizzaCom2Ingredientes = new Pizza(2);
        pizzaSemIngredientes = new Pizza();
    }

    @BeforeEach
    public void setUp() {
        pedido = new PedidoLocal();
    }

    @Test
    public void adicionaPizzaCorretamente() {
        try {
            assertEquals(1, pedido.adicionar(pizzaCom2Ingredientes));
        } catch (OperationNotSupportedException ex) {
            fail(ex.getMessage());
        }

    }

    @Test
    public void naoAdicionaPizzaEmPedidoFechado(){
        try{
            pedido.adicionar(pizzaCom2Ingredientes);
            pedido.fecharPedido();
            assertEquals(1,pedido.adicionar(pizzaSemIngredientes));
        }catch(OperationNotSupportedException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void calculaPrecoCorretamente() {
        try {
            pedido.adicionar(pizzaSemIngredientes);
            pedido.adicionar(pizzaCom2Ingredientes);
            assertEquals(68d, pedido.precoAPagar(), 0.01);
        } catch (OperationNotSupportedException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void relatorioContemDetalhes() {
        try {
            pedido.adicionar(pizzaCom2Ingredientes);
            pedido.adicionar(pizzaSemIngredientes);
            String relatorio = pedido.toString();
            assertTrue(relatorio.contains("29,00"));
            assertTrue(relatorio.contains("39,00"));
            assertTrue(relatorio.contains("68,00"));
        } catch (OperationNotSupportedException ex) {
            fail(ex.getMessage());
        }
    }
}
