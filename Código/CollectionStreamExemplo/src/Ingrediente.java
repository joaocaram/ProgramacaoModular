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

/**
 * Classe ingrediente para o restaurante. Armazena nome, valor individual e valor acumulado para um ingrediente.
 */
public class Ingrediente implements Comparable<Ingrediente>{
    
    //atributos
    public String nome;
    private double preco;
    protected int quantidade;

    /**
     * Construtor simples. Quantidade mínima 1 e valor mínimo R$0,50
     * @param desc Descrição, sem validação
     * @param quant Quantidade (mínimo 1)
     * @param valor Valor individual (mínimo 0,50)
     */
    public Ingrediente(String desc, int quant, double valor){
        this.preco = 0.50;
        this.quantidade = 1;
        this.nome = desc;
        if(valor >0.50) this.preco = valor;
        if(quant >1) this.quantidade = quant;
    }

    /**
     * Preço final: valor individual x quantidade
     * @return Valor do ingrediente
     */
    public double precoFinal(){
        return this.preco*this.quantidade;
    }

    /**
     * Descrição dos dados do ingrediente
     * @return String com nome, quantidade e valor final
     */
    @Override
    public String toString(){
        String aux =  this.nome+"("+this.quantidade+") ";
        if(this.nome.length()<=7) aux+="\t";
        aux+= " R$ "+this.precoFinal();
        return aux;
    }

    @Override
    public int compareTo(Ingrediente i){
        return this.nome.compareTo(i.nome);
    }

    @Override
    public boolean equals(Object o){
        Ingrediente i = (Ingrediente)o;
        return this.nome.equals(i.nome);
    }
    
}