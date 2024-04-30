import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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

/**
 * App-demo do uso de interfaces e habilidades combinadas. 
 */
public class App {
    static int numero=1;        //para id dos dispositivos
    static Scanner teclado;     //para leitura do teclado

    /**
     * Cria um dispositivo baseado em uma string descritora. (método análogo a uma fábrica)
     * @param qual Qual dispositivo deve ser criado
     * @return Um dispositivo, ou nulo, em caso de string não reconhecida
     */
    public static Dispositivo criarDispositivo(String qual){
            Dispositivo novo = null;
            switch (qual.toLowerCase()) {
                case "lampada" -> novo = new Lampada("L"+numero);
                case "cafeteira" -> novo = new Cafeteira("C"+numero);
                case "geladeira" -> novo = new Geladeira("G"+numero);
            }
            numero++;
            return novo;
    }

    
    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in);
        Amplexa assistente = new Amplexa("Faz Tudo");
        for (int i = 0; i < 6; i++) {
            Dispositivo lamp = criarDispositivo("lampada");
            assistente.addDispositivo(lamp);
        }
        
        System.out.println("=====INICIO=====");
        System.out.println(assistente);
        teclado.nextLine();

        System.out.println("=====LIGANDO DISPOSITIVOS=====");
        assistente.ligar("L5");
        System.out.println(assistente);
        teclado.nextLine();

        System.out.println("=====ADICIONANDO E LIGANDO CAFETEIRAS=====");
        assistente.addDispositivo(criarDispositivo("cafeteira"));
        assistente.addDispositivo(criarDispositivo("cafeteira"));
        assistente.ligar("C7");
        System.out.println(assistente);
        teclado.nextLine();

        System.out.println("=====ADICIONANDO GELADEIRA=====");
        assistente.addDispositivo(criarDispositivo("GELADEIRA"));
                
        System.out.println(assistente);
        teclado.nextLine();

        System.out.println("=====REGULANDO CAFETEIRAS E GELADEIRAS=====");
        assistente.ligar("C8");
        assistente.regular("G9", 77);
        assistente.regular("C8", 93);
                
        System.out.println(assistente);
        teclado.nextLine();


        teclado.close();
    }
}
