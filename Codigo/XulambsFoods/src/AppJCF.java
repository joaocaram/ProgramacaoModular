import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;



/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
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

public class AppJCF {

    static List<Cliente> listaClientes;
    static PriorityQueue<Pedido> filaPedidos;
    static HashMap<Integer,Cliente> mapaClientes;
    static TreeMap<String,Cliente> arvoreClientes;
    static BaseDados<String, Cliente> baseClientes;
    

    static Scanner teclado;

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("\nTecle Enter para continuar.");
        teclado.nextLine();
    }

    static void gerarClientes() {
        String[] nomes = {"Everson", "Guilherme", "Givanildo", "Rodrigo", "Gustavo",
         "Paulinho", "Alan" };
        for (String nome : nomes) {
            Cliente novo = new Cliente(nome);
            listaClientes.add(novo);
            baseClientes.cadastrar(nome, novo);
            arvoreClientes.put(nome, novo);
            mapaClientes.put(novo.hashCode(), novo);
        }
    }
    
    static void gerarPedidos() {
        Random aleat = new Random(42);
        Pedido pedido;
        Comida comida;
        for(int i=0; i< listaClientes.size()*10; i++) {
            int tipo = aleat.nextInt() % 2;
            if (tipo == 0) pedido = new PedidoLocal();
            else pedido = new PedidoDelivery(aleat.nextInt(14));
            int quantComidas = aleat.nextInt(4) + 1;
            for (int j = 0; j < quantComidas; j++)
            {
                tipo = aleat.nextInt() % 2;
                int quantAdic = aleat.nextInt(5);
                if (tipo == 0) comida = new Pizza(quantAdic);
                else comida = new Sanduiche(quantAdic);
                pedido.addComida(comida);
            }
            pedido.fecharPedido();
            int pos = aleat.nextInt(listaClientes.size());
            listaClientes.get(pos).registrarPedido(pedido);
            filaPedidos.add(pedido);
        }
    }

    static void listagemGeralClientes(){
        limparTela();
        System.out.println("Lista de clientes: ");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente+"\n");
        }
        pausa(); limparTela();

        System.out.println("Árvore de clientes: ");
        for (Cliente cliente : arvoreClientes.values()) {
            System.out.println(cliente+"\n");
        }
        pausa(); limparTela();

        System.out.println("Mapa de clientes: ");
        for (Cliente cliente : mapaClientes.values()) {
            System.out.println(cliente+"\n");
        }
       
        pausa(); limparTela();

        System.out.println("Base de dados: ");
        baseClientes.ordenar(new ComparadorPorGastoCliente());
        System.out.println(baseClientes.relatorio());

        pausa(); limparTela();
    
    }

    static void listagemGeralPedidos(){
        limparTela();
        System.out.println("Pedidos por prioridade: ");
        for (Pedido ped : filaPedidos) {
            System.out.println(ped+"\n");
        }
        pausa(); limparTela();
    }
       
    static void enfileirarDesenfileirarPedidos() {
        System.out.println("Fila de prioridade de pedidos:");
        System.out.println(filaPedidos.peek());
        pausa();
        Pedido pedNovo = new PedidoLocal();
        pedNovo.fecharPedido();
        filaPedidos.add(pedNovo);
        System.out.println(filaPedidos.peek());
        pausa(); limparTela();
        listagemGeralPedidos();
        
    }

    public static void localizandoDados(){
            Cliente testeCliente;
            Pedido testePedido;
            System.out.println("Localizando dados de cliente por índice na lista: ");
            
            testeCliente = listaClientes.get(3);       //por índice na lista
            System.out.println(testeCliente);

            System.out.println("\nLocalizando dados de cliente por busca na lista: ");
            Cliente auxBusca = new Cliente("Paulinho");
            int pos = listaClientes.indexOf(auxBusca);      //buscando e acessando pela posição
            testeCliente = listaClientes.get(pos);
            System.out.println(testeCliente);

            System.out.println("\nLocalizando dados de cliente no mapa por ID: ");
            testeCliente = mapaClientes.get(3);       //buscando na tabela por ID
            System.out.println(testeCliente);

            System.out.println("\nLocalizando dados de cliente na árvore por nome: ");
            testeCliente = arvoreClientes.get("Everson");
            System.out.println(testeCliente);
                       

            System.out.println("\nMostrando o primeiro pedido na fila de prioridades: ");
            testePedido = filaPedidos.peek();
            System.out.println(testePedido);

           pausa();limparTela();
           
    }

    private static void removendoDados() {
        Cliente testeCliente;
        Pedido testePedido;

        System.out.println("Removendo dados da lista: (Cliente 'Rodrigo'): ");
        testeCliente = new Cliente("Rodrigo");
        listaClientes.remove(testeCliente);
        for (Cliente cli : listaClientes)
           System.out.println(cli + "\n");
        pausa(); limparTela();
        
        System.out.println("Removendo dados da lista: (Cliente na posição 0): ");
        listaClientes.remove(0);
        for (Cliente cli : listaClientes)
            System.out.println(cli + "\n");
        pausa(); limparTela();
        
        System.out.println("Removendo dados da tabela: (Cliente com id 4): ");
        testeCliente = mapaClientes.remove(4);
        System.out.println("Removido: " + testeCliente);
        pausa(); limparTela();

        System.out.println("Removendo dados da árvore: (Cliente 'Gustavo'): ");
        testeCliente = arvoreClientes.remove("Gustavo");
        System.out.println("Removido: " + testeCliente);
        System.out.println("\nRestantes: ");
        for (Cliente cli : arvoreClientes.values())
           System.out.println(cli + "\n");
        pausa(); limparTela();         
        
        System.out.println("Desenfileirando da fila de prioridades: ");
        testePedido = filaPedidos.poll();
        System.out.println(testePedido);
        testePedido= filaPedidos.poll();
        System.out.println("\n"+testePedido);
        
        pausa(); limparTela();
}

    public static void filaDePrioridadeDescendente(){
        Pedido testePedido;
        System.out.println("Nova fila de prioridades (descendente): ");

        Comparator<Pedido> compDescendente = ( (p1, p2) -> (p1.precoFinal()>p2.precoFinal()?-1:1));
        
        PriorityQueue<Pedido> filaDescendente = new PriorityQueue<>(compDescendente);
        filaDescendente.addAll(filaPedidos);

        System.out.println("Desenfileirando da fila de prioridades descendente: ");
        testePedido = filaDescendente.poll();
        System.out.println(testePedido);
        testePedido = filaDescendente.poll();
        System.out.println(testePedido);
        pausa();limparTela();
    }

    public static void totalizacaoComStreamNoCliente(){
        Cliente testeCliente;
        System.out.println("Dados de um cliente usando Stream: ");
        testeCliente = arvoreClientes.get("Givanildo");
        System.out.println(testeCliente);
        System.out.println("Gasto total: R$ "+ testeCliente.totalEmPedidos());
        System.out.println("Média por pedido: R$ "+testeCliente.valorMedioPorPedido());
        pausa();limparTela();
    }

    public static void totalizacaoComStreamNaLista(){
        System.out.println("Soma e média de dados dos clientes direto na lista, usando stream: ");
        double gastoTotal = listaClientes.stream()
                                        .mapToDouble(c -> c.totalEmPedidos())
                                        .sum();

        double mediaDeGasto = listaClientes.stream()
                                            .mapToDouble(c -> c.totalEmPedidos())
                                            .average()
                                            .orElse(0d);

        System.out.println("Gasto total: R$ "+gastoTotal);
        System.out.println("Gasto médio por cliente: R$ "+mediaDeGasto);
        pausa();limparTela();
    }

    public static void totalizacaoComStreamNaBase(){
        System.out.println("Mesma ação: \nSoma e média de dados dos clientes lá na base, usando stream: ");
        double gastoTotal = baseClientes.totalizar(c->c.totalEmPedidos());
        double mediaGastos = baseClientes.media(c->c.totalEmPedidos());
        System.out.printf("Gasto total: R$ %.2f\n", gastoTotal);
        System.out.printf("Gasto médio: R$ %.2f\n", mediaGastos);
        pausa();limparTela();
    }

    public static void buscarMaiorGastoNaLista(){
        System.out.println("Cliente que gastou mais, direto na lista, usando stream: ");
       Comparator<Cliente> comp =( (cli1, cli2) ->
                                cli1.totalEmPedidos() >= cli2.totalEmPedidos()?1:-1);
        Cliente maiorGastador = listaClientes.stream()
                                             .max(comp)
                                             .orElse(new Cliente(null));
        System.out.println(maiorGastador);
                                            
       
        pausa();limparTela();              
    }

    public static void buscarMaiorGastoNaBase(){
        System.out.println("Cliente que gastou mais, lá na base, usando stream: ");
        Comparator<Cliente> comp =( (cli1, cli2) ->
                                 cli1.totalEmPedidos() >= cli2.totalEmPedidos()?1:-1);
         Cliente maiorGastador = baseClientes.maiorDeTodos(comp);
         System.out.println(maiorGastador);    
        pausa();limparTela();            
    }

    private static void filtrandoGastosNaFilaPedidos() {
         System.out.println("Pedidos com valor filtrado: ");
        double minimo, maximo;
        System.out.print("Qual o valor mínimo de pedido para o filtro? R$ ");
        minimo = Double.parseDouble(teclado.nextLine());
        System.out.print("Qual o valor máximo de pedido para o filtro? R$ ");
        maximo = Double.parseDouble(teclado.nextLine());
        limparTela();
        System.out.printf("Pedidos entre R$ %.2f e R$ %.2f:\n", minimo, maximo);
        filaPedidos.stream()
                    .filter(p-> p.precoFinal()>=minimo && p.precoFinal()<=maximo)
                    .sorted((p1,p2)->p1.precoFinal()>p2.precoFinal()?-1:1)
                    .forEach(p-> System.out.println(p+"\n"));  
        pausa();
        limparTela();
    }

    private static void filtrandoGastosComReducao() {
        System.out.println("Pedidos com valor filtrado: ");
       double minimo, maximo;
       System.out.print("Qual o valor mínimo de pedido para o filtro? R$ ");
       minimo = Double.parseDouble(teclado.nextLine());
       System.out.print("Qual o valor máximo de pedido para o filtro? R$ ");
       maximo = Double.parseDouble(teclado.nextLine());
       limparTela();
       System.out.printf("Pedidos entre R$ %.2f e R$ %.2f:\n", minimo, maximo);
       System.out.println(
            filaPedidos.stream()
                        .filter(p-> p.precoFinal()>=minimo && p.precoFinal()<=maximo)
                        .sorted((p1,p2)->p1.precoFinal()>p2.precoFinal()?-1:1)
                        .map(ped -> ped.toString())
                        .reduce((s1, s2) -> s1.concat("\n"+s2))
                        .orElse("Não existem pedidos com estes valores.")
       );
       pausa();
       limparTela();
   }

    public static void filtrandoDadosNaListaClientes(){
        double minimo;
        System.out.print("Qual o valor mínimo de gasto por cliente? R$ ");
        minimo = Double.parseDouble(teclado.nextLine());
        System.out.printf("Clientes que gastaram pelo menos R$ %.2f:\n", minimo) ;
        listaClientes.stream()
                    .filter(cli-> cli.totalEmPedidos()>=minimo)
                    .forEach(cli-> System.out.println(cli+"\n"));  
        pausa();limparTela();
    }

    public static void filtrandoNaArvore(){
        System.out.print("Digite uma letra para filtrar: ");
        String letraFinal = teclado.nextLine()+"z";
        SortedMap<String, Cliente> antesDe = arvoreClientes.subMap("A", letraFinal);
        for (Cliente cliente : antesDe.values()) {
            System.out.println(cliente+"\n");
        }
    }
    
    

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
    
        listaClientes = new LinkedList<>();
        mapaClientes = new HashMap<>(30);
        arvoreClientes = new TreeMap<>();
        baseClientes = new BaseDados<>(30);    
        filaPedidos = new PriorityQueue<>(200, 
                            (ped1, ped2) -> (ped1.precoFinal()>ped2.precoFinal()?1:-1));
        
        gerarClientes();
        gerarPedidos();

       listagemGeralClientes();

       localizandoDados();

       removendoDados();
        
       filaDePrioridadeDescendente();

        totalizacaoComStreamNoCliente();

        totalizacaoComStreamNaLista();

        totalizacaoComStreamNaBase();

        buscarMaiorGastoNaLista();

        buscarMaiorGastoNaBase();

        filtrandoGastosNaFilaPedidos();
        
        filtrandoDadosNaListaClientes();

        filtrandoGastosComReducao();

        filtrandoNaArvore();

        teclado.close();
    }

   
   

    

    
}
