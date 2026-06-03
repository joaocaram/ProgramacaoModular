public class PedidoFechadoException
                        extends IllegalStateException {
    private int idPedido;

    public PedidoFechadoException(int id){
        super("Não pode adicionar produtos em pedido fechado.");
        idPedido = id;
    }

    public int getPedido(){
        return idPedido;
    }
    
}
