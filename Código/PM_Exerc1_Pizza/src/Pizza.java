import java.math.BigDecimal;

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
 
 /**
  * Classe Pizza: POO básica
  * Usando: encapsulamento, construtores, static
  * Pode ser bem melhorada:  refatorações.
  */
public class Pizza {
    
    //#region constantes

    public static final int MAX_ADICIONAIS_PIZZA = 8;
    private static final BigDecimal PRECO_ADICIONAIS_PIZZA = new BigDecimal(4.0);
    private static final BigDecimal PRECO_BASE_PIZZA = new BigDecimal(25.0);
    private static final String DESCRICAO_PIZZA = "Pizza com queijo e calabresa ";
    //#endregion

    //#region atributos
    private int qtdAdicionais;  
    //#endregion 
    
    //#region construtores

    /**
     * Inicializador: só cria pizzas com adicionais válidos
     */
    private void init(int quantosAdicionais){
        if(this.validarIngredientes(quantosAdicionais))
            this.qtdAdicionais = quantosAdicionais;
        else
            this.qtdAdicionais=0;
    }

    /**
     * Construtor padrão: pizza sem adicionais
     */
    public Pizza(){
        init(0);
    }

    /**
     * Cria uma pizza com adicionais. Caso sejam mais que o máximo, fica com 0 adicionais.
     * @param quantosAdicionais Quantidade de adicionais a incluir.
     */
    public Pizza(int quantosAdicionais){
        init(quantosAdicionais);
    }

    //#endregion
    
    //#region métodos de negócio

    /**
     * Calcula o preço final: base + adicionais * preço dos adicionais. Método públic apesar de 
     * não haver requisito explícito pedindo o preço.
     * @return Preço final a pagar pela pizza
     */
    public BigDecimal precoFinal(){       
        return (PRECO_BASE_PIZZA.add(valorIngredientes()));
    }

    /**
     * Método privado para isolar a regra de valor dos adicionais da pizza.
     * @return O valor dos adicionais
     */
    private BigDecimal valorIngredientes(){
        BigDecimal quantAuxiliar = new BigDecimal(this.qtdAdicionais);
        return quantAuxiliar.multiply(PRECO_ADICIONAIS_PIZZA);
    }
    
    /**
     * Método interno para validação da quantidade de ingredientes adicionais. Verifica se a quantidade
     * de ingredientes atuais somada/subtraída com os novos continua válida.
     * @param quantosAdicionais A quantidade a acrescentar/subtrair nos ingredientes atuais.
     * @return TRUE se continuar válido; FALSE caso contrário.
     */
    private boolean validarIngredientes(int quantosAdicionais){
        boolean resposta = false;
        int quantos = this.qtdAdicionais + quantosAdicionais;
        if(quantos>=0 && quantos<=MAX_ADICIONAIS_PIZZA)
            resposta = true;
        return resposta;

    }

    /**
     * Tenta adicionar ingredientes à pizza. Só será executado se o total não exceder o limite, pois há validação interna.
     * @param quantosAdicionais Quantidade de ingredientes a adicionar.
     */
    public void adicionarIngrediente(int quantosAdicionais){
        if(quantosAdicionais>0 && validarIngredientes(quantosAdicionais))
                this.qtdAdicionais += quantosAdicionais;
    }

    /**
     * Tenta retirar ingredientes da pízza. Só será executado se o total não for negativo, pois há validação interna.
     * @param quantosAdicionais Quantidade de ingredientes a retirar.
     */
    public void retirarIngrediente(int quantosAdicionais){
        if(quantosAdicionais<0 && validarIngredientes(-quantosAdicionais))
                this.qtdAdicionais -= quantosAdicionais;
    }
   
    /**
     * Retorna a nota de compra da pizza. Formato:
     * <descricao> com <qtdAdicionais> adicionais - R$ <precoFinal> 
     * @return String no formato indicado.
     */
    public String imprimirNotaDeCompra(){
        StringBuilder aux = new StringBuilder(DESCRICAO_PIZZA + ": R$ "+PRECO_BASE_PIZZA+"\n");
        aux.append("\t com "+this.qtdAdicionais+" adicionais + R$ "+this.valorIngredientes()+"\n");
        aux.append("PREÇO A PAGAR: R$ " +this.precoFinal());
        return aux.toString();
    }
  
    //#endregion
    
}
