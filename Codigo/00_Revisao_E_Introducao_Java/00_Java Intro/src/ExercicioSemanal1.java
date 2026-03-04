public class ExercicioSemanal1 {

    static int[] lerNumeros() {
        int[] vetor;
        String numero = IO.readln("Quantos números você vai digitar? ");
        int n = Integer.parseInt(numero);
        vetor = new int[n];
        return vetor;
    }

    static int somar(int[] numeros) {
        int soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }
        return soma;

    }

    static int[] paresEImpares(int[] valores) {
        int[] resposta = new int[2];
        for (int i = 0; i < valores.length; i++) {
            int resto = valores[i] % 2;
            resposta[resto]++;
        }
        return resposta;
    }

    static int[] maioresMenoresQue(int[] numeros, double valor) {
        int[] quantos = new int[2];
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > valor)
                quantos[0]++;
            else if (numeros[i] < (valor / 2))
                quantos[1]++;
        }
        return quantos;
    }

    public static void main(String[] args) {
        int[] numeros = lerNumeros();
        int soma = somar(numeros);
        double media = (double) soma / numeros.length;
        int[] quantidades = paresEImpares(numeros);
        int[] comparacaoMedia = maioresMenoresQue(numeros, media);
        IO.println("A soma é " + soma);
        IO.println("A media é " + media);
        IO.println("São " + quantidades[0] + " pares e " + quantidades[1] + " ímpares");
        IO.println(comparacaoMedia[0] + " maiores que a média");
        IO.println(comparacaoMedia[1] + " menores que a média");

    }
}
