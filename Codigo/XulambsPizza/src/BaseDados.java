import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseDados<T> {
    private Map<Integer, T> dados;
    private List<T> dadosEmLista;

    public BaseDados(int quantidade){
        dados = new HashMap<>(quantidade);
        dadosEmLista = new ArrayList<>(quantidade);
    }

    public T put(T novoItem){
        dadosEmLista.add(novoItem);
        return dados.put(novoItem.hashCode(), novoItem);
    }

    public T get(int codigo){
        return dados.get(codigo);
    }

    public String report(){
        StringBuilder sb = new StringBuilder();
        for (T dado : dadosEmLista) {
            sb.append("\n"+dado+"\n");
        }
        return sb.toString();
    }

    public String sortedReport(Comparator<T> comparator){
        StringBuilder sb = new StringBuilder();
        List<T> dadosOrdenados = new ArrayList<>(dadosEmLista.size());
        dadosOrdenados.addAll(dadosEmLista);

        dadosOrdenados.sort(comparator);
        for (T dado : dadosOrdenados) {
            sb.append("\n"+dado+"\n");
        }
        return sb.toString();
    }

    public String filteredReport(Predicate<T> condition){
        StringBuilder sb = new StringBuilder();
        
        for (T dado : dadosEmLista) {
            if(condition.test(dado))
                sb.append("\n"+dado+"\n");
        }
        return sb.toString();
    }

    public double aggregator(Function<T,Double> extratora) {
        double resultado = 0;
        for (T dado : dadosEmLista) {
            resultado += extratora.apply(dado); 
        }
        return resultado;
    }

    public void update(Consumer<T> updater){
        for (T dado : dadosEmLista) {
            updater.accept(dado); 
        }
    }
}
