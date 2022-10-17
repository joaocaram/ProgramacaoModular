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
public class Pizza extends Comida implements IPersonalizavel{

    //constantes
    private static final int MAX_INGRED = 8;
    private static final double VALOR_BASICO = 25.0;
    private static final double PRECO_INGRED = 2.0;
    private static final double VALOR_BORDA = 7.5;
    private static final String DESCRICAO = "Pizza simples";
    
    private boolean bordaRecheada;
    
   
/**
     * Validação do máximo de adicionais. Protegido para venda fechada e valores negativos. Não adiciona os adicionais, apenas valida a quantidade.
     * @param quantos Quantidade de adicionais para validar.
     * @return TRUE se válido, FALSE se inválido.
     */
    public boolean validarAdicionais(int quantos){
        if (!this.vendaFechada && quantos>0 && quantos<=this.maximoIngredientes)
            return true;
        else
            return false;
    }
     /**
     * Adiciona ingredientes extras na pizza, além dos já incluídos. Método validado para valores negativos ou acima do limite (8 adicionais). Caso inválido, ignora a operação.
     * @param quantos Quantos ingredientes a adicionar aos que já existem na pizza. 
     * @return TRUE se operação com sucesso, FALSE caso contrário.
     */
    public boolean addIngredientes(Ingrediente adicional){
        boolean resposta = false;
        if(validarAdicionais(this.qtIngredientes+1)){
            this.ingredientes[this.qtIngredientes] = adicional;
            this.qtIngredientes++;
            resposta = true;
        }
        return resposta;
    }

      /**
     * Adiciona ingredientes extras na pizza, além dos já incluídos. Método validado para valores negativos ou acima do limite (8 adicionais). Caso inválido, ignora a operação.
     * @param quantos Quantos ingredientes a adicionar aos que já existem na pizza. 
     * @return TRUE se operação com sucesso, FALSE caso contrário.
     */
    public boolean delIngredientes(String ingrediente){
        boolean resposta = false;
        for (int i = 0; i < this.qtIngredientes; i++) {
            if(this.ingredientes[i].toString().toLowerCase().equals(ingrediente.toLowerCase())){
                this.ingredientes[i] = this.ingredientes[this.qtIngredientes-1];
                resposta = true;
                this.qtIngredientes--;
                break;
            }
        }
        return resposta;
    }

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
    
    @Override
    public String toString(){
        StringBuilder nota = new StringBuilder(this.descricao);
        nota.append(" com "+this.qtIngredientes+
        " adicionais. Preço inicial: R$"+String.format("%.2f", this.valorBasico)+".\n");
        for (int i = 0; i < qtIngredientes; i++) {
            nota.append("\t"+this.ingredientes[i]+"\tR$"+String.format("%.2f", this.ingredientes[i].preco()*PRECO_INGRED)+"\n");
        }
        nota.append("\tValor final: R$"+String.format("%.2f", this.calcularPreco())+".\n");
        return nota.toString();
    }
   
}