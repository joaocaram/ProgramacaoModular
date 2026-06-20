import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Dados<T> {
    private Map<Integer, T> dados;

    public Dados(List<T> lista){
        dados = new HashMap<>(lista.size());
        for (T t : lista) {
            dados.put(t.hashCode(), t);
        }
    }

    public Dados(int tamanho){
        dados = new HashMap<>(tamanho);
    }

    public T localizar(int numero){
        return dados.get(numero);
    }
    
    //Interface Predicate
    public String relatorioFiltrado(Predicate<T> condicao){
        StringBuilder relat = new StringBuilder("Relatório:\n"); 
        for (T dado : dados.values()) {
            if(condicao.test(dado)){
                relat.append(dado+"\n");
            }
        }
        return relat.toString();
    }
    
    //Interface Function
    public double totalizar(Function<T, Double> funcao){
        double valor = 0;
        for (T dado : dados.values()) {
            valor += funcao.apply(dado);
         }
        return valor;
    }

    //Interface Consumer
    public void processar(Consumer<T> funcao){
        for (T dado : dados.values()) {
            funcao.accept(dado);
         }
    }
        
    public int add(T dado){
        dados.put(dado.hashCode(),dado);
        return dados.size();
    }

    public String relatorio(){
        StringBuilder relat = new StringBuilder("Relatório:\n"); 
        for (T dado : dados.values()) {
            relat.append(dado+"\n");
        }
        return relat.toString();
    }

    //Interface Comparator
    public String relatorioOrdenado(Comparator<T> comparador){
        StringBuilder relat = new StringBuilder();
        List<T> valores = new ArrayList<>(dados.values());
        Collections.sort(valores, comparador::compare);
        for (T dado : valores) {
            relat.append(dado);
            relat.append("\n~~~~~~~~~~~~~~~~~~~~\n");
        }
        return relat.toString();
    }
}
