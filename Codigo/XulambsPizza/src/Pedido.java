import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;

public class Pedido {
    private static Random sorteio = new Random(42);
	private static int ultimoPedido;
	private LocalDate data;
	private LinkedList<Pizza> pizzas;
	private int idPedido;
	private boolean aberto;

	public Pedido() {
        ultimoPedido += sorteio.nextInt(1, 6);
        
		aberto = true;
        pizzas = new LinkedList<>();
        data = LocalDate.now();
        idPedido = data.getDayOfMonth()*1000 + ultimoPedido;
	}

	private boolean podeAdicionar() {
		return aberto;
	}

	public int adicionar(Pizza pizza) {
		if(podeAdicionar()){
            pizzas.addLast(pizza);
        }
        return pizzas.size();
	}

	public boolean fecharPedido() {
		if(pizzas.size() > 0 )
            aberto = false;
        return aberto;
	}

	public double precoAPagar() {
		double preco = 0d;
        for (Pizza pizza : pizzas) {
            preco += pizza.valorAPagar();
        }

        // for (int i = 0; i < pizzas.size(); i++) {
        //     Pizza pizza = pizzas.get(i);
        //     preco += pizza.valorAPagar();        
        // }
        return preco;
	}

	public String relatorio() {
		String relat = String.format("Pedido nº %d - %s\n",
                            idPedido, data.toString());
        for (int i = 0; i < pizzas.size(); i++) {
            relat += String.format("%02d: %s\n", 
                            (i+1), pizzas.get(i).cupomDeVenda());
        }
        relat += String.format("TOTAL DO PEDIDO: R$ %.2f",
                         precoAPagar());
        return relat;
	}

}