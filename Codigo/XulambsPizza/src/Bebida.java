import java.security.InvalidParameterException;
import java.text.Collator;

public class Bebida implements IProduto{
    private EBebida tipo;

    public Bebida(EBebida tipo){
        if(tipo == null)
            throw new InvalidParameterException("Obrigatório escolher o tipo da bebida.");

        this.tipo = tipo;
    }

    

    @Override
    public double valorAPagar() {
        return tipo.valorAPagar();
    }

    @Override
    public String toString(){
        return tipo.toString();
    }

    @Override
    public int compareTo(IProduto o) {
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);
        return collator.compare(this.toString().toLowerCase(), o.toString().toLowerCase());
    }
}
