
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    static double totalVendido;
    static Scanner teclado;
    static HashMap<Integer, Cliente> clientes;

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
        System.out.println("🍔 XULAMBS FOODS - v0.41 🍕");
        System.out.println("=====================");
    }

    static int MenuPrincipal() {
        int opcao;
        cabecalho();
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Total Vendido Hoje");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Relatório de um Cliente");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
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
        String hoje = LocalDate.now().format(df);
        System.out.println("Total vendido hoje (" + hoje + "): R$ " + String.format("%.2f", totalVendido));
        pausa();
    }

    static Pedido escolherTipoPedido(){
        int opcao = 0;
        Pedido novoPedido = null;
        cabecalho();
        System.out.println("1 - Para comer no local");
        System.out.println("2 - Para entrega");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 1 -> novoPedido = new PedidoLocal();
            case 2-> {
                System.out.print("Digite a distância da entrega em km: ");
                double dist = Double.parseDouble(teclado.nextLine());
                novoPedido = new PedidoDelivery(dist);
            }
        }
        return novoPedido;

    }
    
    static Pedido criarPedido() {
        Pedido novoPedido = escolherTipoPedido();
        if(novoPedido!=null){ 
            Comida novaComida = criarComida();
            if (novaComida != null) {
                do {
                    novoPedido.addComida(novaComida);
                    System.out.println(novaComida.relatorio() + " adicionado ao pedido.");
                    pausa();
                    novaComida = criarComida();
                } while (novaComida != null);
                novoPedido.fecharPedido();
            } else
                novoPedido = null;
        }
        return novoPedido;
    }

    static boolean querBordaRecheada(){
        String querBorda="";
        System.out.print("\nPizza com borda recheada (S/N)? ");
        querBorda = teclado.nextLine();
        return querBorda.toLowerCase().equals("s");
    }
   
    static Comida criarComida() {
        int tipoComida = MenuComida();
        Comida novaComida = null;
        switch (tipoComida) {
            case 1:
                if(querBordaRecheada())
                    novaComida = new Pizza(0,true);
                else   
                    novaComida = new Pizza();
                break;
            case 2:
                novaComida = new Sanduiche();
                break;
        }

        if (novaComida != null) {
            System.out.print("Deseja quantos adicionais? ");
            int adicionais = Integer.parseInt(teclado.nextLine());
            novaComida.adicionarIngredientes(adicionais);
        }

        return novaComida;
    }

    static Cliente localizarCliente(){
        int idCli;
        cabecalho();
        System.out.print("Digite o id do cliente: ");
        idCli = Integer.parseInt(teclado.nextLine());
        return clientes.get(idCli);
    }

    static Cliente cadastrarCliente(){
        String nomeCliente;
        Cliente novoCliente;
        cabecalho();
        System.out.print("Digite o nome do cliente: ");
        nomeCliente = teclado.nextLine();
        novoCliente = new Cliente(nomeCliente);
        clientes.put(novoCliente.hashCode(), novoCliente);
        System.out.println("Cliente cadastrado:\n" + novoCliente);
        pausa();
        return novoCliente;
    }

    static void registrarPedido(Pedido pedido, Cliente cliente){
        if (pedido != null && cliente !=null) {
            totalVendido += pedido.precoFinal();
            cliente.registrarPedido(pedido);
            System.out.println("Pedido fechado: ");
            System.out.println(pedido.toString());
            System.out.println("Registrado para "+cliente);
            pausa();
        }
    }
    
    public static void main(String[] args) {
        clientes = new HashMap<>();
        teclado = new Scanner(System.in);
        totalVendido = 0d;
        int opcao;
        Pedido pedidoAtual;
        Cliente clienteAtual;
        do {
            opcao = MenuPrincipal();
            switch (opcao) {
                case 1:
                    clienteAtual = localizarCliente();
                    if(clienteAtual == null)
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
                    clienteAtual = localizarCliente();
                    if(clienteAtual != null){
                        System.out.println("Resumo do Cliente:");
                        System.out.println(clienteAtual.resumoPedidos());
                    }   
                    else 
                        System.out.println("Cliente não encontrado.");
                    pausa();
            }
        } while (opcao != 0);
        System.out.println("FLW VLW VLT SMP ABS 🤙");
        teclado.close();
    }
}
