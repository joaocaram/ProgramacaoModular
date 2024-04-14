import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    static double totalVendido;
    static Scanner teclado;

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println("🍔 XULAMBS FOODS - v0.31 🍕");
        System.out.println("=====================");
    }

    static int MenuPrincipal() {
        int opcao;
        cabecalho();
        System.out.println("1 - Abrir Pedido");
        System.out.println("2 - Total Vendido Hoje");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static int MenuComida() {
        int opcao;
        cabecalho();
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduiche");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static void relatorioTotalVendido() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        cabecalho();
        String hoje = LocalDate.now().format(df);
        System.out.println("Total vendido hoje (" + hoje + "): R$ " + String.format("%.2f", totalVendido));
        pausa();
    }

    static Pedido criarPedido() {
        Pedido novoPedido = new Pedido();
        Comida novaComida = criarComida();

        if (novaComida != null) {
            do {
                novoPedido.addComida(novaComida);
                System.out.println(novaComida.relatorio() + " adicionado ao pedido.");
                pausa();
                novaComida = criarComida();
            } while (novaComida != null);
            novoPedido.fecharPedido();
        } else
            novoPedido = null;

        return novoPedido;
    }

    static boolean querBordaRecheada(){
        String querBorda="";
        System.out.print("\nPizza com borda recheada (S/N)? ");
        querBorda = teclado.nextLine();
        return querBorda.toLowerCase().equals("s");
    }
    static Comida criarComida() {
        int tipoComida = MenuComida();
        Comida novaComida = null;
        switch (tipoComida) {
            case 1:
                if(querBordaRecheada())
                    novaComida = new Pizza(0,true);
                else   
                    novaComida = new Pizza();
                break;
            case 2:
                novaComida = new Sanduiche();
                break;
        }

        if (novaComida != null) {
            System.out.print("Deseja quantos adicionais? ");
            int adicionais = Integer.parseInt(teclado.nextLine());
            novaComida.adicionarIngredientes(adicionais);
        }

        return novaComida;
    }

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        totalVendido = 0d;
        int opcao;
        Pedido pedidoAtual;
        do {
            opcao = MenuPrincipal();
            switch (opcao) {
                case 1:
                    pedidoAtual = criarPedido();
                    if (pedidoAtual != null) {
                        totalVendido += pedidoAtual.precoFinal();
                        System.out.println("Pedido fechado: ");
                        System.out.println(pedidoAtual.relatorio());
                        pausa();
                    }
                    break;
                case 2:
                    relatorioTotalVendido();
                    break;
            }
        } while (opcao != 0);
        System.out.println("FLW VLW VLT SMP ABS 🤙");
        teclado.close();
    }
}
