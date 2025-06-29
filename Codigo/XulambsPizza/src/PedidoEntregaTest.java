import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoEntregaTest {
    
    static Pedido pedido;
    static Comida pizzaCom2Ingredientes;
    static Comida pizzaSemIngredientes;

    @BeforeAll
    static public void criarPizzas(){
        pizzaCom2Ingredientes = new Pizza(2);
        pizzaSemIngredientes = new Pizza();
    }

    @BeforeEach
    public void setUp(){
        pedido = new PedidoEntrega(5);
    }

    @Test
    public void adicionaPizzaCorretamente(){
        try {
            assertEquals(1, pedido.adicionar(pizzaCom2Ingredientes));    
        } catch (OperationNotSupportedException e) {
            fail();
        }
        
    }

    @Test
    public void naoAdicionaPizzaEmPedidoFechado(){
        try {
            pedido.adicionar(pizzaCom2Ingredientes);
            pedido.fecharPedido();
            assertEquals(1,pedido.adicionar(pizzaSemIngredientes));    
        } catch (OperationNotSupportedException e) {
            fail();
        }
        
    }

    @Test
    public void naoAdicionaPizzaAcimaDoLimite(){
        try {
            for(int i=0; i<6; i++)
                pedido.adicionar(new Pizza());
            assertEquals(6,pedido.adicionar(pizzaSemIngredientes));    
        } catch (OperationNotSupportedException e) {
            fail();
        }
    }

    @Test
    public void calculaPrecoCorretamenteComTaxa(){
        try {
        pedido.adicionar(pizzaSemIngredientes);
        assertEquals(34d, pedido.precoAPagar(), 0.01);    
        } catch (OperationNotSupportedException e) {
            fail();
        }

    }

    @Test
    public void calculaPrecoCorretamenteIsentoDeTaxa(){
        try {
            pedido = new PedidoEntrega(2);
            pedido.adicionar(pizzaSemIngredientes);
            assertEquals(29d, pedido.precoAPagar(), 0.01);    
        } catch (OperationNotSupportedException e) {
            fail();
        }
        
    }

    @Test
    public void relatorioContemDetalhes(){
        try {
            pedido.adicionar(pizzaCom2Ingredientes);
            pedido.adicionar(pizzaSemIngredientes);
            String relatorio = pedido.toString();
            assertTrue(relatorio.contains("29,00"));
            assertTrue(relatorio.contains("39,00"));
            assertTrue(relatorio.contains("73,00"));    
        } catch (OperationNotSupportedException e) {
            fail();
        }
        
    }
}
