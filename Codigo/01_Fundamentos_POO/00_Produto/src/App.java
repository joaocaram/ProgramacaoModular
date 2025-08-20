import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       Scanner teclado = new Scanner(System.in);
       Produto prod1 = new Produto("Chá mate com gás", 2.99, 0.3);
       System.out.println(prod1.toString());

        teclado.close();
    }
}
