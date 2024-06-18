public class PromocaoTerca implements IPromocao {
    
    
    @Override
    public double valorComDesconto(Pedido pedido) {
        return pedido.precoFinal() - pedido.taxa();
    }
}
