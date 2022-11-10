import java.util.Random;

public class App2 {

    public static void main(String[] args) throws Exception {
        MyList<String> listaFormas = new MyList<>(10);
        Random rand = new Random(System.currentTimeMillis());
        
        
        for(int i=0; i<10; i++){
            
            int tipo = rand.nextInt(4);
            double n1 = rand.nextDouble()*100;
            double n2 = rand.nextDouble()*100;
            String novaForma =  String.format("%5f",n1);
            
            listaFormas.addAtEnd(novaForma);
        }
        listaFormas.addAtEnd("Joao");
        System.out.println(listaFormas);
        String sair = "Joao";
        listaFormas.removeObject(sair);
        System.out.println(listaFormas);
       
    }
}

