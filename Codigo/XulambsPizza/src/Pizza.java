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
public class Pizza extends Comida{

	private static final int MAX_INGREDIENTES = 8;
	private static final String DESCRICAO = "Pizza";
	private static final double PRECO_BASE = 29d;
    private static final double VALOR_ADICIONAL = 5d;
    private static final int ADIC_DESCONTO = 5;
	private static final double PCT_DESCONTO = 0.5;
	


    /**
     * Construtor padrão. Cria uma pizza sem adicionais.
     */
	public Pizza()  {
        super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
	}

    /**
     * Cria uma pizza com a quantidade de adicionais pré-definida. Em caso de valor inválido, a pizza será criada sem adicionais.
     * @param quantosAdicionais Quantidade de adicionais (entre 0 e 8, limites inclusivos).
     */
	public Pizza(int quantosAdicionais) {
        super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
		adicionarIngredientes(quantosAdicionais);
	}

    private double descontoAdicionais() {
        double desconto = 0d;
        int quantosComDesconto = quantidadeIngredientes - ADIC_DESCONTO;
        if(quantosComDesconto > 0)
            desconto = quantosComDesconto * VALOR_ADICIONAL * PCT_DESCONTO;
        return desconto;
    }
    
    /**
     * Retorna o valor final da pizza, incluindo seus adicionais.
     * @return Double com o valor final da pizza.
     */
    @Override
	public double valorFinal() {
		return PRECO_BASE + valorAdicionais() - descontoAdicionais();
	}

    /**
     * Nota simplificada de compra: descrição da pizza, dos ingredientes e do preço.
     * @return String no formato "<DESCRICAO>, no valor de <VALOR>"
     */
    @Override
	public String notaDeCompra() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        String nota = super.notaDeCompra();
        nota += String.format("\n\tDesconto: %s", moeda.format(descontoAdicionais()));
        nota += String.format("\nVALOR A PAGAR: %s", moeda.format(valorFinal()));
        return nota;
	}

}
