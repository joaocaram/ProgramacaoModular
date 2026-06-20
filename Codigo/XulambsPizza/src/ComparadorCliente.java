import java.util.Comparator;

public class ComparadorCliente implements Comparator<Cliente> {

    @Override
    public int compare(Cliente objeto1, Cliente objeto2) {
        return objeto1.hashCode() - objeto2.hashCode();    
    }
    
}
