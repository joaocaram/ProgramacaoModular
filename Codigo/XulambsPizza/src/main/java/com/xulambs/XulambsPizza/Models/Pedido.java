package com.xulambs.XulambsPizza.Models;

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
import java.util.List;

import com.xulambs.XulambsPizza.DTO.PedidoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/** Classe Pedido: composição com classe Pizza. Um pedido pode conter diversas pizzas. Elas podem
 * ser adicionadas desde que o pedido esteja aberto. Um pedido tem um identificador único e armazena
 * sua data. Ele deve calcular o preço a ser pago por ele e emitir um relatório detalhando suas pizzas
 * e o valor a pagar.
 */
@Entity
@Table(name = "TB_PEDIDOS")
public class Pedido {

	//#region static/constantes
	/** Para criar um vetor de pizzas de tamanho grande */
	private static final int MAX_PIZZAS = 100;
	//#endregion
	
	//#region atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	
	@OneToMany
	private List<Pizza> pizzas;

	private LocalDate data;
	private int quantPizzas;
	private boolean aberto;
	//#endregion

	//#region construtores
	/**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */
	public Pedido() {
		pizzas = new LinkedList<>();
        quantPizzas = 0;
        aberto = true;
		data = LocalDate.now();
	}
	//#endregion

	public PedidoDTO generateDTO(){
		return new PedidoDTO(idPedido, quantPizzas, precoAPagar(), relatorio());
	}
	/**
	 * Verifica se uma pizza pode ser adicionada ao pedido, ou seja, se o pedido
	 * está aberto e há espaço na memória.
	 * @return TRUE se puder adicionar, FALSE caso contrário
	 */
	private boolean podeAdicionar() {
		return aberto && quantPizzas < MAX_PIZZAS;
	}

	/**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param pizza Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public int adicionar(Pizza pizza) {
		if(podeAdicionar()){
			//pizzas[quantPizzas] = pizza;
			pizzas.add(pizza);
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
		double precoFinal =0d;
        for (int i=0; i<quantPizzas; i++) {
            precoFinal += pizzas.get(i).valorFinal();
        }
        return precoFinal;
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
	public String relatorio() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido ");
        relat.append(String.format("%02d - %s\n", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        relat.append("=============================\n");
        
        for (int i=0; i<quantPizzas; i++) {
            relat.append(String.format("%02d - %s\n", (i+1), pizzas.get(i).notaDeCompra()));            
        }
        relat.append(String.format("\nTOTAL A PAGAR: %s\n", moeda.format(precoAPagar())));
        relat.append("=============================");
        return relat.toString();
	}

	

}
