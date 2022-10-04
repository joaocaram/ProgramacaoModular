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

 
 /** Classe TrianguloRetangulo: demonstração de polimorfismo universal de inclusão e classes abstratas */
public class TrianguloRetangulo extends Poligono {

	/**
	 * Construtor, recebendo os catetos (base e altura). Valores validados pelo construtor da classe mãe P1olígono.
	 * @param base Base (cateto) do triângulo.
	 * @param altura Altura (cateto) do triângulo.
	 */
	public TrianguloRetangulo(double base, double altura) {
		super(base, altura);
		this.descricao="Triângulo Retângulo";
	}

	/**
	 * Cálculo da hipotenusa do triângulo, usando a precisão da biblioteca Math.
	 * @return Valor da hipotenusa, com a precisão double do Java.
	 */
	private double hipotenusa() {
		return Math.sqrt(Math.pow(base, 2)+Math.pow(altura, 2));
	}


	
	/**
	 * Área do triângulo
	 * @return A área do triângulo, com a precisão double do Java
	 */
	@Override
	public double area() {
		return this.altura*this.base/2.0;
	}

	@Override
	/**
	 * Perímetro do triângulo
	 * @return O perímetro do triângulo, com a precisão double do Java
	 */
	public double perimetro() {
		return this.base+this.altura+this.hipotenusa();
	}

}
