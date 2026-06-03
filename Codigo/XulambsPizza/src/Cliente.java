import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private IFidelidade categoria;
    private List<Pedido> pedidos;

    public Cliente(int id, String nome){
        if(id < 0)
            throw new IllegalArgumentException("Id inválido");
        if(nome == null || nome.length() < 2)
            throw new IllegalArgumentException("Nome inválido");

        this.id = id;
        this.nome = nome;
        pedidos = new LinkedList<>();
        categoria = new XulambsJunior();
    }

    public IFidelidade verificarCategoria(){
        categoria = IFidelidade.definirCategoria(pedidos);
        return categoria;
    }

    public int registrarPedido(Pedido novo){
        if(novo == null)
            throw new IllegalArgumentException("Pedido inválido");
        
        double desconto = categoria.descontoPedido(novo);
        novo.aplicarDesconto(desconto);
        pedidos.addLast(novo);
        return pedidos.size();
    }

    public double totalGasto(){
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.precoAPagar();
        }
        return total;
    }

    public String relatorioPedidos(){
        StringBuilder relat = new StringBuilder();
        for (Pedido pedido : pedidos) {
            relat.append("\n"+pedido);
        }
        return relat.toString();
    }

    public String getNome(){
        return nome;
    }
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        double total = totalGasto();
        int quantidade = pedidos.size();
        return String.format("%02d - %s (%s) - Total de pedidos: %d - Valor gasto: %s (%s)",
                             id, nome, categoria.toString(), quantidade, moeda.format(total), moeda.format(total/quantidade));
    }

    @Override
    public int hashCode(){
        return id;
    }
}
