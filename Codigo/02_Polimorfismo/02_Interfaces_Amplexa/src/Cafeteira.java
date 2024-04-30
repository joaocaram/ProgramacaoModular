/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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
 * Cafeteira: um dispositivo desligável e regulável
 */
public class Cafeteira extends Dispositivo implements IDesligavel, IRegulavel{

	private boolean ligado;
	private int potencia;

	/**
	 * Cria uma cafeteira desligada, com o nome especificado.
	 * @param nome Nome da cafeteira (não deve ser vazio)
	 */
	public Cafeteira(String nome) {
		super(nome);
		desligar();
	}

	/**
	 * Liga a cafeteira, regulando sua potência para 50.
	 * @return TRUE/FALSE com o estado da cafeteira.
	 */
	@Override
	public boolean ligar() {
		regular(50);
		return ligado;
	}

	/**
	 * Desliga a cafeteira.
	 * @return TRUE/FALSE com o estado da cafeteira.
	 */
	@Override
	public boolean desligar() {
		ligado = false;
		setEstado("Cafeteira desligada.");
		return ligado;
	}

	/**
	 * Regula a cafeteira. Se a potência for válida (1 a 100), liga a cafeteira. Caso contrário, não altera seu estado.
	 * @param potencia A potência para regular, que deve ser entre 1 e 100.
	 */
	@Override
	public void regular(int potencia){
		if(potencia>0 && potencia<=100){
			ligado = true;
			this.potencia = potencia;
			setEstado("Cafeteira ligada com potência "+this.potencia);
		}
	}

}
