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

 /** Conjunto geométrico: uma agregação de formas geométricas de tamanho pré-definido*/
public class ConjuntoGeometrico {

	/** Formas serão armazenadas em vetor */
	private Forma[] formas;
	/** Quantas formas armazenadas no momento */
	private int quantasFormas;
	/** Capacidade de armazenamento do conjunto */
	private int capacidade;

	/**
	 * Construtor para o conjunto. Cria um conjunto com a capacidade indicada. O valor mínimo aceito é 2 (e é auto-corrigido se necessário)
	 * @param capacidade A capacidade máxima do conjunto
	 */
	public ConjuntoGeometrico(int capacidade) {
		if(capacidade<2) capacidade =2;
		this.capacidade = capacidade;
		this.quantasFormas=0;
		this.formas = new Forma[capacidade];
	}


	/**
	 * Retorna a forma armazenada com a maior área. 
	 * @return Uma forma geométrica (a que tiver a maior área)
	 */
	public Forma maiorArea() {
		Forma maior = formas[0];
		for (int i = 1; i < this.quantasFormas; i++) {
			if(this.formas[i].area()>maior.area())
				maior = this.formas[i];
		}
		return maior;
	}

	/**
	 * Adiciona, se possível, uma nova forma ao conjunto. Em caso de conjunto cheio, recusa silenciosamente.
	 * @param nova A nova forma a ser armazenada.
	 */
	public void addForma(Forma nova) {
		if(this.quantasFormas<this.capacidade){
			this.formas[this.quantasFormas] = nova;
			this.quantasFormas++;
		}
	}

	/** Descrição em string do conjunto, incluindo cada uma de suas formas.
	 * @return Uma string com a descrição do conjunto, detalhando cada uma das formas armazenadas.
	 */
	@Override
	public String toString() {
		StringBuilder resumoConjunto = new StringBuilder("Conjunto com "+this.quantasFormas+" formas:\n");
		resumoConjunto.append("--------------------\n");
		for (int i = 0; i < this.quantasFormas; i++) {
			resumoConjunto.append(formas[i]+"\n");
		}
		return resumoConjunto.toString();
	}

}
