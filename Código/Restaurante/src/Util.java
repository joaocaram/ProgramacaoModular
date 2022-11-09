import java.util.Random;

public class Util {
    
    static Random sorteio = new Random();
    
    private Util(){

    }

    public static void quicksort(IOrdenavel[] dados, int inicio, int fim){        	
        if(inicio>=fim)            
		    return;        
	    else{
		    int particao = particao(dados, inicio, fim);
		    quicksort(dados, inicio, particao-1);            			
            quicksort(dados, particao+1, fim);        
	    }    
    }

    static int particao(IOrdenavel[] dados, int inicio, int fim){
        int posicao = inicio-1;
        int posPivot = fim - sorteio.nextInt(1+(fim-inicio)/2);
        trocar(dados, fim, posPivot);
        IOrdenavel pivot = dados[fim];
        for (int i = inicio; i < fim; i++) {
            if(!dados[i].maiorQue(pivot)){
                posicao++;                
                trocar(dados, posicao, i);            
            }        
        }        
        posicao++;        
        trocar(dados, posicao, fim);        
        return posicao;
    }

    static void trocar(Object[] vetor, int pos1, int pos2){
        Object aux = vetor[pos1];
        vetor[pos1] = vetor[pos2];
        vetor[pos2] = aux;
    }


}
