import java.security.InvalidParameterException;

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
    public int compareTo(IProduto o) {
        return this.toString().compareTo(o.toString());
    }
}