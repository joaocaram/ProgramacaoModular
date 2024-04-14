import static org.junit.Assert.assertEquals;

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
        pizza = new Pizza();
        sanduiche = new Sanduiche();
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
    public void naoAdicionaIngredienteNegativo(){

        assertEquals(0,pizza.adicionarIngredientes(-1));
    }
}




