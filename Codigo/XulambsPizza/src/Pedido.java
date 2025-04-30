import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MIT License
 *
 * Copyright(c) 2022-25 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
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

public class Pedido {
    //#region static/constantes
	/** Para gerar o id incremental automático */
	private static int ultimoPedido = 0;
	
    /** Para controlar o vetor de Pizzas */
    private static final int MAX_PIZZAS = 100;
    //#endregion
	
	//#region atributos
	private int idPedido;
	private LocalDate data;
	private Pizza[] pizzas;
	protected int quantPizzas;	
    private boolean aberto;
	//#endregion

	
	//#region construtores

	private void init(int maxPizzas){
		if(maxPizzas < 1)
			maxPizzas = 1;
		idPedido = ++ultimoPedido;
        pizzas = new Pizza[maxPizzas];
        quantPizzas = 0;
		data = LocalDate.now();
        aberto = true;
	}
	
	
	protected Pedido(int maxPizzas){
		init(maxPizzas);
	}

	/**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */
	public Pedido() {
		init(MAX_PIZZAS);	
	}


	//#endregion

	/**
	 * Retorna o identificador de um pedido, para fins de localização.
	 * Pode ser feito de maneira melhor - veremos adiante.
	 * @return ID do pedido (inteiro positivo)
	 */
	public int getID(){
		return idPedido;
	}
    /**
     * Verifica se pode adicionar um novo item ao pedido (no caso, se o pedido estiver aberto e
     * protegendo o tamanho do vetor)
     * @return TRUE / FALSE conforme seja possível ou não adicionar o novo item.
     */
	protected boolean podeAdicionar(){
        return aberto;
    }

	/**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param comida Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public int adicionar(Pizza pizza) {
		if(podeAdicionar()){
			pizzas[quantPizzas] = pizza;
			quantPizzas++;
		}
		return quantPizzas;
	}

	/**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. Caso esteja vazio,
	 * a operação é ignorada.
	 */
	public void fecharPedido() {
		if(quantPizzas>0)
			aberto = false;
	}

	/**
	 * Calcula o preço a ser pago pelo pedido (no momento, a soma dos preços de todas as
	 * pizzas contidas no pedido)
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */

	public double precoAPagar() {
        double valor = 0d;
        for (int i = 0; i < quantPizzas; i++) {
            valor += pizzas[i].valorFinal();
        }
		return valor;
	}

	protected String detalhesPedido(){
		StringBuilder relat = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String estado = "FECHADO\n";

		relat.append(String.format("%02d - %s - ", idPedido, formatter.format(data)));
		
        if(aberto)
			estado = "ABERTO\n";
        
		relat.append(estado);
        relat.append("=============================");
        
        for (int i=0; i<quantPizzas; i++) {
            relat.append(String.format("\n%02d - %s", (i+1), pizzas[i].toString()));            
        }
		return relat.toString();
	}
	/**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada pizza e o preço final a ser pago.
	 * @return String com os detalhes especificados:. 
	 * <p>
     * <pre>
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
		
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido Local");
        relat.append("\n"+detalhesPedido());
        relat.append(String.format("\n\nTOTAL A PAGAR: %s", moeda.format(precoAPagar())));
        relat.append("\n=============================");
        return relat.toString();
	}

	@Override
	public boolean equals(Object obj){
		Pedido outro = (Pedido)obj;

		return (this.idPedido == outro.idPedido &&
				this.data.equals(outro.data));
	}
}
