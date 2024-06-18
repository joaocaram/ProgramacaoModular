import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
 * Classe pedido: composição com "comida" e delegação para valores e descrição
 * da comida. Classe abstrata para herança/polimorfismo em PedidoLocal e PedidoDelivery
 */
public abstract class Pedido {
    
    //#region atributos de classe
    private static int ultPedido = 0;     
    private static LocalDate hoje = LocalDate.now(); // registrando o dia de hoje
    //#endregion

    //#region atributos
    private int idPedido;
    private LocalDate dataPedido;
    private String descricao;
    protected Comida[] itens;
    protected boolean aberto;
    protected int quantComidas;
    //#endregion

    //#region construtores
    /**
     * Construtor que cria um pedido já incluindo sua primeira comida.
     * @param primeira Comida inicial do pedido.
     */
    protected Pedido(String descricao, Comida primeira) {
        init(descricao, primeira);
    }

    /**
     * Construtor simples: pedido vazio.
     */
    protected Pedido(String descricao) {
        init(descricao,null);
    }

    /**
     * Inicializador: cria o pedido e insere a primeira comida, se não for nula.
     * @param primeira Primeira comida para abrir o pedido, ou null para pedido
     *                 vazio
     */
    private void init(String descricao, Comida primeira) {
        this.descricao = descricao;
        idPedido = ++ultPedido;
        dataPedido = hoje;
        quantComidas = 0;
        aberto = true;
        itens = new Comida[1_000];  // valor absurdo enquanto não usamos coleções
        if (primeira != null)
            addComida(primeira);

    }
    //#endregion

    //#region métodos de negócio
    /**
     * Método para adicionar comida (abstrato). As regras de adicionar comida estão nas classes filhas.
     * @param novComida Comida a ser adicionada (não pode ser nula)
     * @return Quantidade de comidas após a tentativa de adicionar.
     */
    public abstract int addComida(Comida novComida);

    /**
     * Valor total do pedido (ou seja, a soma dos valores das comidas)
     * @return Double com o valor total do pedido
     */
    public double precoFinal() {
        return valorItens() + taxa();
    }

    protected double valorItens(){
        double valor = 0d;
        for (int i = 0; i < quantComidas; i++) {
            Comida atual = itens[i];
            valor += atual.precoFinal();
        }
        return valor;
    }
    /**
     * Calcula a taxa devida do pedido, de acordo com seu tipo. 
     * @return Valor da taxa (double não negativo), de acordo com seu tipo.
     */
    public abstract double taxa();

    /**
     * Relatório do pedido. Inclui seu número, sua data e os detalhes de cada comida (descrição e preço).
     * Mostra o valor final a pagar.
     * @return String com detalhamento do pedido.
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/uuuu");
        StringBuilder relat = new StringBuilder("");

        relat.append("=====================\n");
        relat.append(descricao+" nº " + this.idPedido + " - " + this.dataPedido.format(df) + "\n");
        for (int i = 0; i < quantComidas; i++) {
            relat.append(String.format("%02d - %s\n", (i + 1), itens[i].relatorio()));
        }
        relat.append("\nTOTAL DOS ITENS:\t R$ " + String.format("%6.2f", this.valorItens()) + "\n");
        relat.append("TAXA:\t\t\t R$ " + String.format("%6.2f", this.taxa()) + "\n");
        relat.append("TOTAL DO PEDIDO:\t R$ " + String.format("%6.2f", this.precoFinal()) + "\n");
        relat.append("=====================");
        return relat.toString();
    }

    /**
     * Fecha o pedido, desde que ele tenha pelo menos 1 comida adicionada.
     */
    public void fecharPedido() {
        if (quantComidas > 0)
            aberto = false;
    }
    //#endregion

}
