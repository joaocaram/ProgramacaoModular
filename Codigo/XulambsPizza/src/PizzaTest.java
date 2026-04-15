import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PizzaTest {
    
    static Pizza pizza;

    @BeforeEach
    public void setup(){
        //Arrange (para todos)
        pizza = new Pizza();
    }

    @Test
    public void criaUmaPizzaVaziaCorretamente(){
        //Act
        double valorPizza = pizza.valorAPagar();
        
        //Assert
        assertEquals(29d, valorPizza, 0.01);
    }
 

    @Test
    public void adicionaIngredientesCorretamente(){
        //Act
        int totalIngredientes = pizza.adicionarIngredientes(3);
        
        //Assert
        assertEquals(3, totalIngredientes);
    }

    @Test
    public void naoAdicionaIngredientesEmExcesso(){
        //Arrange
        pizza.adicionarIngredientes(3);
        
        //Act
        int totalIngredientes =  pizza.adicionarIngredientes(6);
        
        //Assert
        assertEquals(3, totalIngredientes);
    }

    @Test
    public void calculaPrecoComAdicionaisCorretamente(){
        //Arrange
        pizza.adicionarIngredientes(2);

        //Act
        double valor = pizza.valorAPagar();

        //Assert
        assertEquals(39d, valor, 0.01);
    }

    @Test
    public void notaDeCompraContemDescricaoEPrecoDetalhado(){
        //Arrange
        pizza.adicionarIngredientes(2);
        
        //Act
        String cupom = pizza.cupomDeVenda();

        //Assert
        assertTrue(cupom.contains("29,00") &&
                   cupom.contains("2 adicionais") &&
                   cupom.contains("39,00"));
    }


}
