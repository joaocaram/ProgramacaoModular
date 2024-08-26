import java.util.Scanner;

public class RevProg {

    static Scanner teclado;

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
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Encapsula uma leitura de teclado, com mensagem personalizada. A mensagem sempre é completada com ":". Retorna um inteiro. 
     * @param mensagem A mensagem a ser exibida, sem pontuação final.
     * @return String convertida em inteiro (int).
     */
    public static int leituraInteiro(String mensagem){
        System.out.print("\n"+mensagem+": ");
        return Integer.parseInt(teclado.nextLine());
    }


    /**
     * Ver o enunciado do exercício no Canvas.
     */
    public static void exercicio01(){
        int largura = leituraInteiro("Largura do retângulo");
        int altura = leituraInteiro("Altura do retângulo");
        imprimirRetangulo(largura, altura);
    }

    /**
     * Usa os métodos {@link #imprimirLinhaCheia(int, int) imprimirLinhaCheia} e {@link #imprimirLinhaVazada(int, int) imprimirLinhaVazada}
     * para desenhar um retângulo na tela. O caractere usado para a borda está fixo em '#'
     * @param largura A largura do retângulo
     * @param altura A altura do retângulo
     */
    public static void imprimirRetangulo(int largura, int altura){
        imprimirLinhaCheia(largura, '_');
        for (int i = 0; i < altura-2; i++) {
            imprimirLinhaVazada(largura, '|');
        }
        imprimirLinhaCheia(largura, '-');
        
    }

    /**
     * Imprime uma linha preenchida pelo caractere 'borda', com a largura especificada.
     * @param largura Qual a largura da linha
     * @param borda O caractere para ser a 'borda' da linha
     */
    public static void imprimirLinhaCheia(int largura, char borda){
        for (int i = 0; i < largura; i++) {
            System.out.print(borda);
        }
        System.out.println();
    }

    /**
     * Imprime uma linha vazada por espaços, usando o caractere 'borda' nas extremidades, com a largura especificada.
     * @param largura Qual a largura da linha
     * @param borda O caractere para ser a 'borda' da linha
     */
    public static void imprimirLinhaVazada(int largura, char borda){
        System.out.print(borda);
        for (int i = 0; i < largura-2; i++) {
            System.out.print(" ");
        }
        System.out.println(borda);   
    }


    /**
     * Ver o enunciado do exercício no Canvas.
     */
    public static void exercicio02(){
        int tamanhoTabua = leituraInteiro("Tamanho da tábua em metros");
        int quantPedacos = leituraInteiro("Total de pedaços a cortar");
        int quantidadeTabuas = quantidadeAComprar(tamanhoTabua, quantPedacos);
        int sobraCorte = sobraDeMaterial(tamanhoTabua, quantPedacos);

        System.out.println("Será necessário comprar "+quantidadeTabuas+" tábuas de "+tamanhoTabua+"m.");
        System.out.println("Sobrarão "+sobraCorte+" cm de tábua.");
    }

    /**
     * Calcula a quantidade de tábuas a comprar para cortar 'quantPedacos' de madeira de 45cm cada. Método desprotegido: não faz validação de valores negativos.
     * @param tamanhoTabua Tamanho da tábua em metros (inteiro)
     * @param quantPedacos Quantos pedaços de 45cm cada serão necessários.
     * @return Valor inteiro com a quantidade de tábuas necessárias para 'quantPedacos' de madeira.
     */
    public static int quantidadeAComprar(int tamanhoTabua, int quantPedacos){
        int tamanhoTotal = quantPedacos * 45;
        int tabuaEmCm = tamanhoTabua * 100;

        int quantidade = (tamanhoTotal/tabuaEmCm);
        int resto = (tamanhoTotal%tabuaEmCm);
        if(resto>0)
            quantidade++;        
    
        return quantidade;
    }

    /**
     * Calcula a sobra de material, em cm, para se criar 'quantPedacos' de 45cm a partir de tábuas com comprimento 'tamanhoTabua'.
     * Método desprotegido: não faz validação de valores negativos.
     * @param tamanhoTabua Tamanho da tábua em metros (inteiro)
     * @param quantPedacos Quantos pedaços de 45cm cada serão necessários.
     * @return Valor inteiro com a sobra de madeira, em centímetros.
     */
    public static int sobraDeMaterial(int tamanhoTabua, int quantPedacos){
        int tamanhoTotal = quantPedacos * 45;
        int tabuaEmCm = tamanhoTabua * 100;

        int quantidade = quantidadeAComprar(tamanhoTabua, quantPedacos);
        int sobra = (quantidade * tabuaEmCm) % tamanhoTotal;

        return sobra;
    }

    /**
     * Ver o enunciado do exercício no Canvas.
     * O método lê 5 números e em seguida imprime o resultado da rifa fazendo a impressão de cada um dos dígitos extraídos, 
     * sem armazenar o resultado.
     */
    public static void exercicio03(){
        int[] premios = new int[5];
        for (int i = 0; i < premios.length; i++) {
            premios[i] = leituraInteiro("Digite o valor do "+(i+1)+"º prêmio, com 5 dígitos");
        }
        System.out.print("O prêmio da rifa é: ");
        for (int i = 0; i < premios.length; i++) {
            System.out.print(extrairDigito(i+1, premios[i]));
        }
        System.out.println();
    }

    /**
     * Extrai um dígito de um valor inteiro numérico de 5 dígitos a partir da escolha da posição. 
     * Este método considera que o dígito mais à esquerda do valor original é a posição 1. São feitas operações de divisão e resto para
     * obtenção do resultado. Por exemplo, posição 3 do valor 46138 : 46138/10^(5-3) = 46138/10^2 = 46138/100 = 461. Em seguida,
     * 461 % 10 = 1 (3º dígito do valor original). O método não está protegido contra posições inválidas.
     * @param posicao Posição do dígito a ser extraído. O dígito mais à esquerda é considerada a posição 1.
     * @param valorOriginal O valor original, obrigatoriamente com 5 dígitos.
     * @return Um número inteiro representando o dígito na posição desejada do valor original.
     */
    public static int extrairDigito(int posicao, int valorOriginal){
        int potencia = 5 - posicao;
        int fatorDivisao = (int)Math.pow(10, potencia);
        int valor = valorOriginal / fatorDivisao;
        return valor % 10;
    }

    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in);
        
        exercicio02();
        teclado.close();
    }
}
