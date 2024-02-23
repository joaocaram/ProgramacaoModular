import java.util.Scanner;

public class MediaVetor {
    public static void main(String[] args) throws Exception {
        Scanner teclado;
        double[] vetor;
        int tamanhoVetor;
        double soma;
        double media;

        teclado = new Scanner(System.in);
        System.out.print("Qual o tamanho do vetor a ser lido? ");
        
        tamanhoVetor = Integer.parseInt(teclado.nextLine());
        vetor = new double[tamanhoVetor];
        System.out.println();

        for (int i = 0; i < tamanhoVetor; i++){
            System.out.print("Digite o valor " + (i + 1) + ": ");
            vetor[i] = Double.parseDouble(teclado.nextLine());
        }

        soma = 0;

        for (int i = 0; i < tamanhoVetor; i++) {
            soma += vetor[i];
        }

        media = soma / tamanhoVetor;
        System.out.println("O vetor tem "+tamanhoVetor+" elementos. A soma dos elementos é "+soma);
        System.out.println("A média dos valores do vetor é de " + String.format("%.2f", media));
        
        teclado.close();
    }
}
