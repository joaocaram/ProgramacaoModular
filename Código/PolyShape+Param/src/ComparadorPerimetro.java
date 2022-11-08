import java.util.Comparator;

public class ComparadorPerimetro implements Comparator<Forma>{

    @Override
    public int compare(Forma o1, Forma o2) {
        if(o1.perimetro()>o2.perimetro()) return 1;
        else if(o1.perimetro()<o2.perimetro()) return -1;
        else return 0;
    }
    
}
