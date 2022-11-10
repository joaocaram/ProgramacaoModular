import java.util.Comparator;

public class Lista<T extends IOrdenavel>{
    Elemento<T> primeiro, ultimo;
    int tamanho;

    public Lista(){
        this.primeiro = new Elemento<T>(null);
        this.ultimo = this.primeiro;
        this.tamanho=0;
    }

    public void inserirNoFim(T dado){
        Elemento<T> novoElemento = new Elemento<T>(dado);
        this.ultimo.proximo = novoElemento;
        this.ultimo = novoElemento;
        this.tamanho++;
    }

    public int tamanho(){
        return this.tamanho;
    }
    
    public T localizar(T quem){
        Elemento<T> aux = this.primeiro.proximo;
        while(aux!=null){
            if(quem.equals(aux.dado()))
                return aux.dado();
            else
                aux = aux.proximo;
        }
        return null;
    }

    public T maiorElemento(){
        Comparator<T> c = (o1,o2)-> 
                              o1.toString().compareTo(o2.toString());
        return maiorElemento(c);
    }
    
    // public T maiorElemento(){
    //     if(tamanho>0){
    //         T maior = this.primeiro.proximo.dado();
    //         Elemento<T> aux = this.primeiro.proximo;
    //         while(aux!=null){
    //             if(aux.dado().maiorQue(maior))
    //                 maior = aux.dado();
    //             aux = aux.proximo;
    //         }
    //         return maior;
    //     }
    //     else 
    //         return null;
    // }

    public T maiorElemento(Comparator<T> comparador){
        if(tamanho>0){
            T maior = this.primeiro.proximo.dado();
            Elemento<T> aux = this.primeiro.proximo;
            while(aux!=null){
                if(comparador.compare(aux.dado(), maior)>0)
                    maior = aux.dado();
                aux = aux.proximo;
            }
            return maior;
        }
        else 
            return null;
    }

   
}
