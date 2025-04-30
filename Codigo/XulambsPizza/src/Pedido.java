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

public abstract class Pedido {
    //#region static/constantes
	/** Para controlar o vetor de Pizzas */
    private static final int MAX_COMIDAS = 100;
	
	/** Para gerar o id incremental automático */
	private static int ultimoPedido = 0;
    //#endregion
	
	//#region atributos
	private int idPedido;
	private LocalDate data;
	private Comida[] comidas;
	private boolean aberto;
	protected int quantComidas;
	//#endregion

	
	//#region construtores
	private void init(int maxComidas){
		if(maxComidas < 1 || maxComidas > MAX_COMIDAS)
			maxComidas = MAX_COMIDAS;
		idPedido = ++ultimoPedido;
        comidas = new Comida[maxComidas];
        quantComidas = 0;
		data = LocalDate.now();
        aberto = true;
	}
	
	protected Pedido(int maxComidas){
		init(maxComidas);
	}
	//#endregion

    /**
     * Verifica se pode adicionar um novo item ao pedido (no caso, se o pedido estiver aberto e
     * protegendo o tamanho do vetor)
     * @return TRUE / FALSE conforme seja possível ou não adicionar o novo item.
     */
	protected boolean podeAdicionar(){
        return aberto;
    }

	protected final double valorItens(){
		double valor = 0d;
		for (int i = 0; i < quantComidas; i++) {
			valor += comidas[i].valorFinal();
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
        relat.append("=============================\n");
        
        for (int i=0; i<quantComidas; i++) {
            relat.append(String.format("%02d - %s\n\n", (i+1), comidas[i].toString()));            
        }
		return relat.toString();
	}

	/**
	 * Calcula o preço a ser pago pelo pedido 
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */
	public abstract double precoAPagar();

	/**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param comida Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public int adicionar(Comida comida) {
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
			aberto = false;
	}

	//#region Herança Object
	@Override
	public boolean equals(Object obj){
		Pedido outro = (Pedido)obj;

		return (this.idPedido == outro.idPedido &&
				this.data.equals(outro.data));
	}

	/**
	 * Retorna o hashcode de um pedido (seu identificador + inteiro representando a data).
	 * @return código único do pedido (inteiro positivo)
	 */
	@Override
	public int hashCode(){
		return (int)(idPedido + data.toEpochDay());
	}

	
	//#endregion
}
