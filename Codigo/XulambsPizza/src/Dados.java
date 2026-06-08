import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Dados<T> {
    private List<T> dados;

    public Dados(List<T> lista){
        dados = lista;
    }

    public Dados(){
        dados = new LinkedList<>();
    }

    public T localizar(int numero){
        T localizado = null;
        for (int i = 0; localizado == null && i<dados.size(); i++) {
            T candidato = dados.get(i);
            if(candidato.hashCode() == numero)
                localizado = candidato;
        }
        return localizado;
    }
    
    // public void atualizarClientes() {
    //     for (Cliente cliente : clientes) {
    //         cliente.verificarCategoria();
    //     }    
    // }

    public int add(T dado){
        dados.add(dado);
        return dados.size();
    }

    public String relatorio(){
        StringBuilder relat = new StringBuilder("Relatório:\n"); 
        for (T dado : dados) {
            relat.append(dado+"\n");
        }
        return relat.toString();
    }

    public String relatorioOrdenado(Comparator<T> comparador){
        StringBuilder relat = new StringBuilder();
        Collections.sort(dados, comparador::compare);
        for (T dado : dados) {
            relat.append(dado);
            relat.append("\n~~~~~~~~~~~~~~~~~~~~\n");
        }
        return relat.toString();
    }
}
