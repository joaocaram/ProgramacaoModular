public class App {
    public static void main(String[] args) throws Exception {
        Produto prod = new Produto("Xulambs", 12, 4
                                    , 3, new Data("01/03/2023"));

        System.out.println(prod.dadosProduto());


        
    }
}
