/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
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

import java.util.ArrayList;
import java.util.List;

/** Encapsula um conjunto de formas geométricas */
public class ConjuntoGeometrico {

	private List<FormaGeometrica> formas;
	int capacidade;

    /**
     * Cria um conjunto vazio de formas. A capacidade mínima do conjunto é 2.
     * @param capacidade Capacidade do conjunto. Valores menores que 2 viram 2.
     */
	public ConjuntoGeometrico(int capacidade) {
		if(capacidade <2) 
			capacidade = 2;
		formas = new ArrayList<>(capacidade);
		this.capacidade = capacidade;
	}

    /**
     * Retorna a forma com a maior área dentro do conjunto. Pode retornar nulo, se o conjunto estiver vazio.
     * @return A forma com a maior área do conjunto, ou nulo se ele estiver vazio.
     */
	public FormaGeometrica maiorArea() {
		FormaGeometrica maior = null;
		if(formas.size()>0){
			maior = formas.get(0);
			for (int i = 1; i < formas.size(); i++) {
				if(formas.get(i).area() > maior.area())
					maior = formas.get(i);
			}
		}
		
		return maior;
	}

    /**
     * Caso tenha espaço, armazena uma forma no conjunto. Caso contrário, a operação é ignorada.
     * @param nova A forma a ser armazenada.
	 * @return A quantidade de formas no conjunto atual
     */
	public int addForma(FormaGeometrica nova) {
		if(formas.size()<capacidade){
			formas.add(nova);
		}
		return formas.size();
		
	}

	
    /**
     * Representação do conjunto em String: cabeçalho informando a quantidade de formas do conjunto
     * e em seguida uma linha para descrição de cada forma.
     * @return String com a representação informada acima.
     */
	@Override
	public String toString() {
		StringBuilder relat = new StringBuilder("CONJUNTO COM "+formas.size()+" FORMAS GEOMÉTRICAS:");
		for (FormaGeometrica forma : formas) {
			if(forma!=null)
            	relat.append("\n"+forma);
        }
		return relat.toString();
	}

}
