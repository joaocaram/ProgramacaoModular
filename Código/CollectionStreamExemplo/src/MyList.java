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


import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("unchecked")
/** 
 * Classe-demonstração lista estática para polimorfimos paramétrico e implementação de interfaces
 * A lista é parametrizada por T, que implementa Comparable
 * A lista implementa iterable
 */
public class MyList<T extends Comparable<T>> implements Iterable<T>{
    
    //atributos
    private T[] dados;
    private int capacidade;
    private int quantidade;
    private int atual;      //usado para o 'iterator'
   
   
    /**
     * Construtor para lista, tamanho mínimo 2
     * @param maxSize Tamanho máximo (2 em diante)
     */
    public MyList(int maxSize){
        this.capacidade = 2;
        if(maxSize>2) this.capacidade = maxSize;
        this.dados = (T[])new Comparable[maxSize];  //atenção: cast sem verificação - não é boa prática. Apenas para demonstração simples
        this.quantidade = 0;     
        this.atual = -1;
    }

    /**
     * Insere ao final, estilo fila. Se não couber, ignora a operação
     * @param newData Objeto a inserir
     */
    public void addAtEnd(T newData){
        if(this.quantidade < this.capacidade){
            dados[this.quantidade] = newData;
            this.quantidade++;
            if(atual==-1) atual=0;
        }
    }

    /**
     * Remove de uma posição específica e reposiciona outros elementos
     * @param pos Posição de remoção
     * @return Elemento removido ou nulo, se não existir a posição
     */
    public T removeAt(int pos){
        T aux = null;
        if(pos>=0 && pos < this.quantidade){
            aux = this.dados[pos];
            for(int i=pos+1; i<quantidade; i++){
                this.dados[i-1] = this.dados[i];            
            }
            this.quantidade--;
            if(this.atual == this.quantidade) 
                this.atual--;
        }
        return aux;
    }

    /**
     * Remove por igualdade de objeto. Polimorfismo pelo 'equals'
     * @param who Objeto (mock) a ser removido
     * @return Elemento removido ou nulo, se não existir
     */
    public T removeObject(T who){
        T aux = null;
        for(int i = 0 ; i<this.quantidade; i++){
            if(dados[i].equals(who)){
                aux = dados[i];
                for(int j=i+1; j<quantidade; j++){
                    this.dados[j-1] = this.dados[j];            
                }
                this.quantidade--;
                if(this.atual == this.quantidade) 
                    this.atual--;
            }
        }
        return aux;
    }
  
    /**
     * Maior elemento da lista. Demonstração do polimorfismo paramétrico com compareTo
     * @return O maior elemento da lista, pelo comparador padrão de <T>
     */
    public T greatest(){
        T great = this.dados[0];
        for(int i=0; i<this.quantidade; i++){
            if(this.dados[i].compareTo(great)>0)
                great = this.dados[i];
        }
        return great;
    }

    /**
     * Maior elemento da lista. Demonstração do polimorfismo paramétrico com parâmetro explícito
     * @param comparador O comparador de <T> a ser utilizado 
     * @return O maior elemento da lista, pelo comparador parametrizado
     */
    public T greatest(Comparator<T> comparador){
        T great = this.dados[0];
        for(int i=0; i<this.quantidade; i++){
            if(comparador.compare(this.dados[i], great)>0)
                great = this.dados[i];
        }
        return great;
    }

    /**
     * Retorna, sem retirar, o objeto da posição específica.
     * @param pos Posição para o 'peek'
     * @return Objeto nesta posição, ou nulo se posição inválida
     */
    public T objectAt(int pos){
        if(pos>=0 && pos<this.quantidade)
            return this.dados[pos];
        else
            return null;
    }

    @Override
    /**
     * Relatório da lista com todos os objetos
     * @return Descrição completa da lista, um objeto por linha
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.quantidade; i++) {
            sb.append(this.dados[i].toString()+"\n");
        }
        return sb.toString();
    }

    @Override
    /**
     * Iterator para a interface Iterable.
     * Demonstração de uso, porém código "padrão"
     * 'Remove' usa o removeAt da própria lista
     */
    public Iterator<T> iterator() {
        return new Iterator<T>(){

            @Override
            public boolean hasNext() {
                return (quantidade>0 && atual<quantidade );
            }

            @Override
            public T next() {
                T retorno = null;
                if(atual!=-1 && atual<(quantidade)){
                    atual++;
                    retorno = dados[atual];                
                }
                return retorno;
            }

            @Override
            public void remove(){
                removeAt(atual);
            }
            
            
        };
        
    }
}
