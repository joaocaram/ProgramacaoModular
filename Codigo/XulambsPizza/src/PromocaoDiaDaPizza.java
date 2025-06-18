import java.util.List;

public class PromocaoDiaDaPizza implements IPromocao {

	private static final int MIN_PIZZAS = 3;

	@Override
	public double valorItens(List<Comida> comidas) {
		long contPizza = comidas.stream()
							   .filter(c -> c.toString().toLowerCase().contains("pizza"))
							   .count();
		double valor = comidas.stream()
							  .mapToDouble(c -> c.valorFinal())
							  .sum();
		if(contPizza >= MIN_PIZZAS){
			valor -= comidas.stream()
							   .filter(c -> c.toString().toLowerCase().contains("pizza"))
							   .mapToDouble(c -> c.valorFinal())
							   .average()
							   .getAsDouble();
		} 
		return valor;
	}

}
