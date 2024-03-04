import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       Scanner teclado = new Scanner(System.in);
        Produto prod1 = new Produto();
        int quantidade;
        prod1.registrar("Chá mate com gás", 2.99);
        System.out.print("Quantas unidades você quer comprar? ");
        quantidade = Integer.parseInt(teclado.nextLine());
        System.out.println(quantidade+ " unidades de "+prod1.descricao+
                            " custam R$ "+prod1.valorLote(quantidade));

        teclado.close();
    }
}
