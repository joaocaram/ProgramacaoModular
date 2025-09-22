public enum EBorda {
    TRADICIONAL("Tradicional", 0d),
    CHOCOLATE("Chocolate", 8d),
    REQUEIJAO("Requeijao", 7d),
    CHEDDAR("Cheddar", 10d);

    private String descricao;
    private double valor;

    EBorda(String desc, double val){
        descricao = desc;
        valor = val;
    }

    public double valor(){
        return valor;        
    }

    public String descricao(){
        return descricao;
    }
}
