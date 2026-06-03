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
	public int compareTo(IProduto outro){
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.SECONDARY);
		return comparador.compare(this.toString(), outro.toString());
    }

    @Override
    public double valorAPagar() {
        return tipo.valorAPagar();
    }

    @Override
    public String toString(){
        return tipo.toString();
    }


}
