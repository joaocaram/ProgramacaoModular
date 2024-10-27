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

    @Override
    protected boolean podeAdicionar(){
        return(estado.equals(EEstadoPedido.ABERTO) && quantComidas < MAX_PIZZAS);
    }

    @Override
    public void fecharPedido(){
        if(quantComidas>0)
			estado = EEstadoPedido.EM_ENTREGA;
    }

    public void entregarPedido(){
        if(estado.equals(EEstadoPedido.EM_ENTREGA))
            estado = EEstadoPedido.FECHADO;
    }

    @Override
    protected double valorTaxa(){
        int pos = 0;
		while ((distanciaEntrega > DISTANCIAS[pos])){
			pos++;
		}
		return TAXAS[pos];
	}
}
