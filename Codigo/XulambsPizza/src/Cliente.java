import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class Cliente implements Comparable<Cliente>{
    private int id;
    private String nome;
    private IFidelidade categoria;
    private List<Pedido> pedidos;

    /**
     * Um cliente tem seu id definido por entidade externa (deve ser valor positivo) e um nome de pelo menos duas letras.
     * @param id ID, definido pela entidade externa (valor positivo)
     * @param nome Nome do cliente (mínimo duas letras)
     * @throws IllegalArgumentException em caso de qualquer parâmetro inválido.
     */
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

    /**
     * Atualiza a categoria de fidelidade do cliente, retornando-a.
     * @return Nova categoria de fidelidade do cliente.
     */
    public IFidelidade verificarCategoria(){
        categoria = IFidelidade.definirCategoria(pedidos);
        return categoria;
    }

    /**
     * Registra um pedido para um cliente, aplicando o desconto que o cliente tem direito neste pedido.
     * @param novo Pedido para registrar e aplicar o desconto.
     * @return Quantidade de pedidos registrados para este cliente
     * @throws IllegalArgumentException em caso de pedido nulo/inválido
     */
    public int registrarPedido(Pedido novo){
        if(novo == null)
            throw new IllegalArgumentException("Pedido inválido");
        
        double desconto = categoria.descontoPedido(novo);
        novo.aplicarDesconto(desconto);
        pedidos.addLast(novo);
        return pedidos.size();
    }

    /**
     * Total gasto pelo cliente (soma dos valores dos pedidos)
     * @return Valor double não negativo (0 se ainda não tem pedidos)
     */
    public double totalGasto(){
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.precoAPagar();
        }
        return total;
    }

    /**
     * Relatório extenso com todos os pedidos do cliente. Cada pedido vem com seus detalhes.
     * @return String com todos os detalhes de todos os pedidos do cliente.
     */
    public String relatorioPedidos(){
        StringBuilder relat = new StringBuilder();
        for (Pedido pedido : pedidos) {
            relat.append("\n~~~~~~~~~~~~~~~~~~~~\n"+pedido);
        }
        return relat.toString();
    }

    /**
     * Retorna o nome do cliente para UI
     * @return String com nome do cliente, conforme cadastro
     */
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

    @Override
    public int compareTo(Cliente outro) {
        return this.id - outro.id;    
    }
}
