/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import Util.Util;
import Util.DAO;
//import Util.DAO;
import Util.Lista;

/**
 * App-demo para o restaurante. Versão 2: pedidos com pizzas.
 */
public class App {
    static Scanner teclado = new Scanner(System.in);
    // #region utilidades
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa para leitura de mensagens em console
 
     */
    static void pausa() {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }

    
    

    // #endregion

    // #region menus
    /**
     * Menu para o restaurante
     * 

     * @return Opção do usuário (int)
     */
    public static int menu() {

        System.out.println();
        System.out.println();

        System.out.println("XULAMBS FOOOODS");
        System.out.println("==========================");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Incluir comida em pedido");
        System.out.println("3 - Detalhes do pedido");
        System.out.println("4 - Fechar pedido");
        System.out.println("5 - Exibir todos os pedidos");
        System.out.println("6 - Selecionar cliente");
        System.out.println("7 - Gerenciar clientes");
        
        
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }

    public static int menuClientes() {
        limparTela();
        System.out.println();
        System.out.println();

        System.out.println("XULAMBS FOOOODS");
        System.out.println("==========================");
        System.out.println("1 - Visualizar informações de cliente (código)");
        System.out.println("2 - Subconjunto de clientes por nome");
        System.out.println("3 - Cadastrar cliente");
        

        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }

    public static int menuComida() {
        limparTela();
        System.out.println();
        System.out.println();

        System.out.println("XULAMBS FOOOODS");
        System.out.println("==========================");
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduíche");
        System.out.println("3 - Prato feito");
        System.out.println("4 - Suco natural");

        System.out.println("0 - Cancelar");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }

    public static int menuIngrediente() {
        limparTela();
        System.out.println();
        System.out.println();

        System.out.println("XULAMBS FOOOODS");
        System.out.println("==========================");
        System.out.println("1 - Bacon");
        System.out.println("2 - Cheddar");
        System.out.println("3 - Palmito");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }

    // #endregion

    /**
     * Utilizado para perguntar, no momento da criação da pizza para inclusão,
     * quantos adicionais ela deve ter.
     * 
     * @param novaComida A pizza criada que receberá os adicionais.
     */
    private static void adicionarIngredientes(IPersonalizavel novaComida) {
        int cont = -1;
        do{
            cont = menuIngrediente();
            switch(cont){
                case 1: novaComida.addIngredientes(new Bacon());
                    break;
                case 2: novaComida.addIngredientes(new Cheddar());
                    break; 
                case 3: novaComida.addIngredientes(new Palmito());
                    break; 
                default: cont=0;
                    System.out.println("Inclusão finalizada.");
            }
        }while(cont!=0);
    }

    /**
     * Utilizado para conter a rotina de adicionar uma pizza, chamando em seguida a
     * inclusão de ingredientes.
     * 
     * @param pedido  O pedido atual.
     * @param comida   A pizza que será incluída no pedido.
     */
    public static void adicionarComidaAoPedido(Pedido pedido, Comida comida) {
        if (pedido.addComida(comida)) {
            
            System.out.println("Comida adicionada ao pedido.");
        } else
            System.out.println("Não foi possível adicionar a comida: limite atingido ou pedido fechado.");
    }

    public static void exibirPedidos(Lista<Pedido> pedidos){
        Pedido[] pedidosDecrescente = new Pedido[pedidos.tamanho()];
        pedidosDecrescente = pedidos.toArray(pedidosDecrescente);
        Util.quicksort(pedidosDecrescente, 0, pedidosDecrescente.length-1);    
    
        limparTela();
        System.out.println("PEDIDOS DE HOJE:");
        for (int i = pedidosDecrescente.length-1; i >= 0; i--) {
            System.out.println(pedidosDecrescente[i]);
            System.out.println("------------------------");
        }    
    }
    
    public boolean cadastrarCliente(Set<Cliente> clientes){
        limparTela();
        System.out.println("NOVO CLIENTE:");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        Cliente novo = new Cliente(nome);
        
        return clientes.add(novo);
    }

    public static Cliente criarCliente(){
        limparTela();
        System.out.println("NOVO CLIENTE:");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        Cliente novo = new Cliente(nome);
        return novo;
       
    }

    
    public static Cliente selecionarCliente(LinkedHashMap<Integer, Cliente> clientes){
            System.out.print("Código do cliente: ");
            int  codCli = Integer.parseInt(teclado.nextLine());
            return clientes.get(codCli);
    }
    
