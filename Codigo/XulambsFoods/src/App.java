import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

import javax.swing.text.html.Option;

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

public class App {
    
    static Scanner teclado;
    static BaseDados<String, Cliente> baseClientes;
    static BaseDados<Integer,Pedido> todosOsPedidos;
    static BaseDados<String, IFabrica<Comida>> fabricas;
    
    static Comparator<Cliente> compID = (  (cli1,cli2) ->
                                        (cli1.hashCode()-cli2.hashCode())
                                        );

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println("🍔 XULAMBS FOODS - v0.51 🍕");
        System.out.println("=====================");
    }

    static void config(){
        fabricas = new BaseDados<>(5);
        fabricas.cadastrar("pizza", new FabricaPizza());
        fabricas.cadastrar("pizzacomborda", new FabricaPizzaComBorda());
        fabricas.cadastrar("sanduiche", new FabricaSanduiche());
        fabricas.cadastrar("sanduichebacon", new FabricaSanduicheBacon());

        gerarClientes();
    }


    static int MenuPrincipal() {
        int opcao;
        cabecalho();
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Total Vendido Hoje");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Gerenciar Clientes");
        System.out.println("5 - Gerenciar Pedidos");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static int MenuClientes() {
        int opcao;
        cabecalho();
        System.out.println("1 - Relatório de um Cliente");
        System.out.println("2 - Resumo de Clientes");
        System.out.println("3 - Atualizar Fidelidades");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static int MenuComparadorPedidos() {
        int opcao;
        cabecalho();
        System.out.println("1 - Pedidos por identificador");
        System.out.println("2 - Pedidos por valor decrescente");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    private static Comparator<Pedido> escolherComparadorPedido() {
        
        
        Comparator<Pedido> comparador;
        int opcao = MenuComparadorPedidos();
        switch (opcao) {
            case 1: comparador = (  (ped1, ped2) ->
                                    (ped1.hashCode()-ped2.hashCode())
                                 );
                break;
            case 2: 
            default:
                    comparador = 
                            (  (p1, p2) ->
                               (p1.precoFinal()>=p2.precoFinal() ? -1 : 1)
                            );    
            break;
        }
        return comparador;      
    }

    static void acaoClientes() {
        int opcao = MenuClientes();
        Cliente atual;
        switch (opcao) {
            case 1:
                atual = localizarCliente();
                if (atual != null) {
                    System.out.println("Pedidos do Cliente:");
                    System.out.println(atual.resumoPedidos());
                } else
                    System.out.println("Cliente não encontrado.");
                break;
            case 2:
                cabecalho();
                Comparator<Cliente> comparador = escolherComparadorCliente();
                relatorioCliente(comparador);
                break;
            case 3:
                
                cabecalho();
                System.out.println("Atualizando fidelidades....");
                baseClientes.processar(
                                        (cli) -> cli.verificarCategoria()
                                      );                
                break;
        }
        pausa();
    }

    private static Comparator<Cliente> escolherComparadorCliente() {
        System.out.println("Escolha a ordem do relatório: ");       
        System.out.println("1 - Ordem alfabética (padrão)");       
        System.out.println("2 - Ordem de identificador");       
        System.out.println("3 - Ordem de gastos no restaurante "); 
        System.out.print("Digite sua opção: "); 
        
        Comparator<Cliente> comparador = Cliente::compareTo;
        int opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 2: comparador = (  (cli1,cli2) ->
                                    (cli1.hashCode()-cli2.hashCode())
                                 );
                break;
            case 3: comparador = 
                            (  (c1, c2) ->
                               (c1.totalEmPedidos()>=c2.totalEmPedidos()?1 : -1)
                            );    
            break;
        }
        return comparador;      
    }

    private static void relatorioCliente(Comparator<Cliente> comparador) {
        System.out.println("Resumo dos clientes:");
        baseClientes.ordenar(comparador);
        System.out.println(baseClientes.relatorio());    
    }

    static Comida MenuFabricaComida(){
        int opcao = 99;
        cabecalho();
        Comida comida = null;
        Menu comidas = new Menu("comidas.csv");

        System.out.println(comidas);
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        
        IFabrica<Comida> fabrica = fabricas.localizar(comidas.valor(opcao));
        
        if(fabrica!=null)
            comida = fabrica.criar();

        return comida;

    }
    static int MenuComida() {
        int opcao;
        cabecalho();
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduiche");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static void relatorioTotalVendido() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        cabecalho();
        double totalVendido = todosOsPedidos.totalizar(
                                                        (p) -> p.precoFinal()
                                                       );
        String hoje = LocalDate.now().format(df);
        System.out.println("Total vendido hoje (" + hoje + "): R$ " + String.format("%.2f", totalVendido));
        pausa();
    }

    static Pedido escolherTipoPedido() {
        int opcao = 0;
        Pedido novoPedido = null;
        cabecalho();
        System.out.println("1 - Para comer no local");
        System.out.println("2 - Para entrega");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 1 -> novoPedido = new PedidoLocal();
            case 2 -> {
                System.out.print("Digite a distância da entrega em km: ");
                double dist = Double.parseDouble(teclado.nextLine());
                novoPedido = new PedidoDelivery(dist);
            }
        }
        return novoPedido;

    }

    static Pedido criarPedido() {
        Pedido novoPedido = escolherTipoPedido();
        if (novoPedido != null) {
            Comida novaComida = criarComida();
            if (novaComida != null) {
                do {
                    novoPedido.addComida(novaComida);
                    System.out.println(novaComida.toString() + " adicionado ao pedido.");
                    pausa();
                    novaComida = criarComida();
                } while (novaComida != null);
                novoPedido.fecharPedido();
            } else
                novoPedido = null;
        }
        return novoPedido;
    }

    static boolean querBordaRecheada() {
        String querBorda = "";
        System.out.print("\nPizza com borda recheada (S/N)? ");
        querBorda = teclado.nextLine();
        return querBorda.toLowerCase().equals("s");
    }

    static Comida criarComida() {
        Comida novaComida = MenuFabricaComida();
        

        if (novaComida != null) {
            System.out.print("Deseja quantos adicionais? ");
            int adicionais = Integer.parseInt(teclado.nextLine());
            novaComida.adicionarIngredientes(adicionais);
        }

        return novaComida;
    }

    static Cliente localizarCliente() {
        int idCli;
        String nomeCliente;
        cabecalho();
        System.out.print("Digite o nome do cliente: ");
        nomeCliente = teclado.nextLine();
        return baseClientes.localizar(nomeCliente);
    }

    static Cliente cadastrarCliente() {
        String nomeCliente;
        Cliente novoCliente;
        cabecalho();
        System.out.print("Digite o nome do cliente: ");
        nomeCliente = teclado.nextLine();
        novoCliente = new Cliente(nomeCliente);
        baseClientes.cadastrar(nomeCliente, novoCliente);
        System.out.println("Cliente cadastrado:\n" + novoCliente);
        pausa();
        return novoCliente;
    }

    static void registrarPedido(Pedido pedido, Cliente cliente) {
        if (pedido != null && cliente != null) {
            
            cliente.registrarPedido(pedido);
            todosOsPedidos.cadastrar(pedido.hashCode(),pedido);
            System.out.println("Pedido fechado: ");
            System.out.println(pedido.toString());
            System.out.println("Registrado para " + cliente);
            System.out.println("Valor pago: R$" + cliente.valorAPagar(pedido));
            pausa();
        }
    }


    static void gerarClientes() {
        String[] nomes = {"Everson", "Renzo", "Rodrigo", "Jemerson", "Guilherme",
                           "Otávio", "Givanildo", "Matías", "Gustavo",
                            "Paulinho", "Alan" };
        for(String nome : nomes) {
            Cliente novo = new Cliente(nome);   
            baseClientes.cadastrar(nome, novo);
        }
    }
    
    public static void main(String[] args) {
        baseClientes = new BaseDados<>(100);
        todosOsPedidos = new BaseDados<>(1000);
        config();
        teclado = new Scanner(System.in);
        
        int opcao;
        Pedido pedidoAtual;
        Cliente clienteAtual;
        do {
            opcao = MenuPrincipal();
            switch (opcao) {
                case 1:
                    clienteAtual = localizarCliente();
                    if (clienteAtual == null)
                        clienteAtual = cadastrarCliente();

                    pedidoAtual = criarPedido();
                    registrarPedido(pedidoAtual, clienteAtual);

                    break;
                case 2:
                    relatorioTotalVendido();
                    break;
                case 3:
                    clienteAtual = cadastrarCliente();
                    pedidoAtual = criarPedido();
                    registrarPedido(pedidoAtual, clienteAtual);
                    break;
                case 4:
                    acaoClientes();
                    break;
                case 5:
                    cabecalho();
                    todosOsPedidos.ordenar(escolherComparadorPedido());
                    System.out.println(todosOsPedidos.relatorio());
                    pausa();    
                    break;
                case 6:
                    Comida comida = MenuFabricaComida();
                    System.out.println(comida);
                    pausa();
                    break;
            }
        } while (opcao != 0);
        System.out.println("FLW VLW VLT SMP ABS 🤙");
        teclado.close();
    }
}
