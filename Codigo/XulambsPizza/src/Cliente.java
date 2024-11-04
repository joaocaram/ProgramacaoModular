import java.util.LinkedList;
import java.util.List;


public class Cliente implements Comparable<Cliente>{
  
    private static int ultimoID = 0;
    private int id;
    private String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome)
    {
        if (nome.length() == 0)
            nome = "Cliente anônimo";
        this.nome = nome;
        id = ++ultimoID;
        pedidos = new LinkedList<>();
    }

    @Override
    public int compareTo(Cliente outro)
    {
        return nome.compareTo(outro.nome);
        
    }

    public int RegistrarPedido(Pedido novo)
    {
        pedidos.add(novo);
        return pedidos.size();
    }

    public String RelatorioPedidos()
    {
        StringBuilder relat = new StringBuilder(String.format("Cliente %s - %d",nome, id));
        for (Pedido p : pedidos)
            relat.append(p.toString()+"\n");
        relat.append(String.format("Total gasto pelo cliente: R$ %.2f", totalGasto()));
        return relat.toString();
    }

    public double totalGasto()
    {
        double valor = 0d;
        for (Pedido p : pedidos)
           valor += p.precoAPagar();
        return valor;
        
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public  boolean equals(Object obj) {
        boolean resposta = false;
        Cliente outro = (Cliente)obj;
        if (obj != null)
            resposta = (id == outro.id);
        return resposta;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) já gastou %.2f no Xulambs Foods.", nome, id, totalGasto());
    }
}

