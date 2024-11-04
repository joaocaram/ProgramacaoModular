import java.io.File;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat.Encoding;

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

public class XulambsFoods {
    static Scanner teclado;
    static List<Pedido> todosOsPedidos;
    static List<Cliente> todosOsClientes;

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
        System.out.println("XULAMBS FOODS - v0.5\n=============");
    }

    static int exibirMenu() {
        cabecalho();
       
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Alterar Pedido");
        System.out.println("3 - Relatório de Pedido");
        System.out.println("4 - Encerrar Pedido");
        System.out.println("5 - Relatório Clientes (nome)");
        System.out.println("6 - Relatório Clientes (id)");
        System.out.println("7 - Pedido mais caro");
        System.out.println("0 - Finalizar");
        System.out.print("Digite sua escolha: ");
        
        return Integer.parseInt(teclado.nextLine());
    }

    static void config() throws Exception{
        teclado = new Scanner(System.in);
        todosOsPedidos = new LinkedList<Pedido>();
        todosOsClientes = new LinkedList<Cliente>();
        gerarClientes();    
        gerarPedidos();
        
    }
    static void gerarClientes() throws Exception{
        Scanner arq = new Scanner(new File("clientes.txt"), Charset.forName("UTF-8"));
        List<String> nomes = new LinkedList<>();
        while(arq.hasNextLine()){
            nomes.add(arq.nextLine());
        }
        for (String nome : nomes) {
            Cliente cliente = new Cliente(nome);
            todosOsClientes.add(cliente);
        }
        arq.close();
    }

    private static void gerarPedidos() {
        Random aleat = new Random(42);
        int quantos = todosOsClientes.size() * 13;
        for (int i = 0; i < quantos; i++)
        {
            Pedido novoPedido;
            int tipoPedido = aleat.nextInt(100)%2;
            switch (tipoPedido) {
                case 0 -> novoPedido = new PedidoLocal();
                case 1 -> novoPedido = new PedidoEntrega(aleat.nextDouble() * 12);
                default -> novoPedido = null;                 
            }
            
            int quantasComidas = aleat.nextInt(1, 7);
            for (int j = 0; j < quantasComidas; j++)
            {
                Comida novaComida = null;
                int tipoComida = aleat.nextInt(1, 4);
                int ingredientes = aleat.nextInt(6);
                switch (tipoComida) {
                    case 1 -> novaComida = new Comida(EComida.PIZZA);
                    case 2 -> novaComida = new Comida(new Sanduiche(true));                                 
                    case 3 -> novaComida = new Comida(EComida.SANDUICHE);                   
                }
                novaComida.adicionarIngredientes(ingredientes);
                novoPedido.adicionar(novaComida);
            }
    
            novoPedido.fecharPedido();
            int id = aleat.nextInt(1, todosOsClientes.size());
            todosOsPedidos.add(novoPedido);
            Cliente cliente = todosOsClientes.get(id);
            cliente.RegistrarPedido(novoPedido);
        }           
    }

    static Pedido criarPedidoEntrega(){
        System.out.print("\nDistância para entrega: ");
        double distancia = Double.parseDouble(teclado.nextLine());
        return new PedidoEntrega(distancia);
    }
    static Pedido escolherTipoPedido(){
        Pedido novo = null;
        cabecalho();
        int escolha;
        System.out.println("Escolha o tipo de pedido:");
        System.out.println("1 - Local");
        System.out.println("2 - Para entrega");
        System.out.print("Sua opção: ");
        escolha = Integer.parseInt(teclado.nextLine());
        switch (escolha) {
            case 1: novo = new PedidoLocal();
                break;
            case 2: novo = criarPedidoEntrega();
                break;
        }
        return novo;
    }
    static Pedido abrirPedido() {
        
        Pedido novoPedido = escolherTipoPedido();  
        System.out.println(novoPedido.toString());
        pausa();
        adicionarComidas(novoPedido);
        return novoPedido;
    }

    static void relatorioPedido(Pedido pedido) {
        cabecalho();
        System.out.println(pedido.toString() + "\n");
    }

    static Pedido localizarPedido(List<Pedido> pedidos) {
        cabecalho();
        int id;
        System.out.println("Localizando um pedido.");
        System.out.print("ID do pedido: ");
        id = Integer.parseInt(teclado.nextLine());

        for (Pedido ped : pedidos) {
            if (ped.toString().contains("Pedido " + String.format("%02d", id)))
                return ped;
        }
        return null;
    }

    private static void adicionarComidas(Pedido procurado) {
        String escolha = "n";
        do {
            relatorioPedido(procurado);
            Comida novaComida = comprarComida();
            
            procurado.adicionar(novaComida);
            System.out.print("\nDeseja outra comida? (s/n) ");
            escolha = teclado.nextLine();
        } while (escolha.toLowerCase().equals("s"));
    }

    private static Comida comprarComida() {
        Comida novaComida = null;
        int escolha = 0;
        System.out.println("Escolha sua comida: ");
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduiche");
        System.out.print("Opção: ");
        escolha = Integer.parseInt(teclado.nextLine());
        switch (escolha) {
            case 1: novaComida = comprarPizza();
                break;
            case 2:
                System.out.print("Sanduiche: Deseja combo com fritas? (s/n) ");
                String combo = teclado.nextLine();
                boolean querCombo = combo.toLowerCase().equals("s")? true : false;
                novaComida = new Comida(new Sanduiche(querCombo));
                break;
        }
        if(novaComida!=null){
            escolherIngredientes(novaComida);
            mostrarNota(novaComida);
        }
        return novaComida;
    }

    static Comida comprarPizza() {
        System.out.println("Comprando uma nova pizza:");
        Comida novaPizza = new Comida(EComida.PIZZA);
        return novaPizza;
    }

    static void escolherIngredientes(Comida comida) {
        System.out.print("Quantos adicionais você deseja? (máx. 8): ");
        int adicionais = Integer.parseInt(teclado.nextLine());
        comida.adicionarIngredientes(adicionais);
    }

    static void mostrarNota(Comida comida) {
        System.out.println("Você acabou de comprar: ");
        System.out.println(comida.notaDeCompra());

    }

    public static void main(String[] args) throws Exception {
        config();
        Comparator<Cliente> comp = (c1, c2) -> 
                                   (c1.hashCode()-c2.hashCode());
        
        Comparator<Pedido> compPed = (p1, p2) -> 
                                     (p1.precoAPagar()-p2.precoAPagar())>0? 1 : -1;
        
        Pedido pedido = null;
        
        int opcao;
        opcao = exibirMenu();
        do {
            switch (opcao) {
                case 1:
                    pedido = abrirPedido();
                    todosOsPedidos.add(pedido);
                    relatorioPedido(pedido);
                    break;
                case 2:
                    pedido = localizarPedido(todosOsPedidos);
                    if (pedido != null){
                        adicionarComidas(pedido);
                        relatorioPedido(pedido);
                    }
                    else
                        System.out.println("Pedido não existente.");
                    break;
                case 3:
                    pedido = localizarPedido(todosOsPedidos);
                    if (pedido != null)
                        relatorioPedido(pedido);
                    else
                        System.out.println("Pedido não existente.");
                    break;
                case 4:
                    pedido = localizarPedido(todosOsPedidos);
                    if (pedido != null) {
                        pedido.fecharPedido();
                        System.out.println("Pedido encerrado: ");
                        System.out.println(pedido.toString());
                    } else
                        System.out.println("Pedido não existente.");
                    break;
                case 5:
                    Cliente maior = todosOsClientes.get(0);
                    for (Cliente cli : todosOsClientes) {
                            if(cli.compareTo(maior) > 0)
                                    maior = cli;                        
                    }
                    System.out.println(maior);
                    break;
                case 6:
                    Cliente maiorGasto = todosOsClientes.get(0);
                    
                    for (Cliente cli : todosOsClientes) {
                        if(comp.compare(cli, maiorGasto)> 0)
                                    maiorGasto = cli;                        
                    }
                    System.out.println(maiorGasto);
                    break;
                case 7: 
                    Pedido maisCaro = todosOsPedidos.get(0);       
                    for (Pedido pedidoAtual : todosOsPedidos) {
                        if(compPed.compare(pedidoAtual, maisCaro)> 0)
                                    maisCaro = pedidoAtual;                        
                    }
                    System.out.println(maisCaro);
                    break;

            }
            pausa();
            opcao = exibirMenu();
        } while (opcao != 0);

        teclado.close();
        System.out.println("FLW T+ VLW ABS.");
    }

}
