/** 
 * MIT License
 *
 * Copyright(c) 2025-26 João Caram <caram@pucminas.br>
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
    
    
    /**
     * Recebe uma mensagem para exibir e pede a leitura de um número inteiro, retornando-o. 
     * Código sem robustez para valores não inteiros.
     * @param mensagem Mensagem a ser exibida ao usuário
     * @return Número inteiro lido a partir do teclado
     */
    static int lerNumero(String mensagem){
        int valor;
        valor = Integer.parseInt(IO.readln("\t"+mensagem+": "));
        return valor;
    }

    /** Exibe mensagem e espera leitura do teclado para simular uma pausa no programa */
    static void pausa(){
        IO.readln("Digite enter para continuar.");
    }

    /**
     * Exibe um menu de opções, faz a leitura de um número e a retorna para o programa principal.
     * @return Número inteiro lido a partir do teclado (sem robustez para valores inválidos)
     */
    static int menu(){
        IO.println("1 - Procurar uma letra");
        IO.println("2 - Descobrir o tamanho");
        IO.println("3 - Substituir letras");
        IO.println("4 - Recortar frase");
        IO.println("5 - Contar repetição de letra");
        IO.println("0 - Sair");

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
        
        letra = IO.readln("Digite a letra para procurar: ");
        posicao = frase.indexOf(letra);
        if(posicao != -1)
            mensagem = letra + " encontrada na posição "+posicao;
        
        IO.println("\n"+mensagem);
    }

    /**
     * Demonstração de replace para substituir letra de uma string, criando outra string. 
     * Já faz a impressão do resultado para o programa principal
     * @param fraseOriginal Frase na qual a letra será substituída. 
     */
    static void substituirLetras(String fraseOriginal){
        String letraOriginal, letraNova, fraseNova;

        letraOriginal = IO.readln("Digite a letra a ser substituida: ");
        letraNova = IO.readln("Digite a letra a ser colocada: ");

        fraseNova = fraseOriginal.replace(letraOriginal, letraNova);

        IO.println("\nOriginal: "+fraseOriginal);
        IO.println("Com trocas: "+fraseNova);
    }

    /**
     * Exibe uma mensagem no console indicando quantos caracteres
     * existem na frase original.
     * @param fraseOriginal Frase da qual queremos exibir a quantidade
     * de caracteres.
     */
    static void mostrarTamanhoDaFrase(String fraseOriginal){
        IO.println("A frase '"+fraseOriginal+"' tem "+
            fraseOriginal.length()+" caracteres."
        );
    }

    /**
     * Demonstração de substring para recortar um trecho de uma string, retornando em outra string. 
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual o trecho será recortado.
     */
    static void recortarFrase(String fraseOriginal){
        String fraseNova;
        int posicaoInicial, posicaoFinal;
        int tamanhoFrase = fraseOriginal.length();

        IO.println("A frase tem "+tamanhoFrase+" caracteres.");
        posicaoInicial = lerNumero("Início do recorte (>=0)");
        posicaoFinal = lerNumero("Fim do recorte (<=" + tamanhoFrase+")");

        fraseNova = fraseOriginal.substring(posicaoInicial, posicaoFinal);

        IO.println("\nOriginal: "+fraseOriginal);
        IO.println("Cortada: "+fraseNova);
    }

    /**
     * Demonstração de charAt na verificação de repetições de uma letra em uma string.
     * Já faz a impressão do resultado para o programa principal
     * @param frase Frase na qual a letra será procurada/contada. 
     */
    static void contarRepeticao(String fraseOriginal){
        String letraParaContar;
        int contador=0;
        
        letraParaContar = IO.readln("Digite a letra a ser contada: ");
        for (int i = 0; i < fraseOriginal.length(); i++) {
            if(fraseOriginal.charAt(i) == letraParaContar.charAt(0))
                contador++;
        }

        IO.println("Temos "+contador+" ocorrências de "+letraParaContar+" na frase");
    }


    public static void main(String[] args) {
        int opcao=-1;
        String fraseOriginal;
        
        fraseOriginal = IO.readln("Digite uma frase completa: ");
        
        do{
            IO.println("\n\nFrase original: "+fraseOriginal+"\n");
            opcao = menu();
            switch (opcao) {
                case 0 -> IO.println("Adeus!!!");
                case 1 -> procurarLetra(fraseOriginal);
                case 2 -> IO.println("A frase "+fraseOriginal+" tem "+fraseOriginal.length()+" caracteres.");                
                case 3 -> substituirLetras(fraseOriginal);
                case 4 -> recortarFrase(fraseOriginal);
                case 5 -> contarRepeticao(fraseOriginal);
                default -> IO.println("Opção inválida");
            }
            pausa();
            
        }while(opcao !=0);
        

    }
}
