
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


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

public class XulambsFoods {

    static Dados<Pedido> pedidos;    // lista com todos os pedidos. A melhorar no futuro. 
    static Dados<Cliente> clientes;
    
    static void config(){
        clientes = new Dados<>(GeradorClientes.gerarClientes());
        pedidos = new Dados<>(1000);
        // pedidos = GeradorPedidos.gerarPedidos(LocalDate.now().minusDays(100), LocalDate.now(), clientes, 12);
    }

    //#region utilidades
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
        IO.println("XULAMBS FOODS v0.9\n=============");
        IO.println("Pizzas vendidas hoje: "+Pizza.pizzasVendidas());
    }

    static int lerInteiro(String mensagem){
        try{
            return Integer.parseInt(IO.readln(mensagem));
        }catch(NumberFormatException nfe ){
            IO.println("Opção inválida. Digite um número válido.");
            return -1;
        }
    }
    //#endregion

    //#region gerência
    private static void menuGerencia() {
        cabecalho();
        int opcao = exibirMenuGerencia();
        switch (opcao) {
            case 1 -> relatorioClientes();
            case 2 -> relatorioPedidos();
            case 3 -> atualizarClientes();
        }    
    }

    private static<T> void totalDeQualquerCoisa(Function<T, Double> f,
                                            Dados<T> base){
        IO.println(base.totalizar(f));
    }

    private void pedidosPorData(){
        String strData = IO.readln("Qual a data para o filtro? ");
        LocalDate data = LocalDate.parse(strData);
        Predicate<Pedido> porData = ped -> ped.ehNaData(data);
        IO.println(pedidos.relatorioFiltrado(porData));
    }

    private static void totalGasto(){
        Function<Cliente, Double> f = 
            (cli) -> cli.totalGasto();  
        IO.println(clientes.totalizar(f));
    }

    private static void totalPedidos(){
        Function<Pedido, Double> f = 
            (ped) -> ped.precoAPagar();
        IO.println(pedidos.totalizar(f));
    }

    private static void atualizarClientes() {
      Consumer<Cliente> cons = (cli) -> cli.verificarCategoria();
      clientes.processar(cons);
    }

    private static<T> void relatorioOrdenado(Dados<T> dados, 
                                             Comparator<T> comp){
        IO.println(dados.relatorioOrdenado(comp));
    }


    private static void relatorioClientes() {
        cabecalho();
        relatorioOrdenado(clientes, 
                         (cli1, cli2) -> cli1.hashCode() - cli2.hashCode());
    }

    private static void relatorioPedidos() {
        cabecalho();
        IO.println("Relatório pedidos:");
        IO.println(pedidos.relatorioOrdenado(Pedido::compareTo));
    }
    //#endregion

    //#region menus
    static int exibirMenu() {
        cabecalho();
        IO.println("1 - Abrir Pedido");
        IO.println("2 - Alterar Pedido");
        IO.println("3 - Fechar Pedido");
        IO.println("4 - Relatório de Pedido");
        IO.println("5 - Gerência");
        IO.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha: ");
    
    }

    static int exibirMenuGerencia() {
        cabecalho();
        IO.println("1 - Todos os clientes");
        IO.println("2 - Todos os pedidos");
        IO.println("3 - Atualizar clientes");
        IO.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha: ");
    
    }

    static int menuProdutos() {
        cabecalho();
        IO.println("1 - Pizzas");
        IO.println("2 - Sanduiches");
        IO.println("3 - Bebidas");
        IO.println("4 - Sobremesas");
        return lerInteiro("Digite sua escolha: ");
    
    }

    //#endregion

    //#region pedido
    static void abrirPedido(){
        cabecalho();
        Pedido novo = escolherTipoPedido();
        if(novo!=null){
            String maisItens = null;
            do{
                try {
                    IProduto item = comprarProduto();
                    novo.adicionar(item);
                    pedidos.add(novo);
                    imprimir(novo, "");        
                } catch (IllegalStateException | IllegalArgumentException ex) {
                    IO.println(ex.getMessage());
                } finally{
                    maisItens = IO.readln("Algo mais(s/n)? ");
                }
            }while(maisItens.toLowerCase().equals("s"));
        }
    }
        

    private static IProduto comprarProduto() {
        int tipo = menuProdutos();
        return switch(tipo){
            case 1, 2-> comprarPersonalizavel(tipo);
            
            case 3-> comprarBebida();
            case 4-> comprarSobremesa();
            default -> null;
        };
    }

    static Pedido escolherTipoPedido(){
        cabecalho();
        IO.println("Escolha o tipo de pedido:");
        IO.println("1 - Pedido local");
        IO.println("2 - Pedido para entrega");
        int tipo = lerInteiro("Digite sua opção: ");
        return switch(tipo){
            case 1 ->  new PedidoLocal();
            case 2 -> criarPedidoEntrega();
            default -> null;
        };
    }

    static Pedido criarPedidoEntrega(){
        double dist = Double.parseDouble(
                    IO.readln("Pedido para entrega. Distância: "));
        try {
            return new PedidoEntrega(dist);    
        } catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
            return null;
        }
        
    }


    
    static void alterarPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Incluir itens em um pedido.");
        int numPedido = lerInteiro(("Número do pedido: "));
        Pedido pedido = pedidos.localizar(numPedido);
        if(pedido != null ){
            try {
                pedido.adicionar(comprarProduto());    
            } catch (PedidoFechadoException ex) {
                IO.println(ex.getMessage());
                return;
            }catch (IllegalArgumentException ex) {
                IO.println(ex.getMessage());
                alterarPedido();
            }
        }
        imprimir(pedido, resposta);
    }

     static void fecharPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Fechar um pedido.");
        int numPedido = Integer.parseInt(IO.readln("Número do pedido: "));
        Pedido pedido = pedidos.localizar(numPedido);
        int numCliente = Integer.parseInt(IO.readln("ID do cliente: "));
        Cliente cliente = clientes.localizar(numCliente);
        
        if(pedido != null && cliente !=null){
            try {
                cliente.registrarPedido(pedido);
                pedido.fecharPedido();
                String registro = (String.format("%s\nPedido %d registrado para %s\n", pedido, numPedido, cliente.getNome()));
                imprimir(registro, resposta);
            } catch (IllegalStateException ex) {
                IO.print(ex.getMessage());
                return;
            }
        }
    }

    static void relatorioPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Relatório de um pedido.");
        int numPedido = lerInteiro("Número do pedido: ");
        Pedido pedido = pedidos.localizar(numPedido);
        imprimir(pedido, resposta);
    }
    //#endregion

    static void imprimir(Object objeto, String padrao){
        if(objeto!=null){
            cabecalho();
            IO.println(objeto);
        }
            
        else
            IO.println(padrao);
    }
    //#region pizza
    static IProduto comprarPersonalizavel(int tipo){
        cabecalho();
        
        IPersonalizavel item = switch (tipo) {
            case 1 -> comprarPizza();
            case 2 -> comprarSanduiche();
            default -> null;
        };
        IO.println("Comprando "+item.getNome());
        int adicionais = Integer.parseInt(IO.readln("Quantos adicionais você deseja? (máx. "+item.maxIngredientes()+"): "));
        if(item != null){
            item.adicionarIngredientes(adicionais);
        }
        imprimir(item, "Item não pôde ser criado");
        return (IProduto)item;
    }

    static IPersonalizavel comprarPizza() {
        Pizza novaPizza = new Pizza();
        novaPizza.adicionarBorda(escolherBorda());
        
        return novaPizza;
    }

    static IPersonalizavel comprarSanduiche() {
        Sanduiche novoSanduiche = new Sanduiche();
        String combo = IO.readln("Será combo com fritas (s/n)? ").toUpperCase();
        if(combo.equals("S"))
            novoSanduiche.setCombo(true);
        return novoSanduiche;
    }

    private static EBorda escolherBorda() {
        return
            (EBorda)escolherEnum(EBorda.values(), "Escolha uma borda: ");
    }

    private static IProduto comprarSobremesa(){
        ESobremesa sobremesa = (ESobremesa)escolherEnum(ESobremesa.values(), "Escolha sua sobremesa: ");
        return new Sobremesa(sobremesa);
    }

    private static IProduto comprarBebida(){
        EBebida bebida = (EBebida)escolherEnum(EBebida.values(), "Escolha sua bebida: ");
        return new Bebida(bebida);
    }

    private static Enum escolherEnum(Enum[] valores, String msg) {
        IO.println(msg);
        int i = 1;
        for(Enum valor : valores){
            IO.println(String.format("%d - %s", 
                                    i, valor.toString()));
            i++;
        }    
        int opcao = lerInteiro("Digite sua opção: ");
        try {
            return valores[opcao-1];    
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        
    }

    
    //#endregion

    //#region app
    public static void main(String[] args) throws Exception {
        config();
        int opcao = -1;
        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1 -> abrirPedido();    
                case 2 -> alterarPedido();    
                case 3 -> fecharPedido();    
                case 4 -> relatorioPedido();    
                case 5 -> menuGerencia();
            }
            pausa();
        } while (opcao != 0);        
        IO.println("FLW T+ VLW ABS.");
    }
 
    //#endregion
}