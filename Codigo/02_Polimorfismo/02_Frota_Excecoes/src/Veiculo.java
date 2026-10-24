
public class Veiculo {
	private String placa;
	private String modelo;
	private Tanque tanque;
	private int kmRodados;
	private double valorVenda;

	public Veiculo(String placa, int ano, double valor) {
		//TODO: lógica com exceções
		tanque = new Tanque(50);
	}

	public double registarKMRodados(double km) {
		//TODO: lógica com exceções
		return 0;
	}

	public double abastecer(double litros) {
		double resp = 0;
		try {
			resp = tanque.abastecer(litros);
		} catch (IllegalStateException tanqueCheio) {
			String msg = "Veículo placa "+placa+":";
			msg += tanqueCheio.getMessage();
			throw new IllegalStateException(msg, tanqueCheio);
		}	
		return resp;

	}
}
