import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseDados<T> {
    // R E F A T O R A Ç Ã O
    private Map<Integer, T> dados;

    public BaseDados(int size){
        if(size <=0)
            throw new IllegalArgumentException();
        dados = new HashMap<>(size);
    }

    public int put(T novo){
        dados.put(novo.hashCode(), novo);
        return dados.size();
    }

    public int size(){
        return dados.size();
    }

    public T get(int key){
        return dados.get(key);
    }

    public String simpleReport(){
        StringBuilder sb = new StringBuilder("Relatório de "+size()+ " dados:\n");
        for (T dado : dados.values()) {
            sb.append(dado+"\n\n");
        }
        return sb.toString();
    }

    public String sortedReport(Comparator<T> comparador){
        List<T> lista = new ArrayList<>(dados.values());
        Collections.sort(lista, comparador);

        StringBuilder sb = new StringBuilder("Relatório ordenado de "+size()+ " dados:\n");
        for (T dado : lista) {
            sb.append(dado+"\n\n");
        }
        return sb.toString();
    }

    public void processData(Consumer<T> action){
        for (T elemento : dados.values()) {
            action.accept(elemento);            
        }
    }

}
