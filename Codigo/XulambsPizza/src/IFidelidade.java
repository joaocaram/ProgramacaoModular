
import java.util.List;

public interface IFidelidade {
    static final int[] QUANTIDADES = {0,10,20};
    static final int[] VALORES =     {0,29,44};
    
    public double descontoPedido(Pedido pedido);
    
    public static IFidelidade definirCategoria(List<Pedido> pedidos){
        IFidelidade[] categorias = {new XulambsJunior(), new XulambsPleno(), new XulambsSenior()}; 
        IFidelidade resposta = null;
        int quantidade = pedidos.size();
        double valor = 0d;
        for (Pedido pedido : pedidos) {
            valor += pedido.precoAPagar();
        }
        double valorMedio = valor/quantidade;
        int pos = 2;
        while(quantidade < QUANTIDADES[pos] && pos >= 0){
               pos--; 
        }
        if(valorMedio >= VALORES[pos])
            resposta = categorias[pos];
        else
            resposta = categorias[pos-1];
        return resposta;
    }
}
