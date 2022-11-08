

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaTest {
    Lista<Forma> lista;
    Forma f1;
    
    @BeforeEach
    public void init(){
        lista = new Lista<Forma>();
        f1 = new Circulo(10);
        
    }
    
    @Test
    public void inserirNoFimDaLista(){
        lista.inserirNoFim(f1);
        assertEquals(1, lista.tamanho());
    }

    @Test
    public void procurarNaLista(){
        Forma f2 = new Quadrado(12);
        Forma f3 = new Circulo(5);
        lista.inserirNoFim(f3);
        lista.inserirNoFim(f1);
        lista.inserirNoFim(f2);
        Circulo teste = new Circulo(10);
        assertNotEquals(f3, lista.localizar(teste));
    }

    @Test
    public void procurarMaiorElemento(){
        Forma f2 = new Quadrado(20);
        lista.inserirNoFim(f1);
        lista.inserirNoFim(f2);
        ComparadorPerimetro comparador = new ComparadorPerimetro();
        assertEquals(f2, lista.maiorElemento(comparador));
        assertEquals(f2, lista.maiorElemento());
    }

    @Test
    public void procurarMaiorElementoComComparador(){
        Forma f2 = new Quadrado(20);
        ComparadorPerimetro comparador = new ComparadorPerimetro();
        
        Comparator<Forma> x = new Comparator<Forma>() {
            public int compare(Forma a, Forma b){
                return a.descricao.compareTo(b.descricao);
            }
        };
      
        
        lista.inserirNoFim(f1);
        lista.inserirNoFim(f2);
        assertEquals(f2, lista.maiorElemento((o1,o2) -> (o1.descricao.compareTo(o2.descricao))));
    }
}
