
/** 
 * MIT License
 *
 * Copyright(c) 2024-26 João Caram <caram@pucminas.br>
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
 * Classe Pizza para a Xulambs Pizza. Uma pizza tem um preço base e pode ter até
 * 8 ingredientes adicionais. Cada ingrediente tem custo fixo.
 * A pizza deve emitir uma nota de compra com os seus detalhes.
 */
public class Pizza {

    private int maxIngredientes;
    private String descricao;
    private double precoBase;
    private double valorPorAdicional;
    private int quantidadeIngredientes;

    /**
     * Construtor padrão. Cria uma pizza sem adicionais.
     */
    public Pizza() {
        maxIngredientes = 8;
        descricao = "Pizza sem adicionais.";
        precoBase = 29d;
        valorPorAdicional = 5d;
        quantidadeIngredientes = 0;
    }

    /**
     * Retorna o valor final da pizza, incluindo seus adicionais.
     * 
     * @return Double com o valor final da pizza.
     */
    public double valorAPagar() {
        return precoBase + quantidadeIngredientes * valorPorAdicional;
    }

    /**
     * Tenta adicionar ingredientes na pizza. Caso a adição seja inválida
     * (ultrapassando limites ou com valores negativos), mantém
     * a quantidade atual de ingredientes. Retorna a quantidade de ingredientes após
     * a execução do método.
     * 
     * @param quantos Quantos ingredientes a serem adicionados (>0)
     * @return Quantos ingredientes a pizza tem após a execução
     */
    public int adicionarIngredientes(int quantos) {
        int novosIngredientes = quantidadeIngredientes + quantos;
        if (quantos > 0 && novosIngredientes <= maxIngredientes) {
            quantidadeIngredientes = novosIngredientes;
            descricao = "Pizza com " + quantidadeIngredientes + " adicionais.";
        }
        return quantidadeIngredientes;
    }

    /**
     * Nota simplificada de compra: descrição da pizza, dos ingredientes e do preço.
     * 
     * @return String no formato "<DESCRICAO>, no valor de <VALOR>"
     */
    public String cupomDeVenda() {
        double valor = valorAPagar();
        double adicionais = quantidadeIngredientes * valorPorAdicional;
        return String.format("%s \n\tPreço inicial: R$ %.2f \n\tAdicionais: R$ %.2f \nVALOR A PAGAR: R$ %.2f",
                descricao, precoBase, adicionais, valor);
    }

}
