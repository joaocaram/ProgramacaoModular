
public class FlyweightSanduiche implements IFlyweightComida {

    private static final String DESCRICAO = "Sanduíche";
    private static final int MAX_ADICIONAIS = 5;
	private static final double VALOR_ADICIONAIS = 2.50;
	private static final double PRECO_BASE = 15;

	public double precoBase() {
        return PRECO_BASE;
	}

	public double valorAdicional() {
		return VALOR_ADICIONAIS;
	}

	public int maxAdicionais() {
		return MAX_ADICIONAIS;
	}

	public String descricao() {
		return DESCRICAO;
	}
}
