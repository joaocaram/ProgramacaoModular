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
  * Cliente do restaurante. Contém vetor de pedidos (a ser melhorado futuramente)
  * Inclui programa de fidelidade. (Injeção de comportamento por interface)
  */
public class Cliente{
    private static final int MAX_PEDIDOS = 100;
    private static int ultimoCodigo = 0;
    private Pedido[] pedidos;
    private int qtPedidos;
    private int codigo;
    private String nome;
    private IFidelizavel fidelidade;

    /**
     * Construtor para o cliente. Recebe o nome. O código é gerado automaticamente.
     * Cria um vetor com 100 posições para os pedidos.
     * @param nome Nome do cliente (sem validação)
     */
    public Cliente(String nome){
        this.nome = nome;
        this.codigo = ++ultimoCodigo;
        this.pedidos = new Pedido[MAX_PEDIDOS];
        this.qtPedidos = 0;
        this.fidelidade = null;
    }

    /**
     *  Adiciona um pedido ao cliente, dentro do limite permitido. Retorna falso em caso de insucesso.
     * @param novo O pedido a ser adicionado
     * @return TRUE se adicionado, FALSE caso contrário
     */
    public boolean addPedido(Pedido novo){
        boolean resposta = false;
        if(this.qtPedidos<MAX_PEDIDOS){
            resposta = true;
            this.pedidos[this.qtPedidos++] = novo; 
        }
        return resposta;
    }

    /**
     * Relatório com todos os pedidos do cliente. Nome, código e nota de venda de cada pedido.
     * @return String contendo o relatório. 
     */
    public String relatorioPedidos(){
        StringBuilder relat = new StringBuilder("Cliente nº "+this.codigo+" - "+this.nome+"\n");

        for (int i = 0; i < this.qtPedidos; i++) {
            relat.append("\n"+pedidos[i]+"\n----------");    
        }
        return relat.toString();
    }

    /**
     * Retorna o pedido mais caro. Para isso, ordena o pedido por valor (a ser melhorado no futuro com coleções)
     * @return O pedido mais caro do cliente, ou null se não houver pedido.
     */
    public Pedido maisCaro(){
        if(this.qtPedidos>0){
            Util.quicksort(this.pedidos, 0, this.qtPedidos-1);
            return this.pedidos[this.qtPedidos-1];
        }
        return null;
    }

    /**
     * Retorna o pedido mais recente do cliente. Usa método do pedido para verificação de datas.
     * @return O pedido mais recente (null se não houver pedido)
     */    
    public Pedido maisRecente(){
       Pedido recente = this.pedidos[0];
       for (int i = 0; i < this.qtPedidos; i++) {
            if(this.pedidos[i].maisRecente(recente))
                recente = pedidos[i];
       }
       return recente;
    }

    /**
     * Nome do cliente
     * @return String com nome do cliente
     */
    public String nome(){
        return this.nome;
    }

   @Override
   /**
    * Código do cliente - atribuído automaticamente e sequencialmente no construtor.
    * @return Código do cliente 
    */
    public int hashCode(){
        return this.codigo;
    }

    @Override
    /**
     * Igualdade do cliente: nome
     * @return TRUE se dois clientes tem o mesmo nome
     */
    public boolean equals(Object obj){
        Cliente outro = (Cliente)obj;
        return this.nome.equals(outro.nome);
    }
   
    /**
     * Valor total em pedidos do cliente. (a ser melhorado no futuro)
     * @return O valor total em pedidos feitos pelo cliente. 
     */
    private double totalEmPedidos(){
        double total=0;
        for (int i = 0; i < this.qtPedidos; i++) {
            total+= pedidos[i].calcularPreco();
        }
        return  total;
    }

    /**
     * Dados do cliente: número, nome, total de pedidos e valor total gasto
     */
    @Override
    public String toString(){
        StringBuilder cliente = new StringBuilder(String.format("%2d", this.codigo)+" - "+ this.nome+"\n");
        cliente.append("Total de pedidos: "+this.qtPedidos+"\n");
        cliente.append("Total gasto: R$"+ String.format("%.2f", this.totalEmPedidos()));
        return cliente.toString();
    }

   
}