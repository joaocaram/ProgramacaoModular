

public class Cliente{
    private static final int MAX_PEDIDOS = 100;
    private static int ultimoCodigo = 0;
    private Pedido[] pedidos;
    private int qtPedidos;
    private int codigo;
    private String nome;

    public Cliente(String nome){
        this.nome = nome;
        this.codigo = ++ultimoCodigo;
        this.pedidos = new Pedido[MAX_PEDIDOS];
        this.qtPedidos = 0;
    }

    
    
    public boolean addPedido(Pedido novo){
        boolean resposta = false;
        if(this.qtPedidos<MAX_PEDIDOS){
            resposta = true;
            this.pedidos[this.qtPedidos++] = novo; 
        }
        return resposta;
    }

    public String relatorioPedidos(){
        StringBuilder relat = new StringBuilder("Cliente nº "+this.codigo+" - "+this.nome+"\n");

        for (Pedido pedido : pedidos) {
            relat.append("\n"+pedido);    
        }
        return relat.toString();
    }

    public Pedido maisCaro(){
        Util.quicksort(this.pedidos, 0, this.qtPedidos-1);
        return this.pedidos[this.qtPedidos-1];
    }

    public Pedido maisRecente(){
       Pedido recente = this.pedidos[0];
       for (int i = 0; i < this.qtPedidos; i++) {
            if(this.pedidos[i].hashCode()<recente.hashCode())
                recente = pedidos[i];
       }
       return recente;
    }

    public String nome(){
        return this.nome;
    }

   @Override
    public int hashCode(){
        return this.codigo;
    }

    @Override
    public boolean equals(Object obj){
        Cliente outro = (Cliente)obj;
        return this.nome.equals(outro.nome);
    }

    private double totalEmPedidos(){
        double total=0;
        for (Pedido pedido : pedidos) {
            total+= pedido.calcularPreco();
        }
        return total;
    }

    @Override
    public String toString(){
        StringBuilder cliente = new StringBuilder(String.format("%2d", this.codigo)+" - "+ this.nome+"\n");
        cliente.append("Total de pedidos: "+this.qtPedidos+"\n");
        cliente.append("Total gasto: R$"+ String.format("%.2f", this.totalEmPedidos()));
        return cliente.toString();
    }

   
}