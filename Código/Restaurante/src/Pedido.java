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


 
 /** Versão do pedido para a pizzaria: agregação com pizza */
public class Pedido {
    /**
     * Constante para o máximo de pizzas no pedido.
     */
    public static final int MAX_COMIDAS = 10;
    private static int ultimoId=0;
    private int idPedido;
    private Data dataPedido;
    private Comida[] comidas;
    private int quantComidas;
    private boolean encerrado;
    
    /**
     * Método inicializador. Cria o vetor de pizzas e o id automático do pedido.
     * @param dataPedido Data do pedido a ser criado.
     */
    private void init(Data dataPedido){
        this.idPedido = ++ultimoId;
        this.dataPedido = dataPedido; 
        this.comidas = new Pizza[MAX_COMIDAS];
        this.quantComidas = 0;
        this.encerrado = false;
    }

    /**
     * Construtor: recebe a data do pedido em formato String. O id do Pedido é gerado automaticamente.
     * @param dataPedido String no formato DD/MM/AAAA. 
     */
    public Pedido(String dataPedido){
        init(new Data(dataPedido));
    }

    /**
     * Construtor: recebe a data do pedido como objeto Data. O id do Pedido é gerado automaticamente.
     * @param dataPedido Data previamente criada, com a data do pedido.
     */
    public Pedido(Data dataPedido){
        init(dataPedido);
    }

    /**
     * Método privado para verificar as condições de limite de pizzas.
     * @return TRUE se o limite não foi atingido, FALSE caso contrário.
     */
    private boolean verificarTamanho(){
        return (this.quantComidas<MAX_COMIDAS);
    }

    /**
     * Adiciona uma pizza ao pedido, se o limite não foi atingido.
     * O tamanho máximo atual é de 10 pizzas por pedido.
     * @param novaPizza Pizza a ser inserida no pedido. Precisa vir já criada.
     * @return TRUE/FALSE conforme tenha sido adicionada no pedido.
     */
    public boolean addPizza(Pizza novaPizza){
        boolean resposta = false;
        if(verificarTamanho() && !encerrado){
            this.comidas[this.quantComidas] = novaPizza;
            this.quantComidas++;
            resposta = true;
        }
        return resposta;
    }

    /**
     * Adiciona uma comida ao pedido, se o limite não foi atingido.
     * O tamanho máximo atual é de 10 comidas por pedido.
     * @param nova Comida a ser inserida no pedido. Precisa vir já criada.
     * @return TRUE/FALSE conforme tenha sido adicionada no pedido.
     */
    public boolean addComida(Comida nova){
        boolean resposta = false;
        if(verificarTamanho() && !encerrado){
            this.comidas[this.quantComidas] = nova;
            this.quantComidas++;
            resposta = true;
        }
        return resposta;
    }

    /**
     * Calcula o preço do pedido (a soma dos preços de suas pizzas).
     * @return Valor do preço atual do pedido.
     */
    public double calcularPreco(){
        double valor=0d;
        for (int i = 0; i < this.quantComidas; i++) {
            valor+= this.comidas[i].calcularPreco();
        }
        return valor;
    }

    /**
     * Fecha o pedido com as pizzas atuais. Depois do fechamento,
     * não é possível incluir pizzas. Também não é possível fechar
     * o pedido sem nenhuma pizza.
     * @return TRUE/FALSE conforme foi possível fechar o pedido.
     */
    public boolean encerrar(){
        boolean resposta = false;
        if(this.quantComidas>0){
            resposta = true;
            for (int i = 0; i < this.quantComidas; i++) {
                this.comidas[i].fecharVenda();
            }
            this.encerrado = true;
        }
        return resposta;
    }

    public String criarNota(){
        return this.toString();
        
    }
    /**
     * Cria a nota do pedido, contendo detalhes e preços de cada pizza e o preço final do pedido.
     * @return String contendo detalhes e preços de cada pizza e o preço final do pedido.
     */
    public String toString(){
       StringBuilder relatorioPedido = new StringBuilder("Pedido nº "+this.idPedido+"\n");
       relatorioPedido.append(this.dataPedido.dataFormatada());
       
       for (int i = 0; i < this.quantComidas; i++) {
            relatorioPedido.append("\n"+this.comidas[i].toString());
       }
       relatorioPedido.append("\n\n TOTAL DO PEDIDO: R$ "+this.calcularPreco());
       return relatorioPedido.toString();
    }

}
