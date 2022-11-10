import java.util.Comparator;

public class MyList<T extends Comparable<T>> {
    
    private T[] dados;
    private int capacidade;
    private int quantidade;
    
 
    public MyList(int maxSize){
        this.dados = (T[])new Comparable[maxSize]; 
        this.capacidade = maxSize;
        this.quantidade = 0;
        
    }

    public void addAtEnd(T newData){
        if(this.quantidade < this.capacidade){
            dados[this.quantidade] = newData;
            this.quantidade++;
        }
    }

    public T removeAt(int pos){
        T aux = null;
        if(pos < this.quantidade){
            aux = this.dados[pos];
            for(int i=pos+1; i<quantidade; i++){
                this.dados[i-1] = this.dados[i];            
            }
            this.quantidade--;
        }
        return aux;
    }

    public T removeObject(T who){
        T aux = null;
        for(int i = 0 ; i<this.quantidade; i++){
            if(dados[i].equals(who)){
                aux = dados[i];
                for(int j=i+1; j<quantidade; j++){
                    this.dados[j-1] = this.dados[j];            
                }
                this.quantidade--;
            }
        }
        return aux;
    }
  
    public T greatest(){
        T great = this.dados[0];
        for(int i=0; i<this.quantidade; i++){
            if(this.dados[i].compareTo(great)>0)
                great = this.dados[i];
        }
        return great;
    }

    public T greatest(Comparator<T> comparador){
        T great = this.dados[0];
        for(int i=0; i<this.quantidade; i++){
            if(comparador.compare(this.dados[i], great)>0)
                great = this.dados[i];
        }
        return great;
    }

    public T objectAt(int pos){
        if(pos<this.quantidade)
            return this.dados[pos];
        else
            return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.quantidade; i++) {
            sb.append(this.dados[i].toString()+"\n");
        }
        return sb.toString();
    }
}
