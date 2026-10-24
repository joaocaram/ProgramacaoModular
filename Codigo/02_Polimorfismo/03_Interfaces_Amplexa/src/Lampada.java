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
 * Interface especificando dispositivos que podem ter sua potência regulada
 */

public class Lampada extends Dispositivo implements IDesligavel {

	private boolean ligado;

	/**
	 * Construtor da lâmpada, a qual é criada desligada. O nome não deve ser vazio
	 * @param nome String como nome identificador da lâmpada.
	 */
	public Lampada(String nome) {
		super(nome);
		desligar();
	}

	/**
	 * Liga uma lâmpada e atualiza seu estado
	 * @return TRUE/FALSE conforme o estado da lâmpada
	 */
	@Override
	public boolean ligar() {
		ligado = true;
		setEstado("Lâmpada acesa.");
		return ligado;
	}

	/**
	 * Desliga uma lâmpada e atualiza seu estado
	 * @return TRUE/FALSE conforme o estado da lâmpada
	 */
	@Override
	public boolean desligar() {
		ligado = false;
		setEstado("Lâmpada apagada.");
		return ligado;
	}

}
