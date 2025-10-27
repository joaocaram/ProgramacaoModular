public enum ESobremesa implements IProduto{

    BRIGADEIRO(8),
    PUDIM(10),
    DOCE_DE_LEITE(9);

    private String nome;
    private double valor;

    ESobremesa(double preco){
        nome = this.name().toLowerCase().replace("_", " ");
        valor = preco;
    }
    @Override
    public double valorFinal() {
       return valor;
    }

    @Override
    public String toString(){
        return nome+": R$ "+valor;
    }
    
}
