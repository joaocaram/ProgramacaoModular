import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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
public abstract class Pedido {

	//#region static/constantes
	/** Para gerar o id incremental automático */
	private static int ultimoPedido;
	//#endregion
	
	//#region atributos
	private LocalDate data;
	protected Comida[] comidas;
	private int idPedido;
	protected int quantComidas;
	protected EEstadoPedido estado;
	//#endregion

	protected abstract double valorTaxa();
	protected abstract boolean podeAdicionar();

	//#region construtores
	/**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */
	protected Pedido(int maxComidas) {
		idPedido = ++ultimoPedido;
        comidas = new Comida[maxComidas];
        quantComidas = 0;
        estado = EEstadoPedido.ABERTO;
		data = LocalDate.now();
	}
	//#endregion

	

	/**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param comida Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public final int adicionar(Comida comida) {
		if(podeAdicionar()){
			comidas[quantComidas] = comida;
			quantComidas++;
		}
		return quantComidas;
	}

	/**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. Caso esteja vazio,
	 * a operação é ignorada.
	 */
	public void fecharPedido() {
		if(quantComidas>0)
			estado = EEstadoPedido.FECHADO;
	}


	protected double valorItens(){
		double precoItens =0d;
        for (int i=0; i<quantComidas; i++) {
            precoItens += comidas[i].valorFinal();
        }
        return precoItens;
	}
	/**
	 * Calcula o preço a ser pago pelo pedido (no momento, a soma dos preços de todas as
	 * pizzas contidas no pedido)
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */

	public double precoAPagar() {
		return valorItens() + valorTaxa();
	}

	/**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada pizza e o preço final a ser pago.
	 * @return String com os detalhes especificados:. 
	 * <br/><pre>
	 * PEDIDO - NÚMERO - DD/MM/AAAA
	 * 01 - DESCRICAO DA PIZZA
	 * 02 - DESCRICAO DA PIZZA
	 * 03 - DESCRICAO DA PIZZA
	 * 
	 * TOTAL A PAGAR: R$ VALOR
	 * </pre>
	 */
	@Override
	public String toString() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido ");
        relat.append(String.format("%02d %s - %s\n", idPedido, estado.toString(), formatter.format(data)));
        relat.append("=============================");
        
        for (int i=0; i<quantComidas; i++) {
            relat.append(String.format("\n%02d - %s\n", (i+1), comidas[i].notaDeCompra()));            
        }
		relat.append(String.format("\nTAXA: %s\n", moeda.format(valorTaxa())));
        relat.append(String.format("TOTAL A PAGAR: %s\n", moeda.format(precoAPagar())));
        relat.append("=============================");
        return relat.toString();
	}

	@Override
	public int hashCode() {
		return idPedido + data.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Pedido outro = (Pedido) obj;
		return (idPedido == outro.idPedido && data.equals(outro.data));
	}

}
