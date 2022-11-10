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
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Classe cliente - 1ª versão - para demonstração de polimorfismo paramétrico e coleções.
 * Um cliente tem uma lista de pedidos.
 * Implementa Comparable por código.
 */
public class Cliente implements Comparable<Cliente>{
    static LocalDate today = LocalDate.now();
    static Data hoje = new Data(today.getDayOfMonth(), today.getMonthValue());  

    private int cod;
    private static int ultimoCodigo=0;
    private String nome;
    private LinkedList<Pedido> pedidos;

    /**
     * Construtor sem validação. Cria lista de pedidos vazia
     * @param nome
     */
    public Cliente(String nome){
        this.nome = nome;
        this.cod = ++ultimoCodigo;
        this.pedidos = new LinkedList<>();
    }

    /**
     * Adiciona um pedido na lista.
     * @param novo
     */
    public void addPedido(Pedido novo){
        this.pedidos.add(novo);
    }

    /**
     * Retorna a lista de pedidos. A considerar: retornar um stream da lista, para impedir acesso direto.
     * @return Lista com pedidos do cliente
     */
    public LinkedList<Pedido> getPedidos(){
        return this.pedidos;
    }

    /**
     * Uso de stream para localizar o pedido de maior valor do Cliente
     * @return Pedido de maior valor 
     */
    public Pedido pedidoMaiorValor(){
        return this.pedidos.stream()
                           .max(Comparator.comparing(Pedido::valorTotal))
                           .get();
    }

    /**
     * Uso de stream para calcular o total gasto em pedidos pelo cliente
     * @return O valor total (double) gasto pelo cliente
     */
    public double totalEmPedidos(){
        return this.pedidos.stream()
                           .mapToDouble(p-> p.valorTotal())
                           .sum();
    }

    /**
     * Retorna a quantidade de pedidos recentes (<30 dias) do cliente
     * @return Quantidade de pedidos recentes (int)
     */
    public int quantPedidosRecentes(){
        return (int)this.pedidos.stream()
                            .filter(p -> p.getData().acrescentaDias(30).maisFutura(hoje))
                            .count();                             
    }

    /**
     * Para demonstração do uso de fila de prioridades. 
     * Uma fila de prioridades é criada com um comparador por parâmetro, depois é preenchida com uma cópia da fila original de pedidos
     *  @return O pedido com data mais recente (ou 'futura')
     */
    public Pedido pedidoRecente(){
        PriorityQueue<Pedido> pr = new PriorityQueue<>(new Comparator<Pedido>() {
            public int compare(Pedido p1, Pedido p2){
                if(p1.getData().maisFutura(p2.getData())){
                    return 1;
                }
                else return -1;
            }
            });

        pr.addAll(this.pedidos);
        return pr.element();
    }

    /**
     * Um relatório com todos os pedidos detalhados
     * @return String com o relatório
     */
    public String relatorioPedidos(){
        StringBuilder relat = new StringBuilder();
        for (Pedido pedido : pedidos) {
            relat.append(pedido+"\n");
        }
        return relat.toString();
    }

    /**
     * Nome do cliente
     * @return String com o nome
     */
    public String getNome(){
        return this.nome;
    }

    @Override 
    /**
     * Descrião simples do cliente: nome e código
     * @return Nome e código do cliente em uma string
     */
    public String toString(){
        return "Cliente nº"+String.format("%02d", this.cod)+": "+this.nome;
    }
    
    @Override
    /**
     * Verificando igualdade de clientes pela string do nome
     * @return True/false para clientes iguais ou não
     */
    public boolean equals(Object o){
        Cliente outro = (Cliente)o;
        return this.nome.equals(outro.nome);
    }

    @Override
    /**
     * Comparação por código de cliente
     * *ATENÇÃO*: comparação não consistente com equals para fins de demonstração. Não é exatamente uma boa prática
     */
    public int compareTo(Cliente o) {
        return this.cod - o.cod;
    }

}
