public enum EDistancia {
    CURTA(4,0),
    MEDIA(8,5),
    LONGA(Double.MAX_VALUE,8);

    private double distanciaMax;
    private double valorTaxa;

    private EDistancia(double distancia, double taxa){
        distanciaMax = distancia;
        valorTaxa = taxa;
    }

    public double valorTaxa(){
        return valorTaxa;
    }

    public static EDistancia definirDistancia(double distancia){
        EDistancia[] distancias = EDistancia.values();
        int pos = 0;
		while ((distancia > distancias[pos].distanciaMax)){
			pos++;
		}
		return distancias[pos];
    }

}
