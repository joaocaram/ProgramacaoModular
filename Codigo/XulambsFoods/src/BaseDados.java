import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;


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
@SuppressWarnings("unchecked")
public class BaseDados<K,T> {
    private HashMap<K, T> dados;
    private T[] dadosOrdenados;
    
    public BaseDados(int capacidade){
        if(capacidade<16)
            capacidade = 16;
        dados = new HashMap<>(capacidade);
    }

    public void ordenar(Comparator<T> comparador){
        dadosOrdenados = (T[])new Object[dados.size()];
        dadosOrdenados = dados.values().toArray(dadosOrdenados);
        Arrays.sort(dadosOrdenados, comparador);
    }

    public String relatorio() {
        StringBuilder relat = new StringBuilder("Resumo dos dados:\n");    
        for (T dado : dadosOrdenados) {
                relat.append("\n"+dado+"\n");
        }
        return relat.toString();
    }

    public T localizar(K identificador) {
            return dados.get(identificador);
    }

    public void cadastrar(K chave,T dado) {
        dados.put(chave, dado);
    }

    public double totalizar(Function<T, Double> extrator){
        double total=0d;
        for(T dado : dados.values()){
            total += extrator.apply(dado);
        }
        return total;
    }

    public void processar(Consumer<T> metodo){
        for(T dado : dados.values()){
            metodo.accept(dado);
        }
    }
}
