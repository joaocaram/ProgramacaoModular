import java.text.NumberFormat;
import java.time.LocalDate;
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
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


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
	protected LinkedList<IProduto> itens;
	private int idPedido;
	protected double desconto;
	private boolean aberto;
	//#endregion

	//#region construtores
	/**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */
	public Pedido() {
		idPedido = ++ultimoPedido;
        itens = new LinkedList<>();
        aberto = true;
		data = LocalDate.now();
	}
	//#endregion

	/**
	 * Verifica se uma pizza pode ser adicionada ao pedido, ou seja, se o pedido
	 * está aberto e há espaço na memória.
	 * @return TRUE se puder adicionar, FALSE caso contrário
	 */
	protected boolean podeAdicionar() {
		return aberto;
	}


	protected double valorItens(){
		double precoFinal =0d;
		for (IProduto trem : itens) {		//forEach
			precoFinal += trem.valorFinal();
		}
		return precoFinal;
    
	}

	public double aplicarDesconto(double valorDesconto){
		if(desconto!=0)
			throw new IllegalStateException("Desconto já aplicado");
		if(valorDesconto < 0 )
			throw new IllegalArgumentException("Desconto deve ser positivo");
		desconto = valorDesconto;
		return desconto;
	}

	/**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param prod Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public int adicionar(IProduto prod) {
		if(!podeAdicionar()){
            throw new IllegalStateException("Pedido já está fechado ou lotado.");
        }
		itens.add(prod);
        return itens.size();
	}

	/**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. 
	 * Caso esteja vazio, será lançada exceção de estado ilegal.
	 * @exception IllegalStateException Caso o pedido não tenha nenhuma pizza ainda.
	 */
	public void fecharPedido() {
		if(itens.size()==0)
			throw new IllegalStateException("Pedido vazio não pode ser fechado");
		aberto = false;
	}

	/**
	 * Calcula o preço a ser pago pelo pedido (no momento, a soma dos preços de todas as
	 * pizzas contidas no pedido)
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */
	public abstract double precoAPagar();
	

	protected String detalhesPedido(){
		StringBuilder relat = new StringBuilder();
		relat.append(String.format("%02d - %s\n", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        relat.append("=============================\n");
        
        for (int i=0; i<itens.size(); i++) {
            relat.append(String.format("%02d - %s\n", (i+1), itens.get(i).toString()));            
        }
		return relat.toString();
	}

	protected String rodapePedido(){
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
		StringBuilder relat = new StringBuilder();
		relat.append(String.format("DESCONTO DO CLIENTE: %s\n", moeda.format(desconto)));
		relat.append(String.format("TOTAL A PAGAR: %s\n", moeda.format(precoAPagar())));
        relat.append("=============================");
		return relat.toString();

	}
	

}
