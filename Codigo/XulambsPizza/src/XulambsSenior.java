public class XulambsSenior implements IFidelidade {
    private static final double PCT_DESCONTO = 0.15;
    public static final int MIN_PEDIDOS = 20;
    public static final double MIN_MEDIA = 44d;
    public static final int COMPRAS_CUPOM = 5;
    public static final double VALOR_CUPOM = 10d;

    private int quantCompras = 0;

    @Override
    public double descontoPedido(Pedido pedido) {
        double desconto = pedido.precoAPagar() * PCT_DESCONTO;
        if((quantCompras+1 % COMPRAS_CUPOM) ==0) 
            desconto += VALOR_CUPOM;
        quantCompras++;
        pedido.aplicarDesconto(desconto);
        return desconto;
    }
   
    @Override
    public String toString(){
        return "Cliente Xulambs Senior";
    }
}