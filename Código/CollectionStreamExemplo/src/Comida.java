import java.util.ArrayList;

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
  * Classe Comida: POO básica
  * Usando: encapsulamento, construtores, static
  */
public abstract class Comida implements Comparable<Comida> {
    
    //#region atributos
    protected String descricao;
    protected double precoBase=0d;  
    protected ArrayList<Ingrediente> ingredientes;


    //#endregion 

    /**
     * Cria uma comida (1 para Sanduíche, 2 ou mais para Pizza - defesa contra objetos vazios)
     * @param tipo 1 para Sanduíche, 2 ou mais para Pizza
     * @param qtdExtras Extras pedidos pelo cliente.
     */
    public Comida(String descricao){
        this.descricao = descricao;
        this.ingredientes = new ArrayList<Ingrediente>(10);
     
    }

    /**
     * Calcula o preço final: base + adicionais * preço dos adicionais
     * @return Preço final (double)
     */
    public double precoFinal(){       
        return (this.precoBase + this.precoAdicionais());
    }

    protected double precoAdicionais(){
        double aux=0d;
        for (Ingrediente ingrediente : ingredientes) {
            aux+= ingrediente.precoFinal();
        }
       
        return aux;
    }
    /**
     * Tenta adicionar ingredientes à comida. Só será executado se o total não exceder o limite
     * @param quantos Quantidade de ingredientes a adicionar.
     */
    public void addIngrediente(Ingrediente qual){
        this.ingredientes.add(qual);
        
    }

    public void removeIngrediente(Ingrediente qual){
        this.ingredientes.remove(qual);
    }
   
    public int totalAdicionais(){
        return this.ingredientes.size();
    }

    /**
     * Retorna a descrição da comida. Formato:
     * <descricao> com <qtdAdicionais> adicionais - R$ <precoFinal> 
     * @return String no formato indicado.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("-----\n");
        sb.append(this.descricao+"  - R$"+ this.precoBase+"\n");
       
       for (Ingrediente ingrediente : ingredientes) {
            if(ingrediente!=null)
            sb.append(ingrediente+"\n");
       }
       
        sb.append("Valor a pagar: R$"+this.precoFinal()+"\n");
        sb.append("-----");
        return sb.toString();
    }
  
    
    @Override
    public int compareTo(Comida o) {
        if(this.precoFinal() > o.precoFinal()) return 1;
        else if(this.precoFinal() < o.precoFinal()) return -1;
        return 0;
    }
   
}
