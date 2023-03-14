import java.util.Scanner;

/**
 * MIT License
 *
 * Copyright(c) 2022-2023 João Caram <caram@pucminas.br>
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

public class App {
    static Scanner teclado = new Scanner(System.in);
 
    // #region Utilidades

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Menu para o restaurante
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    public static int menu() {
        limparTela();
        System.out.println("XULAMBS PIZZA");
        System.out.println("==========================");
        System.out.println("1 - Comprar pizza");
        System.out.println("2 - Alterar pizza");
        System.out.println("3 - Mostrar compra atual");
        System.out.println("4 - Finalizar compra");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    /**
     * Menu para o restaurante
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    public static int subMenuAdicionais() {

        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Inserir ingredientes");
        System.out.println("2 - Retirar ingredientes");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }
    // #endregion

    // #region métodos do app

    /**
     * Chama a compra (criação) de uma nova pizza, chamando em seguida o método de gerência de ingredientes.
     * @return Uma pizza pronta com seus ingredientes adicionais.
     */
    public static Pizza comprarPizza() {
        System.out.println("\nComprando uma nova pizza");
        Pizza novaPizza = new Pizza();
        alterarPizza(novaPizza);
        return novaPizza;
    }

    /**
     * Permite a alteração dos ingredientes de uma pizza.
     * @param pizza A pizza que sofrerá as alterações nos ingredientes.
     */
    public static void alterarPizza(Pizza pizza) {
        int operacao = subMenuAdicionais();
        int quantos = 0;
        if (operacao == 1 || operacao == 2) {
            System.out.print("Quantos? ");
            quantos = Integer.parseInt(teclado.nextLine());
            if (operacao == 1)
                pizza.adicionarIngrediente(quantos);
            else
                pizza.retirarIngrediente(quantos);
        }

    }

    /**
     * Encapsula a impressão de dados de uma pizza no sistema.
     * @param mensagem Mensagem adicional para exibição (opcional)
     * @param qual A pizza que terá seus dados impressos.
     */
    public static void imprimirDadosPizza(String mensagem, Pizza qual) {
        if (qual != null){
            System.out.println(mensagem);
            System.out.println(qual.imprimirNotaDeCompra());
        }
        else
            System.out.println("Não há compra em andamento.");
    }
    // #endregion

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        Pizza pizzaAtual = null;
        String mensagem="";
        
        do {
            opcao = menu();
            limparTela();
            switch (opcao) {
                case 1:
                    if (pizzaAtual == null) {
                        pizzaAtual = comprarPizza();
                    } else
                        System.out.println("Você precisa terminar a outra compra antes.");
                    break;
                case 2:
                    if (pizzaAtual != null)
                        alterarPizza(pizzaAtual);
                    else
                        pizzaAtual = comprarPizza();
                    break;
                case 3: mensagem = "Compra atual:";
                        imprimirDadosPizza(mensagem, pizzaAtual);
                    break;
                case 4: mensagem = "Finalizando a compra:";
                        imprimirDadosPizza(mensagem, pizzaAtual);
                        pizzaAtual = null;    
                break;    
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Vlw flw glr. T prx.");
    }
}
