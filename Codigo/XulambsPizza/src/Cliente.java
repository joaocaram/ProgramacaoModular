import java.text.NumberFormat;
import java.util.LinkedList;

public class Cliente {
    private int id;
    private String nome;
    private IFidelidade categoria;
    private LinkedList<Pedido> pedidos;

    public Cliente(int id, String nome) {
        if (id < 0 || nome.length() < 2)
            throw new IllegalArgumentException("Id deve ser positivo e o nome deve ter ao menos 2 letras");
        this.id = id;
        this.nome = nome;
        categoria = new XulambsJunior();
        pedidos = new LinkedList<>();
    }

    public int registrarPedido(Pedido novo) {
        if (novo == null)
            throw new IllegalArgumentException("Pedido não foi criado corretamente");
        categoria.descontoPedido(novo);
        pedidos.addLast(novo);
        return pedidos.size();
    }

    public String relatorioPedidos() {
        StringBuilder relat = new StringBuilder(String.format("%s (%d) - Relatório de Pedidos\n", nome, id));
        for (Pedido pedido : pedidos) {
            relat.append("==============\n");
            relat.append(pedido + "\n");
        }
        return relat.toString();
    }

    public double totalGasto() {
        double total = 0d;
        for (Pedido pedido : pedidos) {
            total += pedido.precoAPagar();
        }
        return total;
    }

    public void atualizarCategoria(){
        categoria = IFidelidade.definirCategoria(pedidos);
    }
    
    @Override
    public String toString() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s (%d). Total gasto: %s em %d pedidos.\n%s", nome, id, moeda.format(totalGasto()),
                pedidos.size(), categoria);
    }

    @Override
    public boolean equals(Object obj) {
        boolean resposta;
        try {
            Cliente outro = (Cliente) obj;
            resposta = this.id == outro.id;
        } catch (ClassCastException cex) {
            resposta = false;
        }
        return resposta;
    }

    @Override
    public int hashCode() {
        return id;
    }
}