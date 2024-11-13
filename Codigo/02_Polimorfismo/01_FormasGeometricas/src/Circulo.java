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


public class Circulo extends FormaGeometrica{
    private double raio;
    
    /**
     * Construtor. O raio mínimo do círculo é 1.
     * @param raio Raio do círculo. Valores menores que 1 são convertidos em 1.
     */
    public Circulo(double raio){
        super("CÍRCULO");
        this.raio = raio;
        if(this.raio<=1)
            this.raio=1d;
    }

   
    /**
     * Calcula e retorna a área do círculo
     * @return Área do círculo (double)
     */
    @Override
    public double area(){
        return Math.PI*Math.pow(this.raio, 2.0);
    }

    /**
     * Calcula e retorna o perímetro do círculo
     * @return Perímetro do círculo (double)
     */
    @Override
    public double perimetro(){
        return 2*Math.PI*this.raio;
    }

    /**
     * Retorna a String com a descrição do círculo (descrição, área e raio)
     * @return String com a descrição conforme especificado.
     */
    @Override
    public String toString(){
        return super.toString() + String.format(" | Raio: %05.2f",raio);
    }
}
