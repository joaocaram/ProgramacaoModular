public class Ingrediente {

    protected String descricao;
    protected double preco;

    public Ingrediente(String desc, double preco){
        this.descricao = desc;
        if(preco<1) preco = 1d;
        this.preco = preco;
    }

    public double preco(){
        return this.preco;
    }

    public String toString(){
        return this.descricao;
    }
}
