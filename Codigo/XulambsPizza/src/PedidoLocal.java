public class PedidoLocal extends Pedido{
    private static final double TAXA_SERVICO = 0.1;
    
    public PedidoLocal(){
        super();
    }

    private double valorServico(){
        return valorItens() * TAXA_SERVICO;
    }

    @Override
    public double precoAPagar(){
        return valorServico() + valorItens() - desconto;
    }

    /**
	 * Cria um relatório para o pedido, contendo seu número, sua data (DD/MM/AAAA), detalhamento
	 * de cada pizza e o preço final a ser pago.
	 * @return String com os detalhes especificados:. 
	 * <br/><pre>
	 * PEDIDO - NÚMERO - DD/MM/AAAA
	 * 01 - DESCRICAO DA PIZZA
	 * 02 - DESCRICAO DA PIZZA
	 * 03 - DESCRICAO DA PIZZA
	 * 
	 * TOTAL A PAGAR: R$ VALOR
	 * </pre>
	 */
	public String toString() {
        return "XULAMBS PIZZA - Pedido Local "
                 + detalhesPedido() + "\n" 
                 + "TAXA SERVIÇO: R$ "+valorServico()+"\n"
                 + rodapePedido();		
	}
}