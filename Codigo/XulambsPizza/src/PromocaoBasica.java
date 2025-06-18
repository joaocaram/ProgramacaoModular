import java.util.List;

public class PromocaoBasica implements IPromocao {

	private static final int COMIDAS_DESCONTO = 5;
	private List<Comida> listaComidas;

    private double comidaMaisBarata() {
		double valorMaisBarato = 0;
        if(listaComidas.size() >= COMIDAS_DESCONTO)
			valorMaisBarato =  listaComidas.stream()
			   						  .min((c1, c2) -> c1.valorFinal() > c2.valorFinal()? 1:-1)
			   						  .map(comida -> comida.valorFinal())
			   						  .get();
		return valorMaisBarato;
	}

	@Override
	public double valorItens(List<Comida> comidas) {
		listaComidas = comidas;
		 return comidas.stream()		
		 		.mapToDouble(c -> c.valorFinal())
				.sum() - comidaMaisBarata();
	}    

}