import java.text.NumberFormat;

public class PedidoLocal extends Pedido {

    protected PedidoLocal() {
        super(0);
    }

    @Override
    public double precoAPagar() {
        return valorItens();
    }
    
    /**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada comida e o preço final a ser pago.
	 * @return String com os detalhes especificados:. 
	 * <p>
     * <pre>
	 * PEDIDO - NÚMERO - DD/MM/AAAA
	 * 01 - DESCRICAO DA COMIDA
	 * 02 - DESCRICAO DA COMIDA
	 * 03 - DESCRICAO DA COMIDA
	 * 
	 * TOTAL A PAGAR: R$ VALOR
	 * </pre>
	 */
	@Override
	public String toString() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		
		StringBuilder relat = new StringBuilder("XULAMBS PIZZA - Pedido Local");
        relat.append("\n"+detalhesPedido());
        relat.append(String.format("TOTAL A PAGAR: %s", moeda.format(precoAPagar())));
        relat.append("\n=============================");
        return relat.toString();
	}
}
