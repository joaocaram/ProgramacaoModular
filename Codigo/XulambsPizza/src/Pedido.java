import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024-26 João Caram <caram@pucminas.br>
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

/** Classe Pedido: composição com classe Pizza. Um pedido pode conter diversas pizzas. Elas podem
 * ser adicionadas desde que o pedido esteja aberto. Um pedido tem um identificador único e armazena
 * sua data. Ele deve calcular o preço a ser pago por ele e emitir um relatório detalhando suas pizzas
 * e o valor a pagar.
 */
public abstract class Pedido implements Comparable<Pedido>{
    private static Random sorteio = new Random(42);
	private static int ultimoPedido;
	private LocalDate data;
	protected List<IProduto> itens;
	private int idPedido;
	private boolean aberto;

    /**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */

	public Pedido() {
        ultimoPedido += sorteio.nextInt(1, 6);
        
		aberto = true;
        itens = new LinkedList<>();
        data = LocalDate.now();
        idPedido = data.getDayOfMonth()*1000 + ultimoPedido;
	}

	protected double valorItens(){
		double preco = 0d;
        for (IProduto produto : itens) {
            preco += produto.valorAPagar();
        }
		return preco;
	}
	/**
	 * Retorna o número do pedido (getter) para efeitos de localização/comparação em outros contextos.
	 * Pode ser melhorado no futuro
	 * @return id do pedido (inteiro positivo)
	 */
	@Override
	public int hashCode(){
		return idPedido;
	}

    /**
	 * Verifica se uma pizza pode ser adicionada ao pedido, ou seja, se o pedido
	 * está aberto.
	 * @return TRUE se puder adicionar, FALSE caso contrário
	 */
	protected boolean podeAdicionar() {
		return aberto;
	}

    /**
	 * Adiciona uma pizza ao pedido, se for possível (ou seja, pedido aberto). 
	 * Retorna a quantidade de pizzas do pedido após a execução.
	 * Em caso de pedido fechado ou outra condição que impeça a adição,
	 * lança uma exceção de IllegalStateException. 
	 * @param item Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 * @throws IllegalStateException no caso do pedido estar fechado e não poder receber outras pizzas.
	 */
	public int adicionar(IProduto item) {
		if(item == null)
			throw new IllegalArgumentException("Pizza não foi criada");
		if(!podeAdicionar())
			throw new PedidoFechadoException(idPedido);
	
		itens.addLast(item);
		return itens.size();
	}

    /**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. Caso esteja vazio,
	 * a operação é ignorada.
     * @return Boolean com o estado do pedido (FALSE para fechado, TRUE para aberto) 
	 */
	public boolean fecharPedido() {
		if(itens.size() > 0 ){
            aberto = false;
			return aberto;
		}
		else
			throw new IllegalStateException("Pedido sem itens não pode ser fechado.");
        
	}

    /**
	 * Calcula o preço a ser pago pelo pedido (no momento, a soma dos preços de todas as
	 * pizzas contidas no pedido)
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */
	public abstract double precoAPagar();

	protected String cabecalhoPedido(){
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String dataFormatada = formatoData.format(data);
		String estado = aberto ? "aberto": "fechado";
		StringBuilder relat = new StringBuilder();
		relat.append(String.format("Pedido nº %d - %s - %s\n",
                            idPedido, dataFormatada, estado));
		Collections.sort(itens);
        for (int i = 0; i < itens.size(); i++) {
            relat.append(String.format("%02d: %s\n", 
                            (i+1), itens.get(i).toString()));
        }
		return relat.toString();
	}

	protected String rodapePedido(){
		return String.format("TOTAL DO PEDIDO: R$ %.2f",
                         precoAPagar());

	}

	@Override
	public int compareTo(Pedido outro){
		double diferenca = this.precoAPagar() - outro.precoAPagar();
		return diferenca > 0 ? 1 : -1;
	}
}