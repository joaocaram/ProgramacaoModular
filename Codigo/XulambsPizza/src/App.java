import java.util.LinkedList;
import java.util.List;

/** 
 * MIT License
 *
 * Copyright(c) 2022-26 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {

    static List<Pedido> pedidos;    // lista com todos os pedidos. A melhorar no futuro. 

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        IO.println("\nTecle Enter para continuar.");
        IO.readln();
    }
    static void cabecalho() {
        limparTela();
        IO.println("XULAMBS PIZZA v0.2\n=============");
        IO.println("Pizzas vendidas hoje: "+Pizza.pizzasVendidas());
    }

    static int exibirMenu() {
        cabecalho();
        
        IO.println("1 - Abrir Pedido");
        IO.println("2 - Alterar Pedido");
        IO.println("3 - Fechar Pedido");
        IO.println("4 - Relatório de Pedido");
        IO.println("0 - Finalizar");
        return Integer.parseInt(IO.readln("Digite sua escolha: "));
    }

    static void abrirPedido(){
        cabecalho();
        Pedido novo = new Pedido();
        String outraPizza;
        do{
            Pizza novaPizza = comprarPizza();
            novo.adicionar(novaPizza);
            outraPizza = IO.readln("Mais pizza(s/n)? ");
        }while(outraPizza.toLowerCase().equals("s"));
        pedidos.add(novo);
        IO.println(novo.relatorio());
    }

    static void alterarPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Incluir itens em um pedido.");
        int numPedido = Integer.parseInt(IO.readln("Número do pedido: "));
        Pedido pedido = localizarPedido(numPedido);
        if(pedido != null ){
            pedido.adicionar(comprarPizza());
            resposta = pedido.relatorio();
        }
        IO.println(resposta);
    }

    static void fecharPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Fechar um pedido.");
        int numPedido = Integer.parseInt(IO.readln("Número do pedido: "));
        Pedido pedido = localizarPedido(numPedido);
        if(pedido != null ){
            pedido.fecharPedido();
            resposta = pedido.relatorio();
        }
        IO.println(resposta);
    }

    static Pizza comprarPizza() {
        cabecalho();
        IO.println("Comprando uma nova pizza:");
        int adicionais = Integer.parseInt(IO.readln("Quantos adicionais você deseja? (máx. 8): "));
        Pizza novaPizza = new Pizza(adicionais);
        mostrarNota(novaPizza);
        return novaPizza;
    }

    static void mostrarNota(Pizza pizza) {
        IO.println("Você acabou de comprar: \n ");
        IO.println(pizza.cupomDeVenda());

    }

    public static void main(String[] args) throws Exception {
        pedidos = new LinkedList<>();
        int opcao = -1;
        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1 -> abrirPedido();    
                case 2 -> alterarPedido();    
                case 3 -> fecharPedido();    
                case 4 -> relatorioPedido();    
            }
            pausa();
        } while (opcao != 0);        
        IO.println("FLW T+ VLW ABS.");
    }

}
