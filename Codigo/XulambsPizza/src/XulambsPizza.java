import java.io.Console;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("XULAMBS PIZZA v0.2\n=============");
    }

    static int exibirMenu() {
        cabecalho();

        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Alterar Pedido");
        System.out.println("3 - Relatório de Pedido");
        System.out.println("4 - Encerrar Pedido");
        System.out.println("0 - Finalizar");
        System.out.print("Digite sua escolha: ");

        return Integer.parseInt(teclado.nextLine());
    }

    static void abrirPedido(List<Pedido> todosOsPedidos) {
        cabecalho();
        Pedido novoPedido = new Pedido();
        adicionarPizzas(novoPedido);
        mostrarPedido(novoPedido);
        todosOsPedidos.add(novoPedido);
    }

    static void relatorioPedido(List<Pedido> todosOsPedidos) {
        cabecalho();
        Pedido pedido = localizarPedido(todosOsPedidos);
        if (pedido != null)
            mostrarPedido(pedido);
        else
            System.out.println("Pedido não existente.");
    }

    static Pedido localizarPedido(List<Pedido> pedidos) {
        cabecalho();
        int id;
        System.out.println("Localizando um pedido.");
        System.out.print("ID do pedido: ");
        id = Integer.parseInt(teclado.nextLine());

        for (Pedido ped : pedidos) {
            if (ped.relatorio().contains("Pedido " + String.format("%02d", id)))
                return ped;
        }
        return null;
    }

    static void alterarPedido(List<Pedido> pedidos){
        Pedido pedido = localizarPedido(pedidos);
        if (pedido != null) {
            adicionarPizzas(pedido);
            mostrarPedido(pedido);
        } else
            System.out.println("Pedido não existente.");
    }

    private static void adicionarPizzas(Pedido procurado) {
        String escolha = "n";
        do {
            mostrarPedido(procurado);
            Pizza novaPizza = comprarPizza();
            procurado.adicionar(novaPizza);
            System.out.print("\nDeseja outra pizza? (s/n) ");
            escolha = teclado.nextLine();
        } while (escolha.toLowerCase().equals("s"));
    }

    static void encerrarPedido(List<Pedido> todosOsPedidos) {
        Pedido pedido = localizarPedido(todosOsPedidos);
        if (pedido != null) {
            pedido.fecharPedido();
            System.out.println("Pedido encerrado: ");
            System.out.println(pedido.relatorio());
        } else
            System.out.println("Pedido não existente.");
    }

    static Pizza comprarPizza() {
        System.out.println("Comprando uma nova pizza:");
        Pizza novaPizza = new Pizza();
        escolherBorda(novaPizza);
        escolherIngredientes(novaPizza);
        mostrarNota(novaPizza);
        return novaPizza;
    }

    static void escolherIngredientes(Pizza pizza) {
        System.out.print("Quantos adicionais você deseja? (máx. 8): ");
        int adicionais = Integer.parseInt(teclado.nextLine());
        pizza.adicionarIngredientes(adicionais);
    }

    static void escolherBorda(Pizza pizza){
        EBorda[] bordas = EBorda.values();
        System.out.println("Escolha sua borda:");
        for (int i = 0; i < bordas.length; i++) {
            System.out.println((i+1)+" - "+bordas[i].descricao());
        }
        System.out.print("Sua escolha: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        EBorda escolhida = bordas[opcao-1];
        pizza.adicionarBorda(escolhida);
    }

    static void mostrarNota(Pizza pizza) {
        System.out.println("Você acabou de comprar: ");
        System.out.println(pizza.notaDeCompra());
    }

    static void mostrarPedido(Pedido pedido) {
        System.out.println("Relatório do Pedido: ");
        System.out.println(pedido.relatorio());

    }

    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in);
        List<Pedido> todosOsPedidos = new LinkedList<>();
        int opcao;
        opcao = exibirMenu();
        do {
            switch (opcao) {
                case 1 -> abrirPedido(todosOsPedidos);
                case 2 -> alterarPedido(todosOsPedidos);
                case 3 -> relatorioPedido(todosOsPedidos);
                case 4 -> encerrarPedido(todosOsPedidos);
            }
            pausa();
            opcao = exibirMenu();
        } while (opcao != 0);

        teclado.close();
        System.out.println("FLW T+ VLW ABS.");
    }

}
