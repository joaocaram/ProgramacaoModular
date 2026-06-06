import java.time.LocalDate;

public class PedidoLocal extends Pedido {

    private static final double TAXA_SERVICO = 0.1;

    public PedidoLocal(){
        super();
    }

    public PedidoLocal(LocalDate data){
        super(data);
    }

    private double valorServico(){
        return valorItens() * TAXA_SERVICO;
    }

    @Override
    public double precoAPagar(){
        return valorItens() + valorServico() - desconto;
    }

    /**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada pizza e o preço final a ser pago.
	 * @return String com os detalhes especificados.
     */
	@Override
	public String toString() {
		StringBuilder relat = new StringBuilder(cabecalhoPedido());
        relat.append(String.format("\nSERVIÇO: R$ %.2f\n", valorServico()));
        relat.append(rodapePedido());
        return relat.toString();
	} 
}