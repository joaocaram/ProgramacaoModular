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

 /** Classe abstrata para representação de formas geomérticas */
public abstract class FormaGeometrica {
    
    private String descricao;

    /**
     * Construtor protegido (só é usado pelas classes descendentes)
     * @param desc A descrição da forma geométrica. Não faz validação da descrição
     */
    protected FormaGeometrica(String desc){
        this.descricao = desc;
    }

    /**
     * Retorna a área da forma geométrica.
     * @return Double com a área da forma geométrica instanciada
     */
    public abstract double area();

    /**
     * Retorna o perímetro da forma geométrica.
     * @return Double com o perímetro da forma geométrica instanciada
     */
    public abstract double perimetro();


    /**
     * Representação em string da forma: descrição e área.
     * @return String com a descrição e a área da forma instanciada.
     */
    @Override
    public String toString(){
        return this.descricao + " com área de "+this.area();
    }

    /**
     * Igualdade da forma geométrica: mesma descrição e mesma área. Causa erro se o parâmetro de comparação não for uma Forma.
     * @param obj A forma a ser comparada com esta. Causa erro se o parâmetro de comparação não for uma Forma.
     * @return boolean TRUE se ambas têm mesma descrição e área, FALSE caso contário.
     */
    @Override
    public boolean equals(Object obj){
        FormaGeometrica outra = (FormaGeometrica)obj;
        return ( this.descricao.equals(outra.descricao) &&
                 this.area() == outra.area()
                );
    }
    
}
