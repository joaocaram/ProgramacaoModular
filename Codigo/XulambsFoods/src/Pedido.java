import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
 * da comida
 */
public class Pedido {
    private static Random sorteio = new Random(42); // somente para geração de aleatórios
    private static LocalDate hoje = LocalDate.now(); // registrando o dia de hoje

    private int MAX_COMIDAS = 10;
    private int idPedido;
    private LocalDate dataPedido;
    private Comida[] itens;
    private boolean aberto;
    private int quantComidas;

    /**
     * Construtor que cria um pedido já incluindo sua primeira comida.
     * @param primeira Comida inicial do pedido.
     */
    public Pedido(Comida primeira) {
        init(primeira);
    }

    /**
     * Construtor simples: pedido vazio.
     */
    public Pedido() {
        init(null);
    }

    /**
     * Inicializador: cria o pedido e insere a primeira comida, se não for nula.
     * @param primeira Primeira comida para abrir o pedido, ou null para pedido
     *                 vazio
     */
    private void init(Comida primeira) {
        idPedido = sorteio.nextInt(Integer.MAX_VALUE % 100_000);
        dataPedido = hoje;
        quantComidas = 0;
        aberto = true;
        itens = new Comida[MAX_COMIDAS];
        if (primeira != null)
            addComida(primeira);

    }

    /**
     * Adiciona uma comida ao pedido, caso seja possível. Retorna a quantidade de comidas no pedido após a operação.
     * @param novaComida Comida a ser adicionada
     * @return A quantidade de comidas no pedido após a tentativa de inserção.
     */
    public int addComida(Comida novaComida) {
        if (novaComida != null && podeAdicionarComida()) {
            itens[quantComidas] = novaComida;
            quantComidas++;
        }
        return quantComidas;
    }

    /**
     * Verificação para adicionar um novo item no pedido: ele precisa estar aberto e
     * a quantidade de
     * comidas não pode ter atingido o máximo
     * @return TRUE se é possível adicionar uma comida, FALSE caso contrário.
     */
    private boolean podeAdicionarComida() {
        return (aberto && quantComidas < MAX_COMIDAS);
    }

    /**
     * Valor total do pedido (ou seja, a soma dos valores das comidas)
     * @return Double com o valor total do pedido
     */
    public double precoFinal() {
        double valor = 0d;
        for (int i = 0; i < quantComidas; i++) {
            Comida atual = itens[i];
            valor += atual.precoFinal();
        }
        return valor;
    }

    /**
     * Relatório do pedido. Inclui seu número, sua data e os detalhes de cada comida (descrição e preço).
     * Mostra o valor final a pagar.
     * @return String com detalhamento do pedido.
     */
    public String relatorio() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/uuuu");
        StringBuilder relat = new StringBuilder("");

        relat.append("=====================\n");
        relat.append("Pedido nº " + this.idPedido + " - " + this.dataPedido.format(df) + "\n");
        for (int i = 0; i < quantComidas; i++) {
            relat.append(String.format("%02d - %s\n", (i + 1), itens[i].relatorio()));
        }
        relat.append("\n  TOTAL DO PEDIDO:\t R$ " + String.format("%.2f", this.precoFinal()) + "\n");
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

}