    public static void infoCliente(LinkedHashMap<Integer, Cliente> clientes){
        Cliente clienteInfo = selecionarCliente(clientes);
        if(clienteInfo==null)
            System.out.println("ATENÇÃO: Cliente não encontrado.");
        else{
            limparTela();
            System.out.println(clienteInfo.toString());
            System.out.println("Pedido mais caro:\n "+clienteInfo.maisCaro());
            System.out.println("Pedido mais recente:\n "+clienteInfo.maisCaro());
        
        }
    }

    public static void main(String[] args) throws Exception {
        // testes feitos na classe de teste!!
        
        TreeSet<Cliente> conjuntoClientes = new TreeSet<Cliente>();
        LinkedHashMap<Integer, Cliente> mapaClientes = new LinkedHashMap<Integer, Cliente>();
        SortedMap<String, Cliente> arvoreClientes = new TreeMap<String, Cliente>();

        Pedido novoPedido = null;
        Cliente clienteAtual = null;        
       DAO<Comida> daoCli = new DAO<>();

       //daoCli.carregarArquivoInteiro(null, (linha)->Cliente.parseCliente(linha));
        
        Lista<Pedido> listaDePedidos = new Lista<Pedido>();
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String hoje =  LocalDate.now().format(formatoData);  // fictício, para não precisar pegar data do sistema
        int opcao;


        do {
            limparTela();
            opcao = menu();
            switch (opcao) {
                case 1:
                    novoPedido = new Pedido(hoje);
                    System.out.println("Pedido criado.");
                    break;
                case 2:
                    if (novoPedido != null){
                        int comida = menuComida();
                        switch(comida){
                            case 1: Pizza p = new Pizza(); 
                            adicionarComidaAoPedido(novoPedido, p);
                                adicionarIngredientes(p);
                                break;
                            case 2: Sanduiche s = new Sanduiche(); 
                                adicionarComidaAoPedido(novoPedido, s);
                                adicionarIngredientes(s);
                                break;
                            case 3: PratoFeito pf = new PratoFeito(); 
                                adicionarComidaAoPedido(novoPedido, pf);
                                break; 
                                case 4: Suco su = new Suco(); 
                                adicionarComidaAoPedido(novoPedido, su);
                                break; 
                            default: System.out.println("Inclusão cancelada.");
                        }
                    }
                    else
                        System.out.println("Pedido ainda não foi aberto.");
                    break;
                case 3:
                    limparTela();
                    System.out.println(novoPedido);
                    break;
                case 4:
                    while(clienteAtual==null){
                        System.out.println("Selecionar o cliente do pedido.");
                        clienteAtual = selecionarCliente(mapaClientes);
                    }
                    novoPedido.encerrar();
                    clienteAtual.addPedido(novoPedido);
                    listaDePedidos.inserirNoFim(novoPedido);
                    
                    break;
                case 5:
                    exibirPedidos(listaDePedidos);
                    break;
                case 6: clienteAtual = selecionarCliente(mapaClientes);
                        if(clienteAtual==null)
                            System.out.println("ATENÇÃO: Cliente não encontrado.");
                    break;
                case 7: int opcClientes = menuClientes();
                        switch(opcClientes){
                            case 1:
                                infoCliente(mapaClientes);
                                break;
                            case 2: 
                                limparTela();
                                System.out.println("Nome inicial: ");
                                String from = teclado.nextLine();
                                System.out.println("Nome final: ");
                                String to = teclado.nextLine();
                                NavigableSet<Cliente> consulta = conjuntoClientes.subSet(new Cliente(from), true, new Cliente(to), true);                            
                                for (Cliente cliente : consulta) {
                                    System.out.println(cliente);
                                }
                                break;
                            case 3:
                                Cliente novo = criarCliente();
                                if(conjuntoClientes.add(novo)){
                                    mapaClientes.put(novo.hashCode(), novo);
                                    arvoreClientes.put(novo.nome(), novo);
                                }
                                else
                                    System.out.println("ATENÇÃO: Cliente com este nome já existe.");
                                break;
                        }
                   
                    
                break;
            }
            pausa();
        } while (opcao != 0);
        teclado.close();
    }
}