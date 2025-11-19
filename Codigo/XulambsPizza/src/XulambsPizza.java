import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


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
 * The above copyright notice and this permission notice shall be included in
 * all
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

public class XulambsPizza {
    static Scanner teclado;
    static BaseDados<Cliente> clientes;
    static BaseDados<Pedido> todosOsPedidos;

    //#region utilidades
    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println("XULAMBS PIZZA v0.8\n=============");
    }

    static int lerInteiro(String mensagem){
        int opcao;
        System.out.print(mensagem+": ");
        try {
            opcao = Integer.parseInt(teclado.nextLine());   
        } catch (NumberFormatException e) {
            System.err.println("Opção inválida.");
            opcao = -1;
        }
         return opcao;  
    }
    //#endregion

    //#region gerador aleatório
    static void gerarClientes() {
        Cliente novo = new Cliente("Anônimo");
        clientes.put(novo);
        try {
            Path caminho = Path.of("medalhistas.txt");
            List<String> nomes = Files.readAllLines(caminho, Charset.forName("UTF-8"));
            for (String nome : nomes) {
                novo = new Cliente(nome);
                clientes.put(novo);
            }
        } catch (IOException exception) {
            System.out.println("Problema na leitura do arquivo. Sistema iniciado somente com cliente anônimo.");
            pausa();
        }
    }

    static void gerarPedidos() {
        Random aleat = new Random(42);
        int quantos = clientes.size() * 16;
        // pedidos = new Pedido[quantos*2];
        Pedido pedido;
        IProduto comida = null;
        for (int i = 0; i < quantos; i++) {
            int tipo = aleat.nextInt(10_000) % 3;
            if (tipo <= 1)
                pedido = new PedidoLocal();
            else
                pedido = new PedidoEntrega(aleat.nextInt(10) + 1);

            int quantComidas = aleat.nextInt(1000);
            if (quantComidas > 950)
                quantComidas = 4;
            else if (quantComidas > 750)
                quantComidas = 3;
            else if (quantComidas > 500)
                quantComidas = 2;
            else
                quantComidas = 1;

            for (int j = 0; j < quantComidas; j++) {
                tipo = aleat.nextInt(30_000) % 5;
                switch (tipo) {
                    case 0:
                    case 3:
                    case 4:
                            int quantAdic = aleat.nextInt(6);
                            int borda = aleat.nextInt(EBorda.values().length);
                            comida = new Pizza(quantAdic);    
                            ((Pizza)comida).adicionarBorda(EBorda.values()[borda]);
                        break;
                    case 1:
                            int bebida = aleat.nextInt(EBebida.values().length);
                            comida = EBebida.values()[bebida];
                        break;
                    case 2:
                            int sobre = aleat.nextInt(ESobremesa.values().length);
                            comida = ESobremesa.values()[sobre];
                        break;
                }
                try {
                    pedido.adicionar(comida);
                } catch (IllegalStateException e) {
                    System.err.println("Comida inválida e pedido sem comida");
                }
            }
            Cliente quem = clientes.get(aleat.nextInt(clientes.size())+1);
            if (quem == null)
                quem = clientes.get(1);
            pedido.fecharPedido();
            quem.registrarPedido(pedido);
            todosOsPedidos.put(pedido);
        }
    }

    
    static void config() {
        gerarClientes();
        gerarPedidos();
    }

    //#endregion
      
    //#region menus
    static int exibirMenu() {
        cabecalho();
        System.out.println("======= V E N D A S ======");
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Alterar Pedido");
        System.out.println("3 - Relatório de Pedido");
        System.out.println("4 - Encerrar Pedido");
        System.out.println("5 - Valor do último Pedido");
        System.out.println("======= C L I E N T E S ======");
        System.out.println("6 - Atualizar Fidelidades");
        System.out.println("7 - Relatório de Cliente");
        System.out.println("8 - Relatório resumido dos clientes");
        System.out.println("9 - Relatório ordenado dos clientes (padrão)");
        System.out.println("10 - Relatório ordenado dos clientes (escolha)");
        System.out.println("11 - Total gasto pelos clientes");
        System.out.println("======= P E D I D O S ======");
        System.out.println("12 - Relatório resumido dos Pedidos");
        System.out.println("13 - Relatório ordenado dos pedidos (padrão)");
        System.out.println("14 - Relatório ordenado dos pedidos (escolha)");
        System.out.println("15 - Valor médio dos pedidos");

        System.out.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha");
    }

    static int menuTipoPedido() {
        cabecalho();
        System.out.println("1 - Pedido Local");
        System.out.println("2 - Pedido para Entrega");
        System.out.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha");
    }

    static int menuAdicionarNoPedido() {
        cabecalho();
        System.out.println("1 - Pizzas");
        System.out.println("2 - Bebidas");
        System.out.println("3 - Sobremesas");
        System.out.println("0 - Sair");
        return lerInteiro("Digite sua escolha");
}
    //#endregion

    //#region escolha enumeradores
    static void addBordaPizza(Pizza pizza){
        System.out.println("\nEscolha sua borda: ");
        EBorda[] bordas = EBorda.values();
        for (int i = 0; i < bordas.length; i++) {
            System.out.println((i+1)+ " - "+bordas[i].descricao());
        }
        int opcao = lerInteiro("Entre sua opção");
        pizza.adicionarBorda(bordas[opcao-1]);
    }

    static EBebida comprarBebida(){
        System.out.println("\nEscolha sua bebida: ");
        EBebida[] bebidas = EBebida.values();
        for (int i = 0; i < bebidas.length; i++) {
            System.out.println((i+1)+ " - "+bebidas[i]);
        }
        int opcao = lerInteiro("Entre sua opção");
        return bebidas[opcao-1];
    }

    static ESobremesa comprarSobremesa(){
        System.out.println("\nEscolha sua sobremesa: ");
        ESobremesa[] sobremesas = ESobremesa.values();
        for (int i = 0; i < sobremesas.length; i++) {
            System.out.println((i+1)+ " - "+sobremesas[i]);
        }
        int opcao = lerInteiro("Entre sua opção");
        return (sobremesas[opcao-1]);
    }
    //#endregion
    

    static void abrirPedido() {
        cabecalho();
        Pedido novoPedido = escolherTipoPedido();
        adicionarProdutos(novoPedido);
        mostrarPedido(novoPedido);
        pausa();
        Cliente quem = localizarCliente();
        quem.registrarPedido(novoPedido);
        todosOsPedidos.put(novoPedido);
        System.out.println("\nPedido registrado para "+quem);
    }

    private static Pedido escolherTipoPedido() {
        int opcao = menuTipoPedido();
        switch (opcao) {
            case 1:  return new PedidoLocal();
            case 2:  return criarPedidoEntrega();                
        }
        return null;
    }

    private static Pedido criarPedidoEntrega() {
        System.out.print("Qual a distância?");
        double dist = Double.parseDouble(teclado.nextLine());
        return new PedidoEntrega(dist);
    }

    private static void valorDoUltimoPedido(){
        cabecalho();
        // Pedido ultimo = todosOsPedidos.getLast();
        // System.out.println("Último pedido: R$ "+ultimo.precoAPagar());
    }
    
    static void relatorioPedido() {
        cabecalho();
        Pedido pedido = localizarPedido();
        if (pedido != null)
            mostrarPedido(pedido);
        else
            System.out.println("Pedido não existente.");
    }

    static void relatorioCliente() {
        cabecalho();
        Cliente clie = localizarCliente();
        if (clie != null){
            System.out.println(clie);
            System.out.print("Deseja ver o relatório completo (S/N)? ");
            String completo = teclado.nextLine().toUpperCase();
            if(completo.equals("S")){
                limparTela();
                System.out.println(clie.resumoPedidos());
            }
        }
        else
            System.out.println("Pedido não existente.");
    }

    
    static Pedido localizarPedido() {
        cabecalho();
        int id;
        System.out.println("Localizando um pedido.");
        id = lerInteiro("ID do pedido");
        return todosOsPedidos.get(id);
    }

    static Cliente localizarCliente() {
        cabecalho();
        int id;
        System.out.println("Localizando um cliente.");
        id = lerInteiro("ID do cliente");
        return clientes.get(id);
    }

    static void alterarPedido(){
        Pedido pedido = localizarPedido();
        if (pedido != null) {
            adicionarProdutos(pedido);
            mostrarPedido(pedido);
        } else
            System.out.println("Pedido não existente.");
    }

    private static IProduto escolherProduto(){
        int opcao = menuAdicionarNoPedido();
        IProduto produto = null;
        switch (opcao) {
            case 1 -> produto = comprarPizza();
            case 2 -> produto = comprarBebida();
            case 3 -> produto = comprarSobremesa();
        }
        return produto;
    }
   
    private static void adicionarProdutos(Pedido procurado) {
        String escolha = "n";
        do {
            mostrarPedido(procurado);
            IProduto novoProduto = escolherProduto();
            procurado.adicionar(novoProduto);
            System.out.print("\nDeseja algo mais? (s/n) ");
            escolha = teclado.nextLine();
        } while (escolha.toLowerCase().equals("s"));
    }

    static void encerrarPedido() {
        Pedido pedido = localizarPedido();
        try {
            pedido.fecharPedido();
            System.out.println("Pedido encerrado: ");
            System.out.println(pedido.toString());
        } catch (IllegalStateException excecao) {
            System.out.println(excecao.getMessage());
        }catch (NullPointerException nulo){
            System.out.println("Pedido não existe.");
            encerrarPedido();
        }
    }

    static Pizza comprarPizza() {
        System.out.println("Comprando uma nova pizza:");
        Pizza novaPizza = new Pizza();
        addBordaPizza(novaPizza);
        escolherIngredientes(novaPizza);
        mostrarNota(novaPizza);
        return novaPizza;
    }

    static void escolherIngredientes(Pizza pizza) {
        int adicionais = lerInteiro("Quantidade de adicionais (máx 8)");
        try {
            pizza.adicionarIngredientes(adicionais);    
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        } catch (IllegalStateException ise){
           // TODO: handle exception     
        }
        
    }

    static void atualizarFidelidades() {
         cabecalho();
        
    }

    static void mostrarNota(Pizza pizza) {
        System.out.println("Você acabou de comprar: ");
        System.out.println(pizza.toString());
    }

    static void mostrarPedido(Pedido pedido) {
        System.out.println("Relatório do Pedido: ");
        System.out.println(pedido.toString());

    }

    
    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in);
        todosOsPedidos = new BaseDados<>(1000);
        clientes = new BaseDados<>(80);
        config();
        int opcao;
        opcao = exibirMenu();
        do {
            
            switch (opcao) {
                case 1 -> abrirPedido();
                case 2 -> alterarPedido();
                case 3 -> relatorioPedido();
                case 4 -> encerrarPedido();
                case 5 -> valorDoUltimoPedido();
                case 6 -> atualizarFidelidades();
                case 7 -> relatorioCliente();
                case 8 -> relatorioResumido(clientes);
          //      case 9 -> relatorioOrdenado(clientes, Cliente::compareTo);
          //      case 10 -> relatorioEscolhidoCliente();
          //      case 11 -> totalGastoPorClientes();
                case 12 -> relatorioResumido(todosOsPedidos);
          //      case 13 -> relatorioOrdenado(todosOsPedidos, Pedido::compareTo);
          //      case 14 -> relatorioEscolhidoPedido();
          //      case 15 -> valorMedioDosPedidos();
             
            }
            pausa();
            opcao = exibirMenu();
        } while (opcao != 0);

        teclado.close();
        System.out.println("FLW T+ VLW ABS.");
    }

    private static void relatorioResumido(BaseDados<?> base) {
        cabecalho();
        System.out.println(base.simpleReport());
    }

    

}
