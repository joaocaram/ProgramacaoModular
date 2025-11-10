import java.text.NumberFormat;

public enum EBebida implements IProduto{

    ÁGUA_COM_GAS(4),
    CHÁ_COM_GAS(6),
    SUCO(9),
    TAÇA_DE_VINHO(15);

    private String nome;
    private double valor;

    EBebida(double preco){
        nome = this.name().toLowerCase().replace("_", " ");
        valor = preco;
    }
    
    @Override
    public double valorFinal() {
       return valor;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return nome+": "+moeda.format(valor);
    }
    
}
