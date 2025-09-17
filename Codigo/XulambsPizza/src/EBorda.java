public enum EBorda {
    TRADICIONAL("Tradicional", 0d),
    REQUEIJAO("Requeijao", 7d),
    CHEDDAR("Cheddar", 10d),
    CHOCOLATE("Chocolate", 8d);

    private String descricao;
    private double valor;

    EBorda(String descricao, double valor){
        this.descricao = descricao;
        this.valor = valor;
    }
    public String descricao(){
        return descricao;
    }
    public double valor(){
        return valor;
    }
}
