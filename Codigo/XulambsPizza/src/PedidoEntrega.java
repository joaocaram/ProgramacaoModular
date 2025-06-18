import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.time.LocalDate;

public class PedidoEntrega extends Pedido{
    private static final int MAX_COMIDAS = 8;
    private static final double[] DISTANCIAS =  {4d,8d,Double.MAX_VALUE};
    private static final double[] TAXAS =       {0d,5d,8d};
    private double distanciaEntrega;

    private void init(double distancia){
        if(distancia <=0 )
            throw new InvalidParameterException("Distância deve ser maior que 0.");
        
        distanciaEntrega = distancia;
    }

    public PedidoEntrega(double distancia, LocalDate data, IPromocao promo){
        super(data, promo);
        init(distancia);
    }
    public PedidoEntrega(double distancia) {
        super(null, null);
        init(distancia);
    }

    private double valorTaxa(){
        int pos = 0;
        while(distanciaEntrega > DISTANCIAS[pos])
            pos++;
        return TAXAS[pos];
    }

    @Override
    protected boolean podeAdicionar(){
        return super.podeAdicionar() 
                    && comidas.size() < MAX_COMIDAS;
    }


    @Override
    public double precoAPagar(){
        return valorItens() + valorTaxa();
    }
    
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
	
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido Para Entrega");
        relat.append("\n"+detalhesPedido());
        relat.append(String.format("TAXA DE ENTREGA: %s (%.2f km)", moeda.format(valorTaxa()), distanciaEntrega));
        relat.append("\n"+notaDesconto());
        relat.append(String.format("\nTOTAL A PAGAR: %s", moeda.format(precoAPagar())));
        relat.append("\n=============================");
        return relat.toString();
    }

}