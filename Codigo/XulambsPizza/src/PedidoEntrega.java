import java.security.InvalidParameterException;
import java.text.NumberFormat;

/**
 * MIT License
 *
 * Copyright(c) 2022-25 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
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

public class PedidoEntrega extends Pedido{
    private static final int MAX_PIZZAS = 8;
    private static final double[] DISTANCIAS =  {4d,8d,Double.MAX_VALUE};
    private static final double[] TAXAS =       {0d,5d,8d};
    private double distanciaEntrega;

    public PedidoEntrega(double distancia) {
        super(MAX_PIZZAS);
        if(distancia <=0 )
            throw new InvalidParameterException("Distância deve ser maior que 0.");
        
        distanciaEntrega = distancia;
    }

    private double valorTaxa(){
        int pos = 0;
        while(distanciaEntrega > DISTANCIAS[pos])
            pos++;
        return TAXAS[pos];
    }

    @Override
    protected boolean podeAdicionar(){
        return super.podeAdicionar() 
                    && quantComidas < MAX_PIZZAS;
    }


    @Override
    public double precoAPagar(){
        return valorItens() + valorTaxa();
    }
    
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
	
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido Para Entrega");
        relat.append("\n"+detalhesPedido());
        relat.append(String.format("TAXA DE ENTREGA: %s (%.2f km)", moeda.format(valorTaxa()), distanciaEntrega));
        relat.append(String.format("\nTOTAL A PAGAR: %s", moeda.format(precoAPagar())));
        relat.append("\n=============================");
        return relat.toString();
    }

}