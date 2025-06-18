import java.util.List;

public class PromocaoFimDeSemana implements IPromocao {

	private static final double VALOR_MIN = 100;
	private static final double PCT_DESC = 0.08;

	public double valorItens(List<Comida> comidas) {
		double valor = comidas.stream()
							  .mapToDouble( c-> c.valorFinal())
							  .sum();
		if(valor >= VALOR_MIN)
			valor *= (1-PCT_DESC);
		return valor;
	}

}
