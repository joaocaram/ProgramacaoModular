import java.util.List;

public interface IFidelidade {
    public double descontoPedido(Pedido pedido);
    
    static IFidelidade definirCategoria(List<Pedido> pedidos){
        return null;
    }
}
