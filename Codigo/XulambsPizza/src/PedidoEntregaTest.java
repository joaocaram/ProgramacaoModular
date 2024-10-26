import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoEntregaTest {
    
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
        pedido = new PedidoEntrega(5);
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
    public void naoAdicionaPizzaAcimaDoLimite(){
        for(int i=0; i<6; i++)
            pedido.adicionar(new Pizza());
        assertEquals(6,pedido.adicionar(pizzaSemIngredientes));
    }


    @Test
    public void calculaPrecoCorretamenteComTaxa(){
        pedido.adicionar(pizzaSemIngredientes);
        assertEquals(34d, pedido.precoAPagar(), 0.01);
    }

    @Test
    public void calculaPrecoCorretamenteIsentoDeTaxa(){
        pedido = new PedidoEntrega(2);
        pedido.adicionar(pizzaSemIngredientes);
        assertEquals(29d, pedido.precoAPagar(), 0.01);
    }

    @Test
    public void relatorioContemDetalhes(){
        pedido.adicionar(pizzaCom2Ingredientes);
        pedido.adicionar(pizzaSemIngredientes);
        String relatorio = pedido.toString();
        assertTrue(relatorio.contains("29,00"));
        assertTrue(relatorio.contains("39,00"));
        assertTrue(relatorio.contains("73,00"));
    }
}
