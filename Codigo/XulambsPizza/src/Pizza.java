import java.text.NumberFormat;

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

public class Pizza extends Comida{

	private static final int MAX_INGREDIENTES;
	private static final String DESCRICAO;
	private static final double PRECO_BASE;
	private static final double VALOR_ADICIONAL;
	private static final int ADIC_SEM_DESCONTO;
	private static final double PCT_DESCONTO;

	private EBorda bordaRecheada;

	static{
		MAX_INGREDIENTES = 8;
		DESCRICAO = "Pizza";
		PRECO_BASE = 29d;
		VALOR_ADICIONAL = 5d;
		ADIC_SEM_DESCONTO = 5;
		PCT_DESCONTO = 0.5;
	}
	
	private void init(int adicionais, EBorda borda){
		adicionarIngredientes(adicionais);
		bordaRecheada = borda;
	}

	public Pizza() {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(0, EBorda.SEM_BORDA);
	}

	public Pizza(int adicionais) {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(adicionais, EBorda.SEM_BORDA);
	}

	public Pizza(int adicionais, EBorda borda) {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(adicionais, borda);
	}

	@Override
	public double valorFinal() {
		return PRECO_BASE + valorAdicionais() + bordaRecheada.valor() - descontoAdicionais();
	}

	private double descontoAdicionais(){
		double desconto = 0d;
		int quantosComDesconto = quantidadeIngredientes - ADIC_SEM_DESCONTO;
		if(quantosComDesconto>0)
			desconto = quantosComDesconto * VALOR_ADICIONAL * PCT_DESCONTO;
		return desconto;
	}

	public double adicionarBorda(EBorda borda){
		if(borda == null)
			borda = EBorda.SEM_BORDA;
		bordaRecheada = borda;
		return bordaRecheada.valor();

	}

	@Override
	public String toString() {
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
		StringBuilder notinha = new StringBuilder(super.toString()+"\n");
		notinha.append(String.format("   Borda      : %s\n", bordaRecheada.descricao()));
		notinha.append(String.format("   Adicionais : %s\n", moeda.format(valorAdicionais())));
		notinha.append(String.format("   Desconto   : %s\n", moeda.format(descontoAdicionais())));
		notinha.append(String.format("VALOR A PAGAR : %s", moeda.format(valorFinal())));
		return notinha.toString();
	}

}
