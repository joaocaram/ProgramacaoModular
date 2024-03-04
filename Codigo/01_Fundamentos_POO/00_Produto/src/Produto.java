/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
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
 
public class Produto {
    String descricao;
    double valorUnitario;

    /**
     * Registra um produto com descrição e valor unitário.
     * A descricão tem pelo menos 2 caracteres e o valor unitário mínimo é R$0,01.
     * Em caso de violação das regras, o produto ficará "Sem descrição" e com valor de R$0,01.
     * @param desc Descrição do produto (mínimo 2 caracteres)
     * @param valor Preço unitário (mínimo R$0,01)
     */
    public void registrar(String desc, double valor){
            descricao = desc;
            valorUnitario = valor;
            if(descricao.length()<2)    
                descricao = "Sem descrição";
            if(valorUnitario<=0d)
                valorUnitario = 0.01;
    }

    /**
     * Calcula o preço de venda de um lote de "quant" produtos.
     * Em caso de quantidade não positiva, retorna 0.
     * @param quant Quantidade de produtos a ser vendida (>0)
     * @return Double com o valor do lote, ou valor 0.0 em caso de lote/quantidade inválida.    
     */
    public double valorLote(int quant){
        double resposta = 0d;
        if(quant >0 )
            resposta = valorUnitario * quant; 
        return resposta;
    }
     
}