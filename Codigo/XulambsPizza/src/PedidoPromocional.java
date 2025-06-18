import java.text.NumberFormat;

public class PedidoPromocional extends Pedido {

    private static final int COMIDAS_DESCONTO = 3;

    public PedidoPromocional(){
        super(100);         //conceitualmente, não há limite. Melhoraremos isso em breve.
    }

    private double comidaMaisBarata(){
        double valorMaisBarato = 0;
        if(quantComidas > 0){
            Comida maisBarata = comidas[0];
            for (int i = 1; i < quantComidas; i++) {
                if(comidas[i].compareTo(maisBarata) < 0)
                    maisBarata = comidas[i];
            }
            valorMaisBarato = maisBarata.valorFinal();
        }
        return valorMaisBarato;
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
