
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.naming.OperationNotSupportedException;

/**
 * MIT License
 *
 * Copyright(c) 2022-25 João Caram <caram@pucminas.br>
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

public class XulambsFoods {

    static final int MAX_PEDIDOS = 10_000;
    static final int TAM_BASE = 100;
    static final NumberFormat moeda = NumberFormat.getCurrencyInstance();
    static BaseDados<Pedido> pedidos = new BaseDados<>(MAX_PEDIDOS);
    static BaseDados<Cliente> clientes = new BaseDados<>(TAM_BASE);
    static BaseDados<IFabrica<Comida>> fabricas = new BaseDados<>(10);

    static Map<LocalDate, List<Pedido>> pedidosPorDia = new HashMap<>();
    static int quantPedidos = 0;

    // #region utilidades
    static Scanner teclado;

    /**
     * Lê um inteiro, mostrando uma mensagem para o usuário
     * 
     * @param mensagem Mensagem a ser mostrada
     * @return O inteiro digitado pelo usuário, ou -1 em caso
     *         de leitura inválida.
     */
    static int lerInteiro(String mensagem) {
        System.out.print(mensagem + ": ");
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException nException) {
            return -1;
        }

    }

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
        System.out.println("XULAMBS FOODS v0.11\n================");
    }
    // #endregion

    // #region geradorAleatorio
    static void gerarClientes() {
        clientes.put(new Cliente(0, "Anônimo"));
        try {
            Path caminho = Path.of("medalhistas.txt");
            List<String> nomes = Files.readAllLines(caminho, Charset.forName("UTF-8"));
            int doc = 1;
            for (String nome : nomes) {
                Cliente novo = new Cliente(doc, nome);
                clientes.put(novo);
                doc++;
            }
        } catch (IOException exception) {
            System.out.println("Problema na leitura do arquivo. Sistema iniciado somente com cliente anônimo.");
            pausa();
        }
    }

    static void gerarPedidos() {
        Random aleat = new Random(42);
        int quantos = TAM_BASE * 16;
        // pedidos = new Pedido[quantos*2];
        Pedido pedido;
        Comida comida;
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
                tipo = aleat.nextInt(10_000) % 2;
                int quantAdic = aleat.nextInt(6);
                if (tipo == 0)
                    comida = new Pizza(quantAdic);
                else
                    comida = new Sanduiche(quantAdic);
                try {
                    pedido.adicionar(comida);
                } catch (OperationNotSupportedException e) {
                    System.err.println("Comida inválida e pedido sem comida");
                }
            }
            Cliente quem = clientes.get(aleat.nextInt(TAM_BASE));
            if (quem == null)
                quem = clientes.get(0);
            quem.registrarPedido(pedido);
            pedido.fecharPedido();
            armazenarPedido(pedido);
        }
    }

    @SuppressWarnings("unchecked")
    static void configFabricas(){
        try (Scanner arqConfig = new Scanner(new File("config.txt"))) {
        while (arqConfig.hasNextLine()) {
            String[] configuracoes = arqConfig.nextLine().split(";");
            try {
                IFabrica<Comida> fabrica = (IFabrica<Comida>)Class.forName(configuracoes[1])
                                                .getConstructor(null)
                                                .newInstance(null);
                fabricas.put(fabrica);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                System.err.println("Erro no arquivo de configuração. Corrija e reinicie o sistema.");
                
            }    
        }    
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de configuração não encontrado. Corrija e reinicie o sistema.");
        }
        
    }
    static void config() {
        gerarClientes();
        gerarPedidos();
        configFabricas();
        // fabricas.put(new FabricaPizza());
        // fabricas.put(new FabricaCombo());
        // fabricas.put(new FabricaSanduiche());
        // fabricas.put(new FabricaPizzaCheddar());
    }
    // #endregion

    static int exibirMenuPrincipal() {
        cabecalho();
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Alterar Pedido");
        System.out.println("3 - Relatório de Pedido");
        System.out.println("4 - Fechar Pedido");
        System.out.println("=============================");
        System.out.println("5 - Menu Gerente");
        System.out.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha");
    }

    static int exibirMenuGerente() {
        cabecalho();
        System.out.println("Funções Gerenciais");
        System.out.println("=================================");
        System.out.println("CLIENTES");
        System.out.println("1 - Atualizar programa de fidelidade");
        System.out.println("2 - Relatório de um cliente");
        System.out.println("3 - Relatório resumido dos clientes");
        System.out.println("4 - Relatório resumido ordenado dos clientes ");
        System.out.println("5 - Relatório de clientes com gasto mínimo");
        System.out.println("=================================");
        System.out.println("PEDIDOS");
        System.out.println("6 - Maior pedido do restaurante");
        System.out.println("7 - Pedidos de um dia");
        System.out.println("8 - Pedidos com um prato");
        System.out.println("9 - Pedidos com um valor mínimo");
        System.out.println("=================================");
        System.out.println("FINANCEIRO");
        System.out.println("10 - Gasto total dos clientes");
        System.out.println("11 - Gasto médio por cliente");
        System.out.println("12 - Gasto médio por pedido");
        System.out.println("13 - Arrecadação em um dia");

        System.out.println("\n0 - Voltar");
        return lerInteiro("Digite sua escolha");

    }

    static int exibirMenuPedido() {
        cabecalho();
        System.out.println("1 - Pedido Local");
        System.out.println("2 - Pedido para Entrega");

        return lerInteiro("Digite sua opção");
    }

    static int exibirMenuComidas() {
        cabecalho();
        System.out.println("1 - Pizza (padrão)");
        System.out.println("2 - Sanduíche");
        System.out.println("3 - Sanduíche combo");
        System.out.println("4 - Pizza com borda de cheddar");
        System.out.println("5 - Pizza com borda de chocolate");

        return lerInteiro("Digite sua opção");
    }

    static <T extends Comparable<T>> T localizarMenor(T[] dados, int limite) {
        T menor = dados[0];
        for (int i = 1; i < limite; i++) {
            if (dados[i].compareTo(menor) < 0)
                menor = dados[i];
        }
        return menor;
    }

    static <T extends Comparable<T>> T localizarMaior(T[] dados, int limite) {
        T maior = dados[0];
        for (int i = 1; i < limite; i++) {
            if (dados[i].compareTo(maior) > 0)
                maior = dados[i];
        }
        return maior;
    }

    static int exibirMenuIngredientes(Comida comida) {
        cabecalho();
        mostrar(comida, "Personalizar sua comida:");
        System.out.println("\n1 - Acrescentar ingredientes");
        System.out.println("2 - Retirar ingredientes");
        System.out.println("0 - Não quero alterar");
        return lerInteiro("Digite sua escolha");
    }

    static int exibirMenuBorda() {
        EBorda[] bordas = EBorda.values();
        System.out.println("\nEscolha o tipo de borda: ");
        for (int i = 0; i < bordas.length; i++) {
            System.out.printf("%d - %s\n", (i + 1), bordas[i].descricao());
        }
        return lerInteiro("Digite sua escolha");
    }

    static Comida escolherComida() {
        cabecalho();
        String[] cardapio = {"pizza", "sanduiche", "combo", "pizzacheddar", "pizzachoco"};
        System.out.println("Incluindo uma nova comida:");
        int opcao = exibirMenuComidas();
        String escolha = cardapio[opcao-1];
        Comida novaComida = fabricas.get(escolha.hashCode()).criar();
        escolherIngredientes(novaComida);
        mostrar(novaComida, "Incluído:");
        return novaComida;
    }

    static Comida comprarPizza() {
        Comida pizza = fabricas.get("pizza".hashCode()).criar();
       // escolherBorda(pizza);
        return pizza;
    }

    static Comida comprarSanduiche() {
        System.out.print("\nDeseja combo com fritas (S/N)? ");
        String opcao = teclado.nextLine().toLowerCase();
        boolean combo = (opcao.equals("s"));
        return new Sanduiche(combo);
    }

    static void escolherBorda(Pizza pizza) {
        cabecalho();
        mostrar(pizza, "Pizza atual:");
        int borda = exibirMenuBorda();
        pizza.adicionarBorda(EBorda.values()[borda - 1]);
    }

    static void escolherIngredientes(Comida comida) {
        int opcao = exibirMenuIngredientes(comida);
        while (opcao != 0) {
            int adicionais;
            switch (opcao) {
                case 1 -> {
                    adicionais = lerInteiro("Quantidade de adicionais");
                    try {
                        comida.adicionarIngredientes(adicionais);
                    } catch (IngredientesIncorretos e) {
                        int quantos = e.getQuantosIngredientes();
                        System.out.println("Ingredientes inválidos: " + quantos);
                        pausa();
                    }
                }
                case 2 -> {
                    adicionais = lerInteiro("Quantidade de adicionais");
                    try {
                        comida.retirarIngredientes(adicionais);
                    } catch (IngredientesIncorretos e) {
                        int quantos = e.getQuantosIngredientes();
                        System.out.println("Ingredientes inválidos: " + quantos);
                        pausa();
                    }
                }
                case -1 -> System.out.println("Opção inválida");
            }
            mostrar(comida, "Comprando: ");
            pausa();
            opcao = exibirMenuIngredientes(comida);
        }
    }

    static void mostrar(Object objeto, String mensagem) {
        System.out.println(mensagem);
        System.out.println(objeto);
    }

    static Pedido abrirPedido() {
        Pedido novoPedido = escolherTipoPedido();
        adicionarComidas(novoPedido);
        return novoPedido;
    }

    static Pedido escolherTipoPedido() {
        Pedido novo = null;
        int opcao = exibirMenuPedido();
        switch (opcao) {
            case 1 -> novo = criarPedidoLocal();
            case 2 -> novo = criarPedidoEntrega();

        }
        return novo;
    }

    static Pedido criarPedidoLocal() {
        return new PedidoLocal();
    }

    static Pedido criarPedidoEntrega() {
        cabecalho();
        System.out.println("Pedido para entrega.");
        System.out.print("Distância: ");
        double distancia = Double.parseDouble(teclado.nextLine());
        return new PedidoEntrega(distancia);
    }

    static void adicionarComidas(Pedido pedido) {
        String conf;
        do {
            Comida novaComida = escolherComida();
            try {
                pedido.adicionar(novaComida);
                System.out.print("\nIncluir outra comida (S/N)? ");
                conf = teclado.nextLine().toUpperCase();
            } catch (OperationNotSupportedException operException) {
                System.out.println("\n\nO PEDIDO JÁ ESTÁ FECHADO!!");
                pausa();
                conf = "N";
            } catch (NullPointerException npException) {
                System.out.println("\nComida inválida. Tente novamente");
                pausa();
                conf = "S";
            }

        } while (conf.equals("S"));
    }

    static void armazenarPedido(Pedido pedido) {
        LocalDate dataPedido = pedido.data();
        pedidos.put(pedido);
        if(!pedidosPorDia.containsKey(dataPedido)){
            List<Pedido> novaLista = new LinkedList<>();
            novaLista.add(pedido);
            pedidosPorDia.put(dataPedido, novaLista);
        }
        else{
            List<Pedido> listaDoDia = pedidosPorDia.get(dataPedido);
            listaDoDia.add(pedido);
        }
    }

    static Pedido localizarPedido() {
        cabecalho();
        System.out.println("Localizando um pedido");
        int numero = lerInteiro("Digite o número do pedido");
        Pedido localizado = pedidos.get(numero);
        return localizado;
    }

    static void alterarPedido() {
        Pedido pedidoParaAlteracao = localizarPedido();
        if (pedidoParaAlteracao != null) {
            adicionarComidas(pedidoParaAlteracao);
            mostrar(pedidoParaAlteracao, "Pedido localizado:");
        } else
            System.out.println("Pedido não encontrado");
    }

    static void relatorioDePedido() {
        Pedido localizado = localizarPedido();
        if (localizado != null)
            mostrar(localizado, "Pedido localizado");
        else
            System.out.println("Pedido não existente");
    }

    static void fecharPedido() {
        Pedido localizado = localizarPedido();
        if (localizado != null) {
            localizado.fecharPedido();
            mostrar(localizado, "Pedido localizado");
        } else
            System.out.println("Pedido não existente");
    }

    static <T> void exibirMaior(BaseDados<T> dados, String mensagem,
                                Comparator<T> comparacao) {
            cabecalho();
            System.out.println(mensagem);
            T dado = dados.max(comparacao);
            if(dado!=null)
                System.out.println(dado);
            else
                System.out.println("Não existe dado na base neste momento");
    }

    static void registrarPedidoParaCliente(Pedido pedido) {
        cabecalho();
        mostrar(pedido, "Pedido atual:");
        String mensagem = "Não foi encontrado cliente com este id. Registrado para cliente anônimo.";
        int id = lerInteiro("ID do cliente");
        Cliente cliente = clientes.get(id);
        if (cliente == null) {
            cliente = clientes.get(0);
        } else
            mensagem = String.format("Pedido registrado para %s", cliente.toString());
        cliente.registrarPedido(pedido);
        System.out.println(mensagem);
        mostrar(pedido, "");
        pausa();
    }

    static void criarERegistrarPedido() {
        Pedido novoPedido = abrirPedido();
        registrarPedidoParaCliente(novoPedido);
        armazenarPedido(novoPedido);
    }

    static void atualizarFidelidades() {
        cabecalho();
        System.out.println("Atualizando fidelidades...");
        clientes.update((cli) -> cli.atualizarCategoria());
    }

    static void mostrarCliente() {
        cabecalho();
        System.out.println("Mostrar situação do cliente.");
        int id = lerInteiro("Digite o ID do cliente");
        Cliente quem = clientes.get(id);
        System.out.println(quem);
    }

    static void relatorioDeClientes() {
        cabecalho();
        System.out.println("Relatório resumido de clientes: ");
        System.out.println(clientes.report());
    }

    static void relatorioOrdenadoDeClientes() {
        cabecalho();
        System.out.println("Relatório ordenado de clientes: ");
        Comparator<Cliente> comp = escolherComparador();
        System.out.println(clientes.sortedReport(comp));
    }

    static Comparator<Cliente> escolherComparador() {
        System.out.println("1 - Ordenação alfabética");
        System.out.println("2 - Ordenação por valor gasto");
        int opcao = lerInteiro("Digite sua opção");
        Comparator<Cliente> comp = null;
        switch (opcao) {
            case 1 -> comp = (cli1, cli2) -> cli1.toString().compareTo(cli2.toString());
            case 2 -> comp = (cli1, cli2) -> cli1.totalGasto() > cli2.totalGasto() ? 1 : -1;
        }
        return comp;
    }

    static void totalDeGastosClientes() {
        cabecalho();
        System.out.print("Total gasto no restaurante: ");
        Function<Cliente, Double> totalCliente = (cli) -> cli.totalGasto();
        double valor = clientes.aggregator(totalCliente);
        System.out.printf("R$ %,.2f\n", valor);
    }

    static void filtroDeClientesPorGasto() {
        cabecalho();
        System.out.println("Filtro de clientes por gasto");
        System.out.print("Gasto mínimo: ");
        double minimo = Double.parseDouble(teclado.nextLine());
        Predicate<Cliente> cond = (cliente) -> cliente.totalGasto() >= minimo
                && cliente.hashCode() != 0;
        System.out.println(clientes.filteredReport(cond));
    }

    static <T> void gastoMedio(BaseDados<T> basedados, Function<T, Double> funcao, String nome) {
        //TODO
    }

    static void arrecadacaoDeUmDia() {
        //TODO
    }

    static void pedidosDeUmDia(){
        //TODO
    }

    static void pedidosComUmPrato(){
        cabecalho();
        System.out.println("Pedidos com um prato escolhido:");
        System.out.print("Digite o nome do prato: ");
        String nomePrato = teclado.nextLine().toLowerCase();
        System.out.println(    
            pedidos.filteredReport((ped -> ped.toString().toLowerCase().contains(nomePrato)))
        );
     }

    static void pedidosComValorMinimo(){
        //TODO
    }

    static void  dataComAMaiorArrecadacao(){
        Comparator<List<Pedido>> compListaPorValor =
            (lista1, lista2) -> 
                lista1.stream().mapToDouble(ped -> ped.precoAPagar()).sum() >
                lista2.stream().mapToDouble(ped -> ped.precoAPagar()).sum() ?
                        1 : -1;

        List<Pedido> maiorDia = pedidosPorDia.values().stream()
                                    .max(compListaPorValor)
                                    .orElse(new LinkedList<>());
        System.out.println("Data com a maior arrecadação: "
                        + maiorDia.getFirst().data());
                                    ;
    }
    static void modoGerente() {
        int opcao = -1;
        do {
            opcao = exibirMenuGerente();
            switch (opcao) {
                case 1 -> atualizarFidelidades();
                case 2 -> mostrarCliente();
                case 3 -> relatorioDeClientes();
                case 4 -> relatorioOrdenadoDeClientes();
                case 5 -> filtroDeClientesPorGasto();
                case 6 -> exibirMaior(pedidos, "Pedido mais caro:",
                                        Pedido::compareTo);
                case 7 -> pedidosDeUmDia();
                case 8 -> pedidosComUmPrato();
                case 9 -> pedidosComValorMinimo();
                case 10 -> totalDeGastosClientes();
                case 11 -> gastoMedio(clientes, cli -> cli.totalGasto(), "clientes");
                case 12 -> gastoMedio(pedidos, ped -> ped.precoAPagar(), "pedidos");
                case 13 -> arrecadacaoDeUmDia();
                case 14 -> dataComAMaiorArrecadacao();
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Retornando ao menu principal.");
    }

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        config();
        int opcao = -1;
        do {
            opcao = exibirMenuPrincipal();
            switch (opcao) {
                case 1 -> criarERegistrarPedido();
                case 2 -> alterarPedido();
                case 3 -> relatorioDePedido();
                case 4 -> fecharPedido();
                case 5 -> modoGerente();

                case 0 -> System.out.println("FLW VLW OBG VLT SMP.");
            }
            pausa();
        } while (opcao != 0);
        teclado.close();
    }
}
