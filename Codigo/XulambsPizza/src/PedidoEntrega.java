import java.text.NumberFormat;

public class PedidoEntrega extends Pedido{

    private static final int MAX_PIZZAS = 8;
    private static final double[] TAXAS =      {0, 5, 8};
    private static final double[] DISTANCIAS = {4, 8, Double.MAX_VALUE};
    
    private double distanciaEntrega;

    public PedidoEntrega(double distancia){
        super();
        if(distancia < 0.1)
            throw new DistanciaInvalidaException(distancia);
        
        distanciaEntrega = distancia;
    }

    private double valorTaxa(){
        double taxa = 0d;
        int pos = 0;
		while(distanciaEntrega > DISTANCIAS[pos])
			pos++;
		taxa = TAXAS[pos];
        return taxa;
    }

    @Override
    protected boolean podeAdicionar(){
        return super.podeAdicionar()
               && (pizzas.size() < MAX_PIZZAS);
    }

    @Override
    public double precoAPagar(){
        return valorItens() + valorTaxa();
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido Entrega ");
        relat.append(detalhesPedido());
        relat.append(String.format("\nTAXA DE ENTREGA: %s", moeda.format(valorTaxa())));
        relat.append("\n"+rodapePedido());
        return relat.toString();
    }
}
