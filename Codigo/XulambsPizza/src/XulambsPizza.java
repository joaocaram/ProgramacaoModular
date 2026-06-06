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

public class XulambsPizza {

    static List<Pedido> pedidos;    // lista com todos os pedidos. A melhorar no futuro. 

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
        IO.println("XULAMBS PIZZA v0.6\n=============");
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

    //#region menus
    static int exibirMenu() {
        cabecalho();
        IO.println("1 - Abrir Pedido");
        IO.println("2 - Alterar Pedido");
        IO.println("3 - Fechar Pedido");
        IO.println("4 - Relatório de Pedido");
        IO.println("0 - Finalizar");
        return lerInteiro("Digite sua escolha: ");
    
    }

    static int menuProdutos() {
        cabecalho();
        IO.println("1 - Pizzas");
        IO.println("2 - Bebidas");
        IO.println("3 - Sobremesas");
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
            case 1-> comprarPizza();
            case 2-> comprarBebida();
            case 3-> comprarSobremesa();
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

    static Object localizar(int numero){
        Object localizado = null;
        for (int i = 0; localizado == null && i<pedidos.size(); i++) {
            Object candidato = pedidos.get(i);
            if(candidato.hashCode() == numero)
                localizado = candidato;
        }
        return localizado;
    }
    
    static void alterarPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Incluir itens em um pedido.");
        int numPedido = lerInteiro(("Número do pedido: "));
        Pedido pedido = (Pedido)localizar(numPedido);
        if(pedido != null ){
            try {
                pedido.adicionar(comprarPizza());    
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
        int numPedido = lerInteiro("Número do pedido: ");
        Pedido pedido = (Pedido)localizar(numPedido);
        if(pedido != null ){
            try {
                pedido.fecharPedido();    
            } catch (IllegalStateException ex) {
                IO.print(ex.getMessage());
                return;
            }
        }
        imprimir(pedido, resposta);
    }

    static void relatorioPedido(){
        cabecalho();
        String resposta = "Pedido não encontrado";
        IO.println("Relatório de um pedido.");
        int numPedido = lerInteiro(IO.readln("Número do pedido: "));
        Pedido pedido = (Pedido)localizar(numPedido);
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
    static Pizza comprarPizza() {
        cabecalho();
        IO.println("Comprando uma nova pizza:");
        int adicionais = lerInteiro("Quantos adicionais você deseja? (máx. 8): ");
        Pizza novaPizza = new Pizza(adicionais);
        novaPizza.adicionarBorda(escolherBorda());
        imprimir(novaPizza, "Pizza não pode ser criada");
       
        return novaPizza;
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
    //#endregion
}