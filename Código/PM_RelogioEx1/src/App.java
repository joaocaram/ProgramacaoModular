import java.util.Scanner;
/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
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
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Relogio primeiroRelogio = new Relogio();        //chamada do construtor

        int hora=0;
        int minuto=0;
        int segundo=0;

        String[] horario;
        //App para exemplo de uso de classe, sem tratamento de erros. Cuidado ;-)
        System.out.print("Digite o horário atual, no formato HH:MM:SS --> ");
        horario = teclado.nextLine().split(":");
        if(horario.length==3){
            hora = Integer.parseInt(horario[0]);
            minuto = Integer.parseInt(horario[1]);
            segundo = Integer.parseInt(horario[2]);
        }
        primeiroRelogio.ajustarHora(hora, minuto, segundo);
        System.out.println("Hora atual: "+primeiroRelogio.horaFormatada());
        primeiroRelogio.passarTempo();
        System.out.println("Um segundo depois: "+primeiroRelogio.horaFormatada());
        for (int i = 0; i < 10; i++) {
            primeiroRelogio.passarTempo();    
        }
        System.out.println("Mais 10 segundos: "+primeiroRelogio.horaFormatada());
        

    }
}
