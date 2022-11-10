
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyListShapeTest {
   
    MyList<Shape> lista;
    Circle c1, c2;
    
    @BeforeEach
    public void init(){
        lista = new MyList<>(10);
        c1 = new Circle(10);
        c2 = new Circle(5);
    }
    @Test
    public void testeMaiorObjeto(){
        
        lista.addAtEnd(c1);
        lista.addAtEnd(c2);
        assertEquals(c1, lista.greatest());
    }

    @Test
    public void tirarObjeto(){
        lista.addAtEnd(c1);
        lista.addAtEnd(c2);
        lista.removeAt(0);
        assertEquals(c2, lista.objectAt(0));

    }
}
