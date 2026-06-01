public class XulambsSenior implements IFidelidade{

    private static final double PCT_DESCONTO = 0.15;
    private static final int MIN_PEDIDOS = 20;
    private static final double MIN_MEDIA = 44d;
    
    private static final int COMPRAS_CUPOM = 5;
    private static final double VALOR_CUPOM =10d;

    private int quantCompras = 0;

    @Override
    public double descontoPedido(Pedido pedido) {
        double valor = pedido.precoAPagar() * PCT_DESCONTO;
        if(quantCompras % COMPRAS_CUPOM == 0){
            valor += VALOR_CUPOM;
        }   
        quantCompras++;
        return valor;
    }
    
}
