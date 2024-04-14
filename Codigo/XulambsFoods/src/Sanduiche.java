/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
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
  * Classe sanduíche: especialização de comida (com herança e polimorfismo de inclusão)
  */
public class Sanduiche extends Comida {
    
    //#region constantes
    private static final int MAX_ADICIONAIS = 5;
    private static final double VALOR_ADICIONAIS = 2.5;
    private static final double PRECO_BASE = 15;
    //#endregion   

    //#region construtores

    /**
     * Inicializador privado. Cria um sanduíche com adicionais, fazendo a validação e revertendo para 0 em caso de valor inválido.
     * @param qtdAdicionais Quantos adicionais o sanduíche terá (>=0)
     */
    private void init(int qtdAdicionais){
        descricao = "Sanduíche";
        precoBasico = PRECO_BASE;
        valorPorAdicional = VALOR_ADICIONAIS;
        maxAdicionais = MAX_ADICIONAIS;
        
        adicionarIngredientes(qtdAdicionais);
    }

    /**
     * Cria um sanduíche sem adicionais
     */
    public Sanduiche(){
        init(0);
    }

    /**
     * Cria um sanduíche com adicionais. O valor é validado e em caso de valor inválido é revertido para 0.
     * @param qtdAdicionais Quantos adicionais o sanduíche terá (>0)
     */
    public Sanduiche(int qtdAdicionais){
        init(qtdAdicionais);
    }
    //#endregion
    
}
