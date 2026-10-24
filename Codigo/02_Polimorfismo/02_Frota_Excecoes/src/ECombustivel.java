public enum ECombustivel {
	GASOLINA(10),
	ETANOL(7);

	double consumo;

	ECombustivel(double consumoMedio) {
		consumo = consumoMedio;
	}

	public double consumo() {
		return consumo;
	}

}