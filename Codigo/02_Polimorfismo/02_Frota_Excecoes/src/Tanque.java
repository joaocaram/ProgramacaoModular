
public class Tanque {
	double capacidade;
	ECombustivel combustivel;
	double combustivelRestante;
	double totalAbastecido;

	public Tanque(double capacidade) {
		//TODO: lógica com exceções
		this.capacidade = capacidade; 
	}

	public double autonomia() {
		//TODO
		return  0;
	}

	public double percorrer(double km) {
		//TODO: lógica com exceções
		return  0;
	}

	public double abastecer(double litros) {
		if(litros <= 0 )
			throw new IllegalArgumentException("Litros devem ser positivos");
		if(litros + combustivelRestante > capacidade)
			throw new IllegalStateException("Tanque não tem esta capacidade");

		combustivelRestante += litros;
		return  combustivelRestante;
	}
}
