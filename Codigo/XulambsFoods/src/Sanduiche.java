import java.text.NumberFormat;

public class Sanduiche implements IComida {

	private static final EComida base = EComida.SANDUICHE;
	private static final double VALOR_COMBO = 5d;
	private boolean comboFritas;
	private int quantAdicionais;

    private void init(boolean combo, int quantidadeIngredientes){
		comboFritas = combo;
		quantAdicionais = 0;
        adicionarIngredientes(quantidadeIngredientes);

    }
	public Sanduiche() {
        init(false, 0);
	}

	public Sanduiche(int quantosAdicionais) {
   		init(false, quantosAdicionais);
	}

	public Sanduiche(boolean combo) {
		init(combo, 0);
	}

	public Sanduiche(int quantosAdicionais, boolean combo) {
		init(combo, quantosAdicionais);
	}

    @Override
	public String notaDeCompra() {
	NumberFormat moeda = NumberFormat.getCurrencyInstance();
	StringBuilder nota = new StringBuilder();
	nota.append(String.format("%s (%s) com %d ingredientes (%s)", 
								base.getDescricao(), moeda.format(base.getPrecoBase()), 
								quantAdicionais, moeda.format(valorAdicionais())));

        if(comboFritas)
            nota.append(String.format("\n\tCombo com fritas: %s", moeda.format(VALOR_COMBO)));
        nota.append(String.format("\nVALOR A PAGAR: %s", moeda.format(valorFinal())));
        return nota.toString();	
	}

    @Override
	public double valorFinal() {
        double valor = base.getPrecoBase() + valorAdicionais();
		valor += (comboFritas)? VALOR_COMBO : 0;
        return valor;
	}
	@Override
	public int adicionarIngredientes(int quantos) {
		if ((quantAdicionais + quantos) < base.getMaxIngredientes())
			quantAdicionais += quantos;
		return quantAdicionais;
	}
	@Override
	public double valorAdicionais() {
		return quantAdicionais * base.getValorAdicional();
	}

}

