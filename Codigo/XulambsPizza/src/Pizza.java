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

public class Pizza {

	private static final int MAX_INGREDIENTES;
	private static final double PRECO_BASE;
	private static final double VALOR_POR_ADICIONAL;
	private int quantidadeIngredientes;
	private EBorda bordaRecheada;

	static{
		MAX_INGREDIENTES = 8;
		PRECO_BASE = 29d;
		VALOR_POR_ADICIONAL = 5d;
	}
	
	private void init(int adicionais, EBorda borda){
		adicionarIngredientes(adicionais);
		bordaRecheada = borda;
	}

	public Pizza() {
		init(0, EBorda.SEM_BORDA);
	}

	public Pizza(int adicionais) {
		init(adicionais, EBorda.SEM_BORDA);
	}

	public Pizza(int adicionais, EBorda borda) {
		init(adicionais, borda);
	}

	private double valorAdicionais() {
		return quantidadeIngredientes * VALOR_POR_ADICIONAL;
	}

	private boolean podeAdicionar(int quantos) {
		int total = quantos + quantidadeIngredientes; 
		return ( total >= 0 && total <= MAX_INGREDIENTES);
	}

	public double valorFinal() {
		return PRECO_BASE + valorAdicionais() + bordaRecheada.valor();
	}

	public int adicionarIngredientes(int quantos) {
		if(podeAdicionar(quantos)){
            quantidadeIngredientes += quantos;
        }
        return quantidadeIngredientes;
	}

	public int retirarIngredientes(int quantos) {
		quantos *=-1;
        return adicionarIngredientes(quantos);
	}

	public double adicionarBorda(EBorda borda){
		if(borda == null)
			borda = EBorda.SEM_BORDA;
		bordaRecheada = borda;
		return bordaRecheada.valor();

	}

	@Override
	public String toString() {
		return String.format("Pizza (R$ %.2f) com %d ingredientes (R$ %.2f) e %s. Valor total: R$ %.2f",
                                    PRECO_BASE, quantidadeIngredientes, valorAdicionais(), bordaRecheada.descricao(), valorFinal());
	}

}
