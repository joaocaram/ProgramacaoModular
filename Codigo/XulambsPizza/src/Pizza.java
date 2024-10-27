/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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

import java.text.NumberFormat;

/** Classe Pizza para a Xulambs Pizza. Uma pizza tem um preço base e pode ter até 8 ingredientes adicionais. Cada ingrediente tem custo fixo.
  * A pizza deve emitir uma nota de compra com os seus detalhes.
  */
public class Pizza implements IComida{

	private static final EComida tipo = EComida.PIZZA;
    private static final int ADIC_DESCONTO = 5;
	private static final double PCT_DESCONTO = 0.5;
	
    private int quantosAdicionais;

    /**
     * Construtor padrão. Cria uma pizza sem adicionais.
     */
	public Pizza()  {
        
	}

    /**
     * Cria uma pizza com a quantidade de adicionais pré-definida. Em caso de valor inválido, a pizza será criada sem adicionais.
     * @param quantosAdicionais Quantidade de adicionais (entre 0 e 8, limites inclusivos).
     */
	public Pizza(int quantosAdicionais) {
		adicionarIngredientes(quantosAdicionais);
	}


    private double descontoAdicionais() {
        double desconto = 0d;
        int quantosComDesconto = quantosAdicionais - ADIC_DESCONTO;
        if(quantosComDesconto > 0)
            desconto = quantosComDesconto * tipo.getValorAdicional() * PCT_DESCONTO;
        return desconto;
    }
    
    /**
     * Retorna o valor final da pizza, incluindo seus adicionais.
     * @return Double com o valor final da pizza.
     */
    @Override
	public double valorFinal() {
		return tipo.getPrecoBase() + valorAdicionais() - descontoAdicionais();
	}

    /**
     * Nota simplificada de compra: descrição da pizza, dos ingredientes e do preço.
     * @return String no formato "<DESCRICAO>, no valor de <VALOR>"
     */
    @Override
	public String notaDeCompra() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        
        StringBuilder nota = new StringBuilder();
        nota.append(String.format("%s (%s) com %d ingredientes (%s)", 
                                    tipo.getDescricao(), moeda.format(tipo.getPrecoBase()), 
                                    quantosAdicionais, moeda.format(valorAdicionais())));

        nota.append(String.format("\n\tDesconto: %s", moeda.format(descontoAdicionais())));
        nota.append(String.format("\nVALOR A PAGAR: %s", moeda.format(valorFinal())));
        return nota.toString();
	}

    @Override
    public int adicionarIngredientes(int quantos) {
        if(quantosAdicionais+quantos < tipo.getMaxIngredientes())
            quantosAdicionais += quantos;
        return quantosAdicionais;
        
    }

    @Override
    public double valorAdicionais() {
        return quantosAdicionais * tipo.getValorAdicional();
    }

}
