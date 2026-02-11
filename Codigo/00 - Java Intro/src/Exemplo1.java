public class Exemplo1 {
    public static void main(String[] args) {
        int[] vetor;
        String numero = IO.readln("Quantos números você vai digitar? "); 
        int n = Integer.parseInt(numero);
        vetor = new int[n];
        int soma = 0;
        double media;
        for(int i = 0 ; i < n; i++){
            String valor = IO.readln("Digite o número "+(i+1)+": ");
            int numerico = Integer.parseInt(valor);
            vetor[i] = numerico;
            soma += numerico;
        }
        media = (double)soma / n;
        IO.println("Soma = "+soma);
        IO.println("Media = "+media);
    }
}
