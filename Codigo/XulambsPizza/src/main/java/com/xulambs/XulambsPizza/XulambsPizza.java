package com.xulambs.XulambsPizza;

import java.util.Scanner;
import com.xulambs.XulambsPizza.Models.Pedido;
import com.xulambs.XulambsPizza.Models.Pizza;
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
        System.out.println("XULAMBS PIZZA\n=============");
    }

    static int exibirMenu() {
        cabecalho();
        System.out.println("1 - Comprar pizza");
        System.out.println("0 - Finalizar");
        System.out.print("Digite sua escolha: ");
        return Integer.parseInt(teclado.nextLine());
    }

    static void comprarPizza() {
        cabecalho();
        System.out.println("Comprando uma nova pizza:");
        Pizza novaPizza = new Pizza();
        escolherIngredientes(novaPizza);
        mostrarNota(novaPizza);
        Pedido pedido = new Pedido();
        pedido.adicionar(novaPizza);
    }

    static void escolherIngredientes(Pizza pizza) {
        System.out.print("Quantos adicionais você deseja? (máx. 8): ");
        int adicionais = Integer.parseInt(teclado.nextLine());
        pizza.adicionarIngredientes(adicionais);
    }

    static void mostrarNota(Pizza pizza) {
        System.out.println("Você acabou de comprar: ");
        System.out.println(pizza.notaDeCompra());

    }

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(XulambsPizzaApplication.class, args);
        teclado = new Scanner(System.in);
        int opcao = -1;
        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1:
                    comprarPizza();
                    break;
            }
            pausa();
        } while (opcao != 0);
        teclado.close();
        System.out.println("FLW T+ VLW ABS.");
    }

}
