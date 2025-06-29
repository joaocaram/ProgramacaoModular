import java.text.NumberFormat;

public class PedidoPromocional extends Pedido {

    private static final int COMIDAS_DESCONTO = 3;

    public PedidoPromocional(){
        
    }

    private double comidaMaisBarata(){
        return comidas.stream()
                      .min((c1, c2) -> c1.valorFinal()>c2.valorFinal()?1:-1)
                      .map( c -> c.valorFinal())
                      .orElse(0d);
    }

    @Override
    public double precoAPagar() {
        double preco = valorItens();
        if(quantComidas >= COMIDAS_DESCONTO)
            preco -= comidaMaisBarata();
        return preco;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		
		StringBuilder relat = new StringBuilder("XULAMBS FOODS - Pedido Promocional");
        relat.append("\n"+detalhesPedido());
        if(quantComidas >= COMIDAS_DESCONTO)
            relat.append(String.format("PROMOÇÃO:      %s\n", moeda.format(comidaMaisBarata())));
        relat.append(String.format("TOTAL A PAGAR: %s", moeda.format(precoAPagar())));
        relat.append("\n=============================");
        return relat.toString();
    }
    
}
