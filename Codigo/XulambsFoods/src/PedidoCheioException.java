public class PedidoCheioException extends UnsupportedOperationException{
    private int maxComidas;
    private int numPedido;

    public PedidoCheioException(int numPedido, int maxComidas){
        super("Pedido já tem o máximo de comidas");
        this.maxComidas = maxComidas;
        this.numPedido = numPedido;
        
    }
    
    public PedidoCheioException(String msg){
        super(msg);
        this.maxComidas = maxComidas;
    }

    public int maximoDeComidas(){
        return maxComidas;
    }

    public int numPedido(){
        return numPedido;
    }


}
