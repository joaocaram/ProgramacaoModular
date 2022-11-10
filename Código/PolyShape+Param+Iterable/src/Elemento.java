public class Elemento<T> {
    private T dados;
    public Elemento<T> proximo;

    public Elemento(T dado){
        this.dados = dado;
        this.proximo = null;
    }

    public T dado(){
        return this.dados;
    }
}
