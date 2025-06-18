import java.util.List;

public interface IFidelidade {
    public double descontoPedido(Pedido pedido);

    static IFidelidade definirCategoria(List<Pedido> pedidos){
        IFidelidade categoria = new XulambsJunior();
        int quantos = pedidos.size();
        double valorMedio = 0d;
        for (Pedido pedido : pedidos) {
            valorMedio += pedido.precoAPagar();
        }
        valorMedio /= quantos;
        if( quantos>=XulambsSenior.MIN_PEDIDOS && valorMedio>=XulambsSenior.MIN_MEDIA)
                categoria = new XulambsSenior();
        else if( quantos>=XulambsPleno.MIN_PEDIDOS && valorMedio>=XulambsPleno.MIN_MEDIA)
                categoria = new XulambsPleno();
        return categoria;
        
    }
}
