public enum EBorda {
    TRADICIONAL(0),
    AZEITONA(5),
    CHEDDAR(10),
    CHOCOLATE(8),
    DOCE_DE_LEITE(8),
    REQUEIJÃO(7);

    double valor;

    EBorda(double preco){
        valor = preco;
    }

    public double valorBorda(){
        return valor;
    }

    public String toString(){
        return this.name().toLowerCase().replace("_", " ");
    }
}
