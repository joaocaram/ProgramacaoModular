import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
    
    static Pedido pedido;
    static Pizza pizzaCom2Ingredientes;
    static Pizza pizzaSemIngredientes;

    @BeforeAll
    static public void criarPizzas(){
        pizzaCom2Ingredientes = new Pizza(2);
        pizzaSemIngredientes = new Pizza();
    }

    @BeforeEach
    public void setUp(){
        pedido = new Pedido();
    }

    @Test
    public void adicionaPizzaCorretamente(){
        assertEquals(1, pedido.adicionar(pizzaCom2Ingredientes));
    }

    @Test
    public void naoAdicionaPizzaEmPedidoFechado(){
        pedido.adicionar(pizzaCom2Ingredientes);
        pedido.fecharPedido();
        assertEquals(1,pedido.adicionar(pizzaSemIngredientes));
    }

    @Test
    public void calculaPrecoCorretamente(){
        pedido.adicionar(pizzaSemIngredientes);
        pedido.adicionar(pizzaCom2Ingredientes);
        assertEquals(68d, pedido.precoAPagar(), 0.01);
    }

    @Test
    public void relatorioContemDetalhes(){
        pedido.adicionar(pizzaCom2Ingredientes);
        pedido.adicionar(pizzaSemIngredientes);
        String relatorio = pedido.toString();
        assertTrue(relatorio.contains("29,00"));
        assertTrue(relatorio.contains("39,00"));
        assertTrue(relatorio.contains("68,00"));
    }
}
