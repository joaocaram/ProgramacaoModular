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

 /** Classe para representar a forma Retângulo */
public class Retangulo extends PoligonoReto{
    
    /**
     * Construtor: recebe base e altura. A classe-mãe valida ambos valores para o mínimo de 1
     * @param base Base para o retângulo. Valor deve ser igual ou maior a 1, ou será corrigido para 1.
     * @param altura Altura para o retângulo. Valor deve ser igual ou maior a 1, ou será corrigido para 1.
     */
    public Retangulo(double base, double altura){
        super("RETÂNGULO", base, altura);
    }

    /**
     * Calcula e retorna a área do retângulo
     * @return Área do retângulo (double)
     */
    @Override
    public double area(){
        return this.base * this.altura;
    }

    /**
     * Calcula e retorna o perímetro do retângulo
     * @return Perímetro do retângulo (double)
     */
    @Override
    public double perimetro(){
        return this.base*2 + 2*this.altura;
    }

    /** String do retângulo: inclui informação do tamanho da base e da altura
     * @return String contendo descrição, área, perímetro, base e altura do retângulo
     */
    @Override
    public String toString(){
        return super.toString() + String.format(" | Base: %05.2f | Altura: %05.2f", base, altura);
    }
}
