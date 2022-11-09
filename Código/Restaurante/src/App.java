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
import java.util.Scanner;

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
        System.out.println("5 - Exibir lista de pedidos");
        
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

    public static int menuComida() {
        limparTela();
        System.out.println();
        System.out.println();

        System.out.println("XULAMBS FOOOODS");
        System.out.println("==========================");
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduíche");
        System.out.println("3 - PF");
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

    private static void exibirPedidos(Pedido[] todosOsPedidos, int quantPedidos) {
        Util.quicksort(todosOsPedidos, 0, quantPedidos-1);
        for (int i = 0; i < quantPedidos; i++) {
            System.out.println(todosOsPedidos[i]);
            System.out.println("------------------------------");
        } 
    }
    
    public static void main(String[] args) throws Exception {
        // testes feitos na classe de teste!!
        Scanner teclado = new Scanner(System.in);
        final int MAX_PEDIDOS = 100;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String hoje =  LocalDate.now().format(formatoData);  // data do sistema
        int opcao;
        Pedido[] todosOsPedidos = new Pedido[MAX_PEDIDOS];  //por enquanto, tamanho fixo
        Pedido novoPedido = null;
        int quantPedidos = 0;

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
                            case 3:
                                adicionarComidaAoPedido(novoPedido, new PratoFeito());
                            
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
                    novoPedido.encerrar();
                    todosOsPedidos[quantPedidos++] = novoPedido;
                    break;
                case 5:
                    limparTela();
                    exibirPedidos(todosOsPedidos, quantPedidos);
                    break;
            }
            pausa();
        } while (opcao != 0);
        teclado.close();
    }

    
}
