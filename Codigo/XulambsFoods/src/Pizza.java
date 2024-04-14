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
  * Pizza: especialização de comida (herança e polimorfismo de inclusão)
  */
public class Pizza extends Comida {

    //#region constantes
    private static final int MAX_ADICIONAIS = 8;
    private static final double VALOR_ADICIONAIS = 4.0;
    private static final double PRECO_BASE = 29;
    private static final double VALOR_BORDA = 5.5;
    private static final int ADICIONAIS_SEM_DESCONTO = 5;
    private static final double DESCONTO_ADICIONAIS = 0.5;
    //#endregion

    //#region atributos
    private boolean temBordaRecheada;
    //#endregion

    //#region construtores

    /**
     * Método inicializador para pizza, com quantidade de adicionais e opção de borda recheada. Ajusta a descrião e o preço base caso tenha borda recheada.
     * @param qtdAdicionais Quantidade de adicionais (valores inválidos revertem para 0)
     * @param borda Boolean indicado se há borda recheada ou não.
     */
    private void init(int qtdAdicionais, boolean borda){
        descricao = "Pizza";
        precoBasico = PRECO_BASE;
        valorPorAdicional = VALOR_ADICIONAIS;
        maxAdicionais = MAX_ADICIONAIS;
        setBorda(borda);
        adicionarIngredientes(qtdAdicionais);
    }

    /**
     * Cria uma pizza sem adicionais nem borda recheada.
     */
    public Pizza(){
        init(0, false);
    }

    /**
     * Cria uma pizza com adicionais e sem borda recheada. Os adicionais são válidados e valores inválidos revertem para 0. 
     * @param qtdAdicionais Quantidade de adicionais da pizza ( > 0)
     */
    public Pizza(int qtdAdicionais){
        init(qtdAdicionais, false);
    }

    /**
     * Cria uma pizza com adicionais e com opção de borda recheada. Os adicionais são válidados e valores inválidos revertem para 0. A borda deve ser indicada no segundo parâmetro.
     * @param qtdAdicionais Quantidade de adicionais da pizza ( > 0)
     * @param bordaRecheada Boolean indicando se há ou não borda recheada
     */
    public Pizza(int qtdAdicionais, boolean bordaRecheada){
        init(qtdAdicionais, bordaRecheada);
    }
    //#endregion

    //#region métodos próprios

    /**
     * Calcula o valor dos adicionais da pizza, incluindo o possível desconto.
     *  @return Double com o valor dos adicionais (valor não negativo)
     */    
    @Override
    protected double valorAdicionais(){
        
        return super.valorAdicionais() - descontoAdicionais();
    }

    /**
     * Calcula o valor do desconto concedido aos adicionais. A partir do 6º ingrediente é concedido 50% de desconto para cada ingrediente.
     * @return Valor do desconto a ser abatido do valor dos adicionais ( >=0 )
     */
    private double descontoAdicionais(){
        double valorDesconto = 0d;
        int quantidadeComDesconto = qtdAdicionais - ADICIONAIS_SEM_DESCONTO;
        if(quantidadeComDesconto > 0){
            valorDesconto = quantidadeComDesconto*VALOR_ADICIONAIS*DESCONTO_ADICIONAIS;
        }
        return valorDesconto;
    }
    
    /**
     * Permite escolher ou retirar a borda recheada da Pizza. Descrição e valores são atualizados internamente.
     * @param recheada Boolean representando se a pizza terá ou não borda recheada.
     * @return Boolean indicando se a pizza está ou não com borda recheada.
     */
    public boolean escolherBorda(boolean recheada){
        setBorda(temBordaRecheada);
        return temBordaRecheada;
    }

    /**
     * Realiza as operações de configuração da borda recheada (preço e descrição).
     * @param borda Boolean indicando se a pizza terá ou não a borda recheada.
     */
    private void setBorda(boolean borda){
        temBordaRecheada = borda;
        if(temBordaRecheada){
            descricao = "Pizza com borda recheada";
            precoBasico = PRECO_BASE + VALOR_BORDA;
        }
        else{
            descricao = "Pizza";
            precoBasico = PRECO_BASE;
        }
    }
    //#endregion
}

