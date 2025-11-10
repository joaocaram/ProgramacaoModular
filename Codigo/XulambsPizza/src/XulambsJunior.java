public class XulambsJunior implements IFidelidade {
    private static final double PCT_DESCONTO = 0.0;
    
    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar() * PCT_DESCONTO;
        pedido.aplicarDesconto(valor); 
        return valor;
    }

    @Override
    public String toString(){
        return "Cliente Xulambs Júnior";
    }
}
