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

public class Lista<T extends Comparable<T>> implements Iterable<T>{
    Elemento<T> primeiro, ultimo;
    int tamanho;

    public Lista(){
        this.primeiro = new Elemento<T>(null);
        this.ultimo = this.primeiro;
        
        this.tamanho=0;
    }

    public void inserirNoFim(T dado){
        Elemento<T> novoElemento = new Elemento<T>(dado);
        this.ultimo.proximo = novoElemento;
        this.ultimo = novoElemento;
        this.tamanho++;
    }

    public int tamanho(){
        return this.tamanho;
    }
    
    public T localizar(T quem){
        Elemento<T> aux = this.primeiro.proximo;
        while(aux!=null){
            if(quem.equals(aux.dado()))
                return aux.dado();
            else
                aux = aux.proximo;
        }
        return null;
    }

    public T maiorElemento(){
        Comparator<T> c = (o1,o2)-> o1.compareTo(o2);
        return maiorElemento(c);
    }
    
   
    public T maiorElemento(Comparator<T> comparador){
        if(tamanho>0){
            T maior = this.primeiro.proximo.dado();
            Elemento<T> aux = this.primeiro.proximo;
            while(aux!=null){
                if(comparador.compare(aux.dado(), maior)>0)
                    maior = aux.dado();
                aux = aux.proximo;
            }
            return maior;
        }
        else 
            return null;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            Elemento<T> atual = primeiro.proximo;
            
            @Override
            public boolean hasNext() {
                return (atual!=null);
            }

            @Override
            public T next() {
                T dado = atual.dado();
                atual = atual.proximo;
                return dado;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

   
}
