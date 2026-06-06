public class XulambsJunior implements IFidelidade {
    private static final double PCT_DESCONTO = 0;
    
    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar();
        return valor * PCT_DESCONTO;
    }

    @Override
    public String toString(){
        return "Junior";
    }
}