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

 /**
 * Classe Sanduiche: especialização da classe abstrata Comida
 */
public class Sanduiche extends Comida{
    
    //atributos
    private static final double VALOR_VEGANO = 5d;
    private static final double PRECO_BASE = 13.0;
    private boolean opcaoVegana;

    /**
     * inicializador privado
     * @param opcaoVegana V/F para sanduiche vegano
     */
    private void init(boolean opcaoVegana){
        this.precoBase = Sanduiche.PRECO_BASE;
        this.opcaoVegana = opcaoVegana;
    }
    
    /** 
    * Construtor padrão: sanduíche regular
     */
    public Sanduiche(){
        super("Sanduíche");
        this.init(false);
    }

    /**
     * Construtor para indicar opção vegana
     * @param opcaoVegana True/False para sanduíche vegano ou não
     */
    public Sanduiche(boolean opcaoVegana){
        super("Sanduíche");
        this.init(opcaoVegana);
    }


    @Override
    /**
     * Calcula o valor dos adicionais, aproveitando e estendendo o método-base (que tem valor dos ingredientes)
     * @return double Valor de todos os adicionais de um sanduíche
     */
    protected double precoAdicionais(){
        double aux = super.precoAdicionais();
        
        if(this.opcaoVegana)
            aux+=VALOR_VEGANO;

        return aux;
    }

    @Override
    /**
     * Aproveita a descrição da classe mãe e indica opção vegana borda recheada, se for o caso
     */
    public String toString(){
        String aux = super.toString();
        if(this.opcaoVegana)
            aux += ". Sanduíche vegano.";
        return aux;
    }
    
}
