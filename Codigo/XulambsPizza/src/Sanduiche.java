import java.text.NumberFormat;

public class Sanduiche extends Comida {

	private static final int MAX_INGREDIENTES = 5;
	private static final String DESCRICAO = "Sanduíche";
	private static final double PRECO_BASE = 15d;
	private static final double VALOR_ADICIONAL = 3d;;
	private static final double VALOR_COMBO = 5d;
	private boolean comboFritas;

    private void init(boolean combo, int quantidadeIngredientes){
        comboFritas = combo;
        adicionarIngredientes(quantidadeIngredientes);

    }
	public Sanduiche() {
		super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
        init(false, 0);
	}

	public Sanduiche(int quantosAdicionais) {
        super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
		init(false, quantosAdicionais);
	}

	public Sanduiche(boolean combo) {
		super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
		init(combo, 0);
	}

	public Sanduiche(int quantosAdicionais, boolean combo) {
		super(DESCRICAO,MAX_INGREDIENTES,PRECO_BASE,VALOR_ADICIONAL);
		init(combo, quantosAdicionais);
	}

    @Override
	public String notaDeCompra() {
	NumberFormat moeda = NumberFormat.getCurrencyInstance();
        String nota = super.notaDeCompra();
        if(comboFritas)
            nota += String.format("\n\tCombo com fritas: %s", moeda.format(VALOR_COMBO));
        nota += String.format("\nVALOR A PAGAR: %s", moeda.format(valorFinal()));
        return nota;	
	}

    @Override
	public double valorFinal() {
        double valor = PRECO_BASE + valorAdicionais();
		valor += (comboFritas)? VALOR_COMBO : 0;
        return valor;
	}

}

