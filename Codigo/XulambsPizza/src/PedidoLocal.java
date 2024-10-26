public class PedidoLocal extends Pedido{
    /** Para criar um vetor de pizzas de tamanho grande */
	private static final int MAX_PIZZAS = 100;
	
    private static final double TAXA_SERVICO = 0.1;
    private static final double PCT_DESCONTO = 0.5;
	private static final int PIZZAS_DESCONTO = 4;

    public PedidoLocal(){
        super(MAX_PIZZAS);
    }

    /**
	 * Verifica se uma pizza pode ser adicionada ao pedido, ou seja, se o pedido
	 * está aberto e há espaço na memória.
	 * @return TRUE se puder adicionar, FALSE caso contrário
	 */
    @Override
	protected boolean podeAdicionar() {
		return aberto && quantComidas < MAX_PIZZAS;
	}

    @Override
    protected double valorTaxa(){
        return valorItens() * TAXA_SERVICO;
    }

    protected double descontoItens(){
        double desconto = 0d;
        if(quantComidas >= PIZZAS_DESCONTO){
			double menor = Double.MAX_VALUE;
			for(int i=0; i< quantComidas; i++)
				if(comidas[i].valorFinal() < menor)
					menor = comidas[i].valorFinal();
			desconto = menor * (1-PCT_DESCONTO);
		}
        return desconto;
    }

    @Override
    public double precoAPagar(){
        return valorItens() + valorTaxa() - descontoItens();
    }
	
}
