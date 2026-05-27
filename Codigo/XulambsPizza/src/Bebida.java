import java.security.InvalidParameterException;

public class Bebida implements IProduto{
    private EBebida tipo;

    public Bebida(EBebida tipo){
        if(tipo == null)
            throw new InvalidParameterException("Obrigatório escolher o tipo da bebida.");

        this.tipo = tipo;
    }

    @Override
	public int compareTo(IProduto outro){
		return toString().toLowerCase().compareTo(outro.toString().toLowerCase());
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
