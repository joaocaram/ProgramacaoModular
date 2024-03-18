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
  * Classe Comida simples: atributos, métodos, encapsulamento, construtores.
  */
 public class Comida{

    //#region Atributos
    private String descricao;
    private double precoBasico;
    private double valorPorAdicional;
    private int maxAdicionais;
    private int qtdAdicionais;
    //#endregion

    //#region Construtores

    /**
     * Inicializador privado. Recebe a descrição da comida e a quantidade de adicionais. A descrição pode ser 'Pizza' ou 'Sanduiche',
     * sem distinção de maiúsculas ou minúsculas. Os adicionais serão validados de acordo com as regras das comidas. Em caso de descrição inválida,
     * cria uma pizza. Adicionais inválidos se tornam 0.
     * @param descricao Tipo da comida: "Pizza" ou "Sanduiche", sem distinção de maiúsculas/minúsculas.
     * @param qtdAdicionais Quantos adicionais (>0 e respeitando regra das comidas.)
     */
    private void init(String descricao, int qtdAdicionais){
        switch (descricao.toLowerCase()) {
            case "sanduiche":
                    this.descricao = "Sanduiche";
                    precoBasico = 15;
                    valorPorAdicional = 2.50;
                    maxAdicionais = 5;
                break;
            case "pizza":
            default:
                    this.descricao = "Pizza";
                    precoBasico = 29;
                    valorPorAdicional = 5.0;
                    maxAdicionais = 8;
                break;
        }
        adicionarIngredientes(qtdAdicionais);
    }
    
    /**
     * Cria uma comida do tipo especificado. Recebe a descrição da comida ('Pizza' ou 'Sanduiche') e cria com 0 adicionais. 
     * Em caso de descrição inválida, cria uma pizza. 
     * @param descricao Tipo da comida: "Pizza" ou "Sanduiche", sem distinção de maiúsculas/minúsculas.
     */
    public Comida(String descricao){
        init(descricao, 0);
    }

    /**
     * Construtor. Recebe a descrição da comida e a quantidade de adicionais. A descrição pode ser 'Pizza' ou 'Sanduiche',
     * sem distinção de maiúsculas ou minúsculas. Os adicionais serão validados de acordo com as regras das comidas. Em caso de descrição inválida,
     * cria uma pizza. Adicionais inválidos se tornam 0.
     * @param descricao Tipo da comida: "Pizza" ou "Sanduiche", sem distinção de maiúsculas/minúsculas.
     * @param qtdAdicionais Quantos adicionais (>0 e respeitando regra das comidas.)
     */
    public Comida(String descricao, int qtdAdicionais){
        init(descricao, qtdAdicionais);
    }

    /**
     * Cria uma pizza com 0 adicionais.
     */
    public Comida(){
        init("pizza", 0);
    }
    //#endregion

    //#region Métodos de negócio
    
    /**
     * Retorna o preço final a pagar pela comida. 
     * @return O preço a pagar pela comida (double não negativo)
     */
    public double precoFinal(){
        return precoBasico + valorAdicionais();
    }

    /**
     * Método privado que calcula o valor dos ingredientes adicionais.
     * @return O valor dos adicionais da comida (double não negativo).
     */
    private  double valorAdicionais(){
        return qtdAdicionais * valorPorAdicional;
    }

    /**
     * Tenta adicionar ingredientes na comida. Há validação da regra de máximo de ingredientes de acordo com o tipo.
     * @param quantos Quantos ingredientes serão adicionados à comida (>0).
     * @return Quantos ingredientes a comida terá depois da tentativa. (inteiro não negativo)
     */
    public int adicionarIngredientes(int quantos){
        if(podeAdicionarIngredientes(quantos)){
            qtdAdicionais += quantos;
        }
        return qtdAdicionais;
    }

    /**
     * Verifica a possibilidade de adicionar novos ingredientes. A quantidade adicionada deve ser positiva e não ultrapassar
     * o total estabelecido para cada comida.
     * @param quantos Quantidade de ingredientes a ser adicionados.
     * @return TRUE se é possível adicional, FALSE caso contrário
     */
    private boolean podeAdicionarIngredientes(int quantos){
        return ((quantos>0) &&
                (qtdAdicionais + quantos <= maxAdicionais));

    }

    /**
     * Cria um relatório simples da comida em String. 
     * @return String com o relatório conforme a o requisito: descrição da comida, preço básico, quantidade de adicionais e preço a pagar.
     */
    public String relatorio(){
        return String.format("%s (R$ %2.2f) com %d adicionais (R$ %.2f)\n   TOTAL: R$ %.2f",
                             descricao, precoBasico, qtdAdicionais, valorAdicionais(), precoFinal());
    }

    //#endregion
}
