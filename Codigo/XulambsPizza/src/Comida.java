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
public abstract class Comida {
    
    private int maxIngredientes;
    protected int quantidadeIngredientes;
    private double precoBase;
    private double valorAdicionais;
    private String descricao;

    protected Comida(String desc, int max, double base, double valorAdc){
        descricao = desc;
        maxIngredientes = max;
        precoBase = base;
        valorAdicionais = valorAdc;
    }

    private boolean podeAdicionar(int quantos) {
		int total = quantos + quantidadeIngredientes; 
		return ( total >= 0 && total <= maxIngredientes);
	}

    protected double valorAdicionais() {
		return quantidadeIngredientes * valorAdicionais;
	}

    public int adicionarIngredientes(int quantos){
		if(!podeAdicionar(quantos)){
           throw new IngredientesEmExcessoException(quantos+quantidadeIngredientes);
        }
        quantidadeIngredientes += quantos;
        return quantidadeIngredientes;
	}

	public int retirarIngredientes(int quantos){
		quantos *=-1;
        return adicionarIngredientes(quantos);
	}

    public abstract double valorFinal();

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		String notinha = String.format("%s com %d ingredientes\n", descricao, quantidadeIngredientes);
		notinha+= (String.format("   Valor base : %s", moeda.format(precoBase)));
        return notinha;

    }
    
}
