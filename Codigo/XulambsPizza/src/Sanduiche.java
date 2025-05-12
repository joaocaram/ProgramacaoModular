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
public class Sanduiche extends Comida {
    private static final int MAX_INGREDIENTES;
    private static final String DESCRICAO;
	private static final double PRECO_BASE;
	private static final double VALOR_ADICIONAL;
	private static final double VALOR_COMBO;

	private boolean comboFritas;
	
	static{
		MAX_INGREDIENTES = 5;
		PRECO_BASE = 15d;
		VALOR_ADICIONAL = 3d;
        VALOR_COMBO = 5d;
        DESCRICAO = "Sanduiche";
	}
	
	private void init(int adicionais, boolean combo){
		adicionarIngredientes(adicionais);
		comboFritas = combo;
	}

	public Sanduiche() {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(0, false);
	}

	public Sanduiche(int quantosAdicionais) {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(quantosAdicionais, false);
	}

    public Sanduiche(boolean combo) {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(0, combo);
	}

    public Sanduiche(int quantosAdicionais, boolean combo) {
		super(DESCRICAO, MAX_INGREDIENTES, PRECO_BASE, VALOR_ADICIONAL);
		init(quantosAdicionais, combo);
	}

    @Override
	public double valorFinal() {
		double valor =  PRECO_BASE + valorAdicionais();
        if(comboFritas)
            valor += VALOR_COMBO;
        return valor;
	}

	@Override
	public String toString() {
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
		StringBuilder notinha = new StringBuilder(super.toString()+"\n");
		if(comboFritas)
        	notinha.append(String.format("   Combo      : %s\n", moeda.format(VALOR_COMBO)));
		notinha.append(String.format("   Adicionais : %s\n", moeda.format(valorAdicionais())));
		notinha.append(String.format("VALOR A PAGAR : %s", moeda.format(valorFinal())));
		return notinha.toString();
	}
}
