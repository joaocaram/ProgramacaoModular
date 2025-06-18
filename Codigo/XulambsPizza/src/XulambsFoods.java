
import java.security.InvalidParameterException;
import java.util.Scanner;
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

    static final int MAX_PEDIDOS = 100;
    static Pedido[] pedidos = new Pedido[MAX_PEDIDOS];
    static int quantPedidos = 0;

    // #region utilidades
    static Scanner teclado;

    /**
     * Lê um inteiro, mostrando uma mensagem para o usuário
     * @param mensagem Mensagem a ser mostrada
     * @return O inteiro digitado pelo usuário, ou -1 em caso
     * de leitura inválida. 
     */
    static int lerInteiro(String mensagem) {
        System.out.print(mensagem + ": ");
        try {
            return Integer.parseInt(teclado.nextLine());    
        } catch (NumberFormatException nException) {
            throw new InvalidParameterException();
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
        System.out.println("XULAMBS FOODS v0.6\n================");
    }
    // #endregion

    static int exibirMenuPrincipal() {
        cabecalho();
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Acrescentar Comidas a um Pedido");
        System.out.println("3 - Verificar um Pedido");
        System.out.println("4 - Fechar um Pedido");
        System.out.println("0 - Finalizar");
       
        return lerInteiro("Digite sua opção");
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
        
        return lerInteiro("Digite sua opção");
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
            System.out.printf("%d - %s\n", (i+1), bordas[i].descricao());
        }
        return lerInteiro("Digite sua escolha");
    }

    static Comida escolherComida() {
        cabecalho();
        System.out.println("Incluindo uma nova comida:");
        int opcao = exibirMenuComidas();
        Comida novaComida = null;
        switch (opcao) {
            case 1 -> novaComida = comprarPizza();
            case 2 -> novaComida = comprarSanduiche();
            default -> novaComida = comprarPizza();    
        }
        escolherIngredientes(novaComida);
        mostrar(novaComida, "Incluído:");
        return novaComida;
    }

    static Comida comprarPizza(){
        Pizza pizza = new Pizza();
        escolherBorda(pizza);
        return pizza;
    }

    static Comida comprarSanduiche(){
        System.out.print("\nDeseja combo com fritas (S/N)? ");
        String opcao = teclado.nextLine().toLowerCase();
        boolean combo = (opcao.equals("s"));
        return new Sanduiche(combo);
    }

    static void escolherBorda(Pizza pizza){
        cabecalho();
        mostrar(pizza, "Pizza atual:");
        int borda = exibirMenuBorda();
        pizza.adicionarBorda(EBorda.values()[borda-1]);
    }

    static void escolherIngredientes(Comida comida) {
        int opcao = exibirMenuIngredientes(comida);
        while (opcao != 0) {
            switch (opcao) {
                case -1 -> System.out.println("Opção inválida");
                case 1 -> adicionarIngredientes(comida);
                case 2 -> retirarIngredientes(comida);
            }
            mostrar(comida, "Comprando: ");
            pausa();
            opcao = exibirMenuIngredientes(comida);
        }
    }

    static void adicionarIngredientes(Comida comida){
        int adicionais = lerInteiro("Quantidade de adicionais para acrescentar:");
        try{
             comida.adicionarIngredientes(adicionais); 
        }catch (IngredientesEmExcessoException e) {
             int quantos = e.getQuantosIngredientes();    
             System.out.println("Ingredientes em excesso: "+quantos);
             pausa();
        }
    }

    static void retirarIngredientes(Comida comida){
        int adicionais = lerInteiro("Quantidade de adicionais para retirar:");
        try{
             comida.retirarIngredientes(adicionais); 
        }catch (IngredientesEmExcessoException e) {
             int quantos = e.getQuantosIngredientes();    
             System.out.println("Ingredientes em excesso: "+quantos);
             pausa();
        }

    }
    static void mostrar(Object objeto, String mensagem){
        System.out.println(mensagem);
        System.out.println(objeto);
    }
   
    static Pedido abrirPedido() {
            Pedido novoPedido = escolherTipoPedido();
            adicionarComidas(novoPedido);
            return novoPedido;
    }

    static Pedido escolherTipoPedido(){
        Pedido novo = null;
        int opcao = exibirMenuPedido();
        switch (opcao) {
            case 1 -> novo = criarPedidoLocal();
            case 2 -> novo = criarPedidoEntrega();
        }
        return novo;
    }

    static Pedido criarPedidoLocal(){
        return new PedidoLocal();
    }

    static Pedido criarPedidoEntrega(){
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
            } catch(NullPointerException npException){
                System.out.println("\nComida inválida. Tente novamente");
                pausa();
                conf="S";
            }
            
        } while (conf.equals("S"));
    }

    static void armazenarPedido(Pedido pedido) {
        if(quantPedidos < MAX_PEDIDOS) {
            pedidos[quantPedidos] = pedido;
            quantPedidos++;
        }
    }

    static Pedido localizarPedido() {
        cabecalho();
        System.out.println("Localizando um pedido");
        int numero = lerInteiro("Digite o número do pedido");
        Pedido localizado = null;
        
        for (int i = 0; i < quantPedidos && localizado == null; i++) {
            if (pedidos[i].hashCode() == numero)
                localizado = pedidos[i];
        }
        return localizado;
    }

    static void alterarPedido() {
        Pedido pedidoParaAlteracao = localizarPedido();
        if (pedidoParaAlteracao != null) {
            adicionarComidas(pedidoParaAlteracao);
            mostrar(pedidoParaAlteracao, "Pedido localizado:");
        }
        else
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
        if (localizado != null){
            localizado.fecharPedido();
            mostrar(localizado, "Pedido localizado");
        }
        else
            System.out.println("Pedido não existente");
    }
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        int opcao = -1;
            do {
                opcao = exibirMenuPrincipal();
                switch (opcao) {
                    case 1:
                        Pedido novoPedido = abrirPedido();
                        mostrar(novoPedido, "Novo pedido:");
                        armazenarPedido(novoPedido);
                        break;
                    case 2:
                        alterarPedido();
                        break;
                    case 3:
                        relatorioDePedido();
                        break;
                    case 4:
                        fecharPedido();
                        break;
                    case 0: System.out.println("FLW VLW OBG VLT SMP.");
                        break;
                }
                pausa();
            } while (opcao != 0);
        teclado.close();
    }
}
