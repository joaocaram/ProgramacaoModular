public class XulambsPleno implements IFidelidade {
    private static final double PCT_DESCONTO = 0.1;
    private static final int MIN_PEDIDOS = 10;
    private static final double MIN_MEDIA = 29d;

    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar();
        return valor * PCT_DESCONTO;
    }
    
}
