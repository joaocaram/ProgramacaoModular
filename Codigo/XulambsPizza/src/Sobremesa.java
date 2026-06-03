import java.security.InvalidParameterException;
import java.text.Collator;

public class Sobremesa implements IProduto{
    private ESobremesa tipo;

    public Sobremesa(ESobremesa tipo){
        if(tipo == null)
            throw new InvalidParameterException("Obrigatório escolher o tipo da sobremesa.");

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
	public int compareTo(IProduto outro){
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.SECONDARY);
		return comparador.compare(this.toString(), outro.toString());
    } 
}