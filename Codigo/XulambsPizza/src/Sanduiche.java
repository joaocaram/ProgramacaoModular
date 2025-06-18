import java.text.NumberFormat;

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
