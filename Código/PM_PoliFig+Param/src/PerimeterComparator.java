import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape>{

    @Override
    public int compare(Shape o1, Shape o2) {
        if(o1.perimetro()>o2.perimetro())
            return 1;
        else if(o1.perimetro()<o2.perimetro())
                return -1;
        return 0;
    }
    
}
