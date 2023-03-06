import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
 
 /**
  * Teste unitário básico para classe Pizza.
  */

public class PizzaTest {
    Pizza pizzaBasica;
    Pizza pizzaComAdicional;

    @BeforeEach  
    public void prepare(){
        pizzaBasica = new Pizza();
        pizzaComAdicional = new Pizza(2);
    }

    
    @Test
    public void calculaPrecoBaseCorreto(){
        assertEquals(BigDecimal.valueOf(25), pizzaBasica.precoFinal());
    }

    @Test
    public void deixaRetirarAdicionais(){
        pizzaComAdicional.retirarIngrediente(2);
        assertEquals(BigDecimal.valueOf(25), pizzaBasica.precoFinal());
    }

    @Test
    public void naoPodeTerAdicionaisNegativos(){
        pizzaBasica.retirarIngrediente(5);
        assertEquals(BigDecimal.valueOf(25), pizzaBasica.precoFinal());
    }

    @Test
    public void naoPodeTerAdicionaisAlemDoMaximo(){
        pizzaBasica.adicionarIngrediente(Pizza.MAX_ADICIONAIS_PIZZA+1);
        assertEquals(BigDecimal.valueOf(25), pizzaBasica.precoFinal());
    }

    @Test
    public void calculaPrecoCorretoComAdicionais(){
        pizzaBasica.adicionarIngrediente(2);
        assertEquals(BigDecimal.valueOf(33), pizzaBasica.precoFinal());
    }

    @Test
    public void deixaCriarPizzaComAdicionais(){
        assertEquals(BigDecimal.valueOf(33), pizzaComAdicional.precoFinal());
    }

    public void geraNotaDeCompra(){
        String nota = pizzaComAdicional.imprimirNotaDeCompra();
        assertTrue(nota.contains("Pizza com queijo e calabresa")&&nota.contains("R$ 33"));
    }
}
