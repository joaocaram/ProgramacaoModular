package com.xulambs.XulambsPizza;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xulambs.XulambsPizza.Models.Pizza;

public class PizzaTest {
    
    static Pizza pizza;

    @BeforeEach
    public void setup(){
        pizza = new Pizza();
    }

    @Test
    public void criaUmaPizzaVaziaCorretamente(){
        assertEquals(29d, pizza.valorFinal(), 0.01);
    }

    @Test
    public void criaUmaPizzaComAdicionaisCorretamente(){
        pizza = new Pizza(2);
        assertEquals(39d, pizza.valorFinal(), 0.01);
    }

    @Test
    public void criaPizzaCorretamenteMesmoComAdicionaisInválidos(){
        pizza = new Pizza(-4);
        assertEquals(29d, pizza.valorFinal(), 0.01);
    }

    @Test
    public void adicionaIngredientesCorretamente(){
        assertEquals(3, pizza.adicionarIngredientes(3));
    }

    @Test
    public void naoAdicionaIngredientesEmExcesso(){
        pizza.adicionarIngredientes(3);
        assertEquals(3, pizza.adicionarIngredientes(6));
    }

    @Test
    public void calculaPrecoComAdicionaisCorretamente(){
        pizza.adicionarIngredientes(3);
        assertEquals(44d, pizza.valorFinal(), 0.01);
    }

    @Test
    public void notaDeCompraContemDescricaoEPrecoDetalhado(){
        pizza.adicionarIngredientes(3);
        assertTrue(pizza.notaDeCompra().contains("29,00"));
        assertTrue(pizza.notaDeCompra().contains("3 ingredientes"));
        assertTrue(pizza.notaDeCompra().contains("44,00"));
    }


}
