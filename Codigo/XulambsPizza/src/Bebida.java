public class Bebida implements IProduto{
    private EBebida tipo;

    public Bebida(EBebida tipo){
        if(tipo == null)
            throw new IllegalArgumentException("Obrigatório escolher o tipo da bebida.");

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
