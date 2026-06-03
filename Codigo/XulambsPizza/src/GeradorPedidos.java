import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorPedidos {
    static Random sorteio = new Random(42);

    static List<Pedido> gerarPedidos(LocalDate dataInicio, LocalDate dataFim, List<Cliente> clientes, int fator){
        int quantidade = clientes.size() * fator;
        int periodo = (int)dataInicio.until(dataFim, ChronoUnit.DAYS);
        int porDia = (quantidade/periodo)+1;

        ArrayList<Pedido> pedidos = new ArrayList<>((int)Math.ceil(quantidade*1.1));
        LocalDate dataPedido = dataInicio;
        for (int i = 0; i < quantidade; i++) {
            Pedido novo = new PedidoLocal(dataPedido);
            int produtos = sorteio.nextInt(1, 6);
            for (int j = 0; j < produtos; j++) {
                int qualProduto = sorteio.nextInt(1, 5);
                IProduto produtoNovo = GeradorProdutos.criar(qualProduto);
                novo.adicionar(produtoNovo);
            }
            int posicao = sorteio.nextInt(clientes.size());
            Cliente cliente = clientes.get(posicao);
            cliente.registrarPedido(novo);
            novo.fecharPedido();
            pedidos.add(novo);
        }
    
        return pedidos;
    }
    
}
