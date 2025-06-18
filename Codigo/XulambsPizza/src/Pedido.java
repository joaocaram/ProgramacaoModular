import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.naming.OperationNotSupportedException;

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

public abstract class Pedido implements Comparable<Pedido> {
    static LocalDateTime dataBase = LocalDateTime.of(2025, 02, 01,12,0,0);
	static Random sorteio = new Random(42);
	
	//#region static/constantes
	/** Para controlar o vetor de Pizzas */
    
	
	/** Para gerar o id incremental automático */
	private static int ultimoPedido = 0;
    //#endregion
	
	//#region atributos
	private int idPedido;
	private LocalDate data;
	protected List<Comida> comidas;
	private boolean aberto;
	private IPromocao promoAtiva; 
	private double desconto;
	
	//#endregion

	
	//#region construtores
	private void init(LocalDate dataPedido, IPromocao promo){
	
		promoAtiva = promo;
		if(promoAtiva == null)
			promoAtiva = new PromocaoBasica();
		
		idPedido = ++ultimoPedido;
		
		if (data != null)
    		data = dataPedido;
		else {
    		dataBase = dataBase.plusMinutes(sorteio.nextInt(38)+12);
    		if (dataBase.getHour() < 12)
        		dataBase.plusHours(13 - dataBase.getHour());

    		data = LocalDate.from(dataBase);
		}            
		comidas = new LinkedList<Comida>();
		desconto = 0d;
		aberto = true;
	}
	
	protected Pedido(){
		init(null, null);
	}

	protected Pedido(LocalDate dataPedido, IPromocao promo){
		init(dataPedido, promo);
	}
	//#endregion

	@Override
    public int compareTo(Pedido other){
        double dif = this.precoAPagar() - other.precoAPagar();
        int resultado = 0;
        if(dif < 0)
            resultado = -1;
        else if(dif > 0)
                resultado = 1;
        return resultado;
    }

    /**
     * Verifica se pode adicionar um novo item ao pedido (no caso, se o pedido estiver aberto e
     * protegendo o tamanho do vetor)
     * @return TRUE / FALSE conforme seja possível ou não adicionar o novo item.
     */
	protected boolean podeAdicionar(){
        return aberto;
	}
	protected final double valorItens(){
		double valorPromo = promoAtiva.valorItens(comidas);
		return valorPromo - desconto;
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
        int posicao = 1;
		
		for (Comida comida : comidas) {
            relat.append(String.format("%02d - %s\n\n", (posicao++), comida));            
        }
									  
		return relat.toString();
	}

	protected String notaDesconto(){
	    NumberFormat moeda = NumberFormat.getCurrencyInstance();
	
		return String.format("DESCONTO: %s", moeda.format(desconto));
	}

	/**
	 * Calcula o preço a ser pago pelo pedido 
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */
	public abstract double precoAPagar();

	/**
	 * Adiciona uma comida ao pedido, se for possível. 
	 * Caso não seja, será lançada uma exceção. 
	 * Retorna a quantidade de pizzas do pedido após a execução.
	 * @param comida Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 * @throws OperationNotSupportedException caso o pedido esteja fechado.
	 * @throws NullPointerException caso a comida adicionada seja nula.
	 */
	public int adicionar(Comida comida) throws OperationNotSupportedException{
		if(!podeAdicionar()){ 
			throw new OperationNotSupportedException("Não posso adicionar comida em pedido fechado");
		}
		if(comida == null){
			throw new NullPointerException("Comida não foi criada");
		}
		comidas.add(comida);
		
		return comidas.size();
	}

	/**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. Caso esteja vazio,
	 * a operação é ignorada.
	 */
	public void fecharPedido() {
		if(comidas.size()==0)
			throw new IllegalStateException("Pedido sem comidas não pode ser fechado");
		aberto = false;
	}
	public double aplicarDesconto(double valor){
		if(valor < 0 || valor > valorItens() )
			throw new InvalidParameterException("Valor inválido para o desconto");
		if(desconto != 0)
			throw new IllegalStateException("Pedido já tem desconto");
		desconto = valor;
		return desconto;
	}

	public LocalDate data(){
		return data;
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
		return (idPedido);
	}

	
	//#endregion
}
