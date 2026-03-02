public class App {
    public static void main(String[] args) throws Exception {
        double preco = 0;
        double margem = 0;
        do{
            preco = Double.parseDouble(IO.readln("Preço de custo? "));
            margem = Double.parseDouble(IO.readln("Margem (de 10 a 50)? "));
        }while(preco < 0 && (margem <10 || margem >50));
        Produto meuProduto = new Produto("Caderno", 
                                         preco, margem);
        IO.print(meuProduto.toString());
    }
}
