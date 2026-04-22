public class PedidoEntrega extends Pedido {
    private static final int MAX_PIZZAS = 8;
    private static final double[] TAXAS = {0,5,8};
    private static final double[] DISTANCIAS = {4,8,Double.MAX_VALUE};
    private double distanciaEntrega;

    public PedidoEntrega(double distancia){
        super();
        if(distancia <= 0 )
            distancia = 0.1;
        distanciaEntrega = distancia;
    }

    private double valorTaxa(){
        int pos = 0;
        while (distanciaEntrega > DISTANCIAS[pos]) {
            pos++;
        }
        return TAXAS[pos];
    }

    @Override
    protected boolean podeAdicionar(){
        return (pizzas.size() < MAX_PIZZAS && super.podeAdicionar());
    }

    @Override
    public double precoAPagar(){
        return super.precoAPagar() + valorTaxa();
    }
}
