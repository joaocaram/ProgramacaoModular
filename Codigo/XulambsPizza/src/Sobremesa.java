public class Sobremesa implements IProduto{
    private ESobremesa tipo;

    public Sobremesa(ESobremesa tipo){
        if(tipo == null)
            throw new IllegalArgumentException("Obrigatório escolher o tipo da sobremesa.");

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

 
}