/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
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

public abstract class Comida  implements IOrdenavel{
    
    protected double valorBasico;
    protected String descricao; 
    protected boolean vendaFechada;
    
    protected Comida(double valor, String descricao){
        
        this.valorBasico = valor;
        this.descricao = descricao;
        this.vendaFechada=false;

    }
    protected abstract double valorAdicionais();

    /**
     * Calcula o preco a ser cobrado pela pizza (valor básico + valor dos adicionais). Perceba que, se considerado adequado,
     * o método para o preço dos adicionais poderia ser um método à parte - talvez privado.
     * @return Valor atual a ser cobrado pela pizza.
     */
    public double calcularPreco(){
        return this.valorBasico + this.valorAdicionais();
    }

    /**
     * Fecha a venda da pizza. Depois deste método, não se pode mais adicionar ingredientes. Sempre retorna a nota atual de venda.
     * @return A nota simplificada de venda (string formatada).
     */
    public String fecharVenda(){
        this.vendaFechada = true;
        return toString();
    }

    @Override
    public boolean maiorQue(IOrdenavel outra){
        Comida outraComida = (Comida)(outra);
        return (this.descricao.compareTo(outraComida.descricao)>0);

    }
   
}
