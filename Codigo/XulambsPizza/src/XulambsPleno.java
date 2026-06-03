public class XulambsPleno implements IFidelidade {
    private static final double PCT_DESCONTO = 0.1;

    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar();
        return valor * PCT_DESCONTO;
    }
 
    @Override
    public String toString(){
        return "Pleno";
    }
}
