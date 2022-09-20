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

 /** Classe Circulo: demonstração de polimorfismo universal de inclusão e classes abstratas */
public class Circulo extends Forma {

	private double raio;

	/**
	 * Construtor. Valida o raio mínimo (1.0)
	 * @param raio Raio para o círculo (valores menores que 1.0 voltam a 1.0)
	 */
	public Circulo(double raio) {
		super("Círculo");
		if(raio<1) raio = 1;
		this.raio = raio;
	}

	@Override
	/**
	 * Área do círculo
	 * @return A área do círculo, com a precisão double do Java
	 */
	public double area() {
		return Math.PI * Math.pow(this.raio, 2);
	}

	
	/**
	 * Perímetro do círculo
	 * @return O perímetro do círculo, com a precisão double do Java
	 */
	@Override
	public double perimetro() {
		return 2*Math.PI*this.raio;
	}

	@Override
	/**
	 * Descrição do círculo: nome e raio.
	 * @return A descrição do círculo (nome e raio) em formato string.
	 */
	public String toString() {
		return super.toString() + " com raio de "+this.raio+".";
	}

}
