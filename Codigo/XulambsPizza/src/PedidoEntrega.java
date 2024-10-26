public class PedidoEntrega extends Pedido {
    private static final int MAX_PIZZAS = 6;
    private static final double DISTANCIAS[] = {4,8,Double.MAX_VALUE};
    private static final double TAXAS[] = {0,5,8};

    private double distanciaEntrega;

    public PedidoEntrega(double distancia){
        super(MAX_PIZZAS);
        if(distancia < 0.1)
            distancia = 0.1;
        distanciaEntrega = distancia;
    }

    protected boolean podeAdicionar(){
        return(aberto && quantComidas < MAX_PIZZAS);
    }

    protected double valorTaxa(){
        double taxa = 0d;
        for(int i = (DISTANCIAS.length-1); i>=0; i--) {
            if (distanciaEntrega <= DISTANCIAS[i])
                taxa = TAXAS[i];
        }
        return taxa;

    }
}
