
public class FlyweightPizza  implements IFlyweightComida{

    private static final String DESCRICAO = "Pizza";
    private static final int MAX_ADICIONAIS = 8;
	private static final double VALOR_ADICIONAIS = 4;
	private static final double PRECO_BASE = 29;

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
