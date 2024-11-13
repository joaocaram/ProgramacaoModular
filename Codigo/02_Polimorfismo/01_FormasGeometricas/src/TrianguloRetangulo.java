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

 /** Representação de uma forma Triângulo Retângulo */
public class TrianguloRetangulo extends PoligonoReto{
    
    /**
     * Construtor: recebe os catetos. A classe-mãe valida ambos valores para o mínimo de 1
     * @param cateto1 Cateto para o triângulo. Valor deve ser igual ou maior a 1, ou será corrigido para 1.
     * @param cateto2 Cateto para o triângulo. Valor deve ser igual ou maior a 1, ou será corrigido para 1.
     */
    public TrianguloRetangulo(double cateto1, double cateto2){
        super("TRIANGULO RETÂNGULO", cateto1, cateto2);
    }

    /**
     * Calcula e retorna a área do triângulo
     * @return Área do triângulo (double)
     */
    @Override
    public double area(){
        return (this.base * this.altura)/2;
    }

    /**
     * Calcula e retorna o perímetro do triângulo
     * @return Perímetro do triângulo (double)
     */
    @Override
    public double perimetro(){
        return this.base + this.altura + hipotenusa();
    }

    /**
     * Método interno. Calcula a hipotenusa para cálculo do perímetro.
     * @return Hipotenusa do triângulo (double)
     */
    private double hipotenusa(){
        double soma = Math.pow(altura, 2) + Math.pow(base, 2); 
        return Math.sqrt(soma);
    }

    /** String do triângulo retângulo: inclui informação dos catetos (base e altura) e da hipotenusa
     * @return String contendo descrição, área, perímetro, catetos e hipotenusa do retângulo
     */
    @Override
    public String toString(){
        return super.toString() + String.format(" | Catetos: %05.2f e %05.2f | Hipotenusa: %05.2f", base, altura, hipotenusa());
    }
}
