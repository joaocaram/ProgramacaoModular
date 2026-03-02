public class AppRetangulo {

    public static void main(String[] args) {

        Retangulo meuRetangulo;
        int altura = Integer.parseInt(IO.readln("Altura? "));
        int largura = Integer.parseInt(IO.readln("Largura? "));
        meuRetangulo = new Retangulo(altura, largura);
            
        IO.println(meuRetangulo.desenhar());
    }
    
}
