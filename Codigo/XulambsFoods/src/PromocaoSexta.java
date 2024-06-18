public class PromocaoSexta implements IPromocao {

    private static final double PCT_DESCONTO = 0.12;
    private static final double MINIMO_PARA_DESCONTO = 60d;

    @Override
    public double valorComDesconto(Pedido pedido) {
        double valor = pedido.precoFinal();
        return (valor >= MINIMO_PARA_DESCONTO ? valor * (1-PCT_DESCONTO) : valor);
    }
    
}
