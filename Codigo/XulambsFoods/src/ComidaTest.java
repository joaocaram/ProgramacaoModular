import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste básico para a classe comida. 
 * Exemplo simples de JUnit, sem preocupação com cobertura total de testes.
 */
public class ComidaTest {
    
    Comida pizza;
    Comida sanduiche;

    @BeforeEach
    public void setUp(){
        pizza = new Comida("pizza");
        sanduiche = new Comida("sanduiche");
    }

    @Test
    public void calculaPrecoDaPizzaCorretamente(){
        assertEquals(29.0, pizza.precoFinal(),0.01);
    }

    @Test
    public void calculaPrecoDaPizzaComAdicionaisCorretamente(){
        pizza.adicionarIngredientes(2);
        assertEquals(39.0, pizza.precoFinal(),0.01);
    }

    @Test
    public void adicionaIngredientesCorretamenteNaComida(){
        assertEquals(3,sanduiche.adicionarIngredientes(3));        
    }

    @Test
    public void naoAdicionaIngredientesEmExcessoNaComida(){
        sanduiche.adicionarIngredientes(4);
        assertEquals(4,sanduiche.adicionarIngredientes(3));        
    }

    @Test
    public void naoAdicionaIngredientesEmExcessoNaPizza(){
        
        for (int i = 0; i < 10; i++) {
            pizza.adicionarIngredientes(1);
        }        
        assertTrue(pizza.relatorio().contains("8 adicionais"));
    }

    @Test
    public void naoAdicionaIngredienteNegativo(){

        assertEquals(0,pizza.adicionarIngredientes(-1));
    }
}




