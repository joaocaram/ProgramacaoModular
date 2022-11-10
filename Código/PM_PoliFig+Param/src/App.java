import java.util.Comparator;
import java.util.Random;

public class App {
    static MyList<Shape> listaFormas = new MyList<Shape>(20);
    static Random rand = new Random(System.currentTimeMillis());
    
    public static void criarLista(){
        for(int i=0; i<10; i++){
            Shape novaForma = null;
            int tipo = rand.nextInt(4);
            double n1 = rand.nextInt(100)+1;
            double n2 = rand.nextInt(100)+1;
            switch(tipo){
                case 0: novaForma = new Circle(n1);
                break;
                case 1: novaForma = new Rectangle(n1, n2);
                break;
                case 2: novaForma = new Square(n1);
                break;
                case 3: novaForma = new Triangle(n1, n2);
                break;
            }
            listaFormas.addAtEnd(novaForma);
        }   
        Square sq = new Square(5);
        listaFormas.addAtEnd(sq);
    }
    public static void main(String[] args) throws Exception {

      PerimeterComparator perimeterComparator = new PerimeterComparator();

        criarLista();
        Shape c = new Circle(40);
       listaFormas.addAtEnd(c);
        System.out.println(listaFormas);
        Shape aMaior = listaFormas.greatest();
        System.out.println("área: "+aMaior);
        
        aMaior = listaFormas.greatest(perimeterComparator);
        System.out.println("perimetro: "+aMaior);

        aMaior = listaFormas.greatest(new Comparator<Shape>(){
            public int compare(Shape o1, Shape o2) {
                return o1.toString().compareTo(o2.toString());
            }  
        });

        System.out.println("descricao: "+aMaior);

        aMaior = listaFormas.greatest((o1,o2) -> 
                                        o1.toString().compareTo(o2.toString()));
       
        System.out.println("descricao: "+aMaior);

        
        
    }
}
