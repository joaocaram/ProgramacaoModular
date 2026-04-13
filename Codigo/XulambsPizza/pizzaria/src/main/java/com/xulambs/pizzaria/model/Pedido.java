package com.xulambs.pizzaria.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
@Entity
public class Pedido {
	private LocalDate data;
	
	@OneToMany
	private List<Pizza> pizzas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	private boolean aberto;

    /**
	 * Cria um pedido com a data de hoje. Identificador é gerado automaticamente a partir
	 * do último identificador armazenado.
	 */

	public Pedido() {          
		aberto = true;
        pizzas = new LinkedList<>();
        data = LocalDate.now();
	}

	/**
	 * Retorna o número do pedido (getter) para efeitos de localização/comparação em outros contextos.
	 * Pode ser melhorado no futuro
	 * @return id do pedido (inteiro positivo)
	 */
	public int getNumero(){
		return idPedido;
	}

    /**
	 * Verifica se uma pizza pode ser adicionada ao pedido, ou seja, se o pedido
	 * está aberto.
	 * @return TRUE se puder adicionar, FALSE caso contrário
	 */
	private boolean podeAdicionar() {
		return aberto;
	}

    /**
	 * Adiciona uma pizza ao pedido, se for possível. Caso não seja, a operação é
	 * ignorada. Retorna a quantidade de pizzas do pedido após a execução.
	 * @param pizza Pizza a ser adicionada
	 * @return A quantidade de pizzas do pedido após a execução.
	 */
	public int adicionar(Pizza pizza) {
		if(podeAdicionar()){
            pizzas.addLast(pizza);
        }
        return pizzas.size();
	}

    /**
	 * Fecha um pedido, desde que ele contenha pelo menos 1 pizza. Caso esteja vazio,
	 * a operação é ignorada.
     * @return Boolean com o estado do pedido (FALSE para fechado, TRUE para aberto) 
	 */
	public boolean fecharPedido() {
		if(pizzas.size() > 0 )
            aberto = false;
        return aberto;
	}

    /**
	 * Calcula o preço a ser pago pelo pedido (no momento, a soma dos preços de todas as
	 * pizzas contidas no pedido)
	 * @return Double com o valor a ser pago pelo pedido (> 0)
	 */
	public double precoAPagar() {
		double preco = 0d;
        for (Pizza pizza : pizzas) {
            preco += pizza.valorAPagar();
        }

        // for (int i = 0; i < pizzas.size(); i++) {
        //     Pizza pizza = pizzas.get(i);
        //     preco += pizza.valorAPagar();        
        // }
        return preco;
	}

    /**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada pizza e o preço final a ser pago.
	 * @return String com os detalhes especificados.
     */
	public String relatorio() {
		String relat = String.format("Pedido nº %d - %s\n",
                            idPedido, data.toString());
        for (int i = 0; i < pizzas.size(); i++) {
            relat += String.format("%02d: %s\n", 
                            (i+1), pizzas.get(i).cupomDeVenda());
        }
        relat += String.format("TOTAL DO PEDIDO: R$ %.2f",
                         precoAPagar());
        return relat;
	}

}