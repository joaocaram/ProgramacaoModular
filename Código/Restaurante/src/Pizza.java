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

/** Classe pizza simples para demonstração de fundamentos de POO e teste unitário */
public class Pizza extends Comida{

    //constantes
    private static final int MAX_INGRED = 8;
    private static final double VALOR_BASICO = 25.0;
    private static final double PRECO_INGRED = 2.0;
    private static final double VALOR_BORDA = 7.5;
    private static final String DESCRICAO = "Pizza simples";
    
    private boolean bordaRecheada;
    
   

    private void init(boolean bordaRecheada){
        this.incluirBorda(bordaRecheada);
    }
       
    /**
     * Construtor simples: cria uma pizza sem adicionais.
     */
    public Pizza(){
        super(MAX_INGRED, VALOR_BASICO, DESCRICAO);
        init(false);
    }

    public Pizza(boolean bordaRecheada){
        super(MAX_INGRED, VALOR_BASICO, DESCRICAO);
        init(bordaRecheada);
    }
    @Override
    protected double valorAdicionais() {
        double valor=0d;
        for (int i = 0; i < this.qtIngredientes; i++) {
            valor+= this.ingredientes[i].preco()*PRECO_INGRED;
        }
        if(this.bordaRecheada)
            valor+= VALOR_BORDA;
        return valor;
    }

    public boolean incluirBorda(boolean inclusao){
        this.bordaRecheada = inclusao;
        return this.bordaRecheada;
    }
    
   
}