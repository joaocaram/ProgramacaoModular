/** 
 * MIT License
 *
 * Copyright(c) 2022-4 João Caram <caram@pucminas.br>
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

import java.util.Scanner;

/**
  * Demonstração do uso de objetos da classe Hora em um programa/app. 
  * Este programa nada mais faz além de testar algumas operações da classe (o que aprenderemos a fazer melhor posteriormente).
  */
public class HoraApp {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Hora hora;
        Hora hora2;
        int horas;
        int minutos;
        int segundos;
        int incremento;
        String horaDoUsuario;

        System.out.print("Por favor, digite um horário válido (HH:MM:SS): ");
        horaDoUsuario = teclado.nextLine();
        
        String[] detalhesHora = horaDoUsuario.split(":");
        horas = Integer.parseInt(detalhesHora[0]);
        minutos = Integer.parseInt(detalhesHora[1]);
        segundos = Integer.parseInt(detalhesHora[2]);

        hora = new Hora();
        hora.ajustar(horas, minutos, segundos);

        System.out.print("Sua hora escolhida: ");
        System.out.println(hora.horaFormatada());

        System.out.print("\nPor favor, digite um incremento em segundos positivos: ");
        incremento = Integer.parseInt(teclado.nextLine());

        hora2 = hora.incrementar(incremento);

        System.out.printf("%s é sua hora original e %s é sua hora com incremento de %d segundos\n"
                        , hora.horaFormatada(), hora2.horaFormatada(), incremento);
        
        System.out.printf("%s está na frente de %s? ", hora.horaFormatada(), hora2.horaFormatada());
        System.out.println(hora.estahNaFrenteDe(hora2));

        System.out.printf("%s está na frente de %s? ", hora2.horaFormatada(), hora.horaFormatada());
        System.out.println(hora2.estahNaFrenteDe(hora));

        System.out.print("Ajustando a hora para 19:08:24: ");
        hora.ajustar(19, 8, 24);
        System.out.println(hora.horaFormatada());

        teclado.close();
    }
}
