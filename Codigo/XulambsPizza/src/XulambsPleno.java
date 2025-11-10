public class XulambsPleno implements IFidelidade{
    private static final double PCT_DESCONTO = 0.1;
    public static final int MIN_PEDIDOS = 10;
    public static final double MIN_MEDIA = 29d;

    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar() * PCT_DESCONTO;
        pedido.aplicarDesconto(valor); 
        return valor;
    }

    
    @Override
    public String toString(){
        return "Cliente Xulambs Pleno";
    }
    
}
