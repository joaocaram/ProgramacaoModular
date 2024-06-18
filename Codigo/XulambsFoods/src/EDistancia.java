public enum EDistancia {
    CURTA(5,0),
    MEDIA(10,5),
    LONGA(12,8),
    ENORME(Double.MAX_VALUE, 13);

    private double distanciaMax;
    private double valorTaxa;

    private EDistancia(double dist, double valor){
        distanciaMax = dist;
        valorTaxa = valor;
    }

    public double valorTaxa(){
        return valorTaxa;
    }

    static public EDistancia definirDistancia(double distancia){
        EDistancia[] valores = EDistancia.values();
        int i=0;
        while ((distancia > valores[i].distanciaMax)) {
            i++;
        }
        return valores[i];
    }

    // CURTA(5,0),
    // MEDIA(10,5),
    // LONGA(12,8),
    // ENORME(Double.MAX_VALUE, 13);

}
