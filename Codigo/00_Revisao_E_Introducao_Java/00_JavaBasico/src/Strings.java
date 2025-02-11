import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2025 João Caram <caram@pucminas.br>
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

 /** Java básico: E/S e algumas operações com strings */
public class Strings {
    
    /** Scanner para leitura da entrada padrão (teclado) */
    static Scanner teclado;

    /**
     * Recebe uma mensagem para exibir e pede a leitura de um número inteiro, retornando-o. 
     * Código sem robustez para valores não inteiros.
     * @param mensagem Mensagem a ser exibida ao usuário
     * @return Número inteiro lido a partir do teclado
     */
    static int lerNumero(String mensagem){
        int valor;
        System.out.print("\t"+mensagem+": ");
        valor = Integer.parseInt(teclado.nextLine());
        return valor;
    }

    /** Exibe mensagem e espera leitura do teclado para simular uma pausa no programa */
    static void pausa(){
        System.out.println("Digite enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Exibe um menu de opções, faz a leitura de um número e a retorna para o programa principal.
     * @return Número inteiro lido a partir do teclado (sem robustez para valores inválidos)
     */
    static int menu(){
        System.out.println("1 - Procurar uma letra");
        System.out.println("2 - Descobrir o tamanho");
        System.out.println("3 - Substituir letras");
        System.out.println("4 - Recortar frase");
        System.out.println("5 - Contar repetição de letra");
        System.out.println("0 - Sair");

        int opcao = lerNumero("Digite sua opção");
        return opcao;
    }

    /**
     * Demonstração de indexOf para localizar a ocorrência de uma letra ou substring em outra string. 
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual a letra será procurada. 
     */
    static void procurarLetra(String frase){
        String letra;
        String mensagem = "Letra não existe na frase";
        int posicao;
        System.out.print("Digite a letra para procurar: ");
        letra = teclado.nextLine();
        posicao = frase.indexOf(letra);
        if(posicao!=-1)
            mensagem = letra + " encontrada na posição "+posicao;
        
        System.out.println("\n"+mensagem);
    }

    /**
     * Demonstração de replace para substituir letra de uma string, criando outra string. 
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual a letra será substituída. 
     */
    static void substituirLetras(String fraseOriginal){
        String letraOriginal, letraNova, fraseNova;

        System.out.print("Digite a letra a ser substituida: ");
        letraOriginal = teclado.nextLine();
        System.out.print("Digite a letra a ser colocada: ");
        letraNova = teclado.nextLine();

        fraseNova = fraseOriginal.replace(letraOriginal, letraNova);

        System.out.println("\nOriginal: "+fraseOriginal);
        System.out.println("Com trocas: "+fraseNova);
    }

    /**
     * Demonstração de substring para recortar um trecho de uma string, retornando em outra string. 
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual o trecho será recortado.
     */
    static void recortarFrase(String fraseOriginal){
        String fraseNova;
        int posicaoInicial, posicaoFinal;
        System.out.println("A frase tem "+fraseOriginal.length()+" caracteres.");
        posicaoInicial = lerNumero("Início do recorte (>=0)");
        posicaoFinal = lerNumero("Fim do recorte (<=" + fraseOriginal.length()+")");

        fraseNova = fraseOriginal.substring(posicaoInicial, posicaoFinal);

        System.out.println("\nOriginal: "+fraseOriginal);
        System.out.println("Cortada: "+fraseNova);
    }

    /**
     * Demonstração de charAt na verificação de repetições de uma letra em uma string.
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual a letra será procurada/contada. 
     */
    static void contarRepeticao(String fraseOriginal){
        String letraParaContar;
        int contador=0;
        System.out.print("Digite a letra a ser contada: ");
        letraParaContar = teclado.nextLine();
        for (int i = 0; i < fraseOriginal.length(); i++) {
            if(fraseOriginal.charAt(i) == letraParaContar.charAt(0))
                contador++;
        }

        System.out.println("Temos "+contador+" ocorrências de "+letraParaContar+" na frase");
    }


    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        int opcao=-1;
        String fraseOriginal;
        
        System.out.print("Digite uma frase completa: ");
        fraseOriginal = teclado.nextLine();
        
        do{
            System.out.println("\n\nFrase original: "+fraseOriginal+"\n");
            opcao = menu();
            switch (opcao) {
                case 0 -> System.out.println("Adeus!!!");
                case 1 -> procurarLetra(fraseOriginal);
                case 2 -> System.out.println("A frase "+fraseOriginal+" tem "+fraseOriginal.length()+" caracteres.");                
                case 3 -> substituirLetras(fraseOriginal);
                case 4 -> recortarFrase(fraseOriginal);
                case 5 -> contarRepeticao(fraseOriginal);
            }
            pausa();
            
        }while(opcao !=0);
        

    }
}
