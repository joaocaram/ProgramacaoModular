import java.text.NumberFormat;

public enum ESobremesa implements IProduto{

    BRIGADEIRO_DE_COLHER(8),
    PUDIM(10),
    DOCE_DE_LEITE(9);

    private String nome;
    private double valor;

    ESobremesa(double preco){
        nome = this.name().toLowerCase().replace("_", " ");
        valor = preco;
    }
    
    @Override
    public double valorAPagar() {
       return valor;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return nome+": "+moeda.format(valor);
    }
    
}
