import java.util.LinkedList;
import java.util.List;

public class Frota {
	private List<Veiculo> veiculos;

	public Frota(){
		veiculos = new LinkedList<>();
	}

	public Veiculo localizar(String placa){
		Veiculo localizado = null;
		int codigo = placa.hashCode();
		for (int i = 0; i < veiculos.size() && localizado == null; i++) {
			Veiculo candidato = veiculos.get(i);
			if(candidato.hashCode() == codigo)
				localizado = candidato;
		}
		return localizado;
	}
	
	
	public double despesaVeiculos() {
		//TODO
		return 0;
	}

	public double despesaTotalVeiculos() {
		//TODO
		return 0;
	}

	public String veiculoMaiorDespesa() {
		//TODO
		return "";
	}

	public void addVeiculo(Veiculo v) {
		//TODO: lógica com exceções
		veiculos.add(v);
	}
}
