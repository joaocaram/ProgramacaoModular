public class Retangulo {
 
    int altura;
    int largura;

    public Retangulo(int altura, int largura){
        this.altura = this.largura = 2;
        if(altura > 2){
            this.altura = altura;
        }
        if(largura > 2){
            this.largura = largura;
        }
    }

    String desenharLinhaCheia(int largura){
        String linha = "";
        for (int i = 0; i < largura; i++) {
            linha += "#";
        }
        return linha+"\n";
    }

     String desenharLinhaVazia(int largura){
        String linha = "#";
        for (int i = 0; i < largura-2; i++) {
            linha += " ";
        }
        return linha+"#\n";
    }
    public String desenhar(){
        String desenho;
        String linhaCheia = desenharLinhaCheia(largura);
        String linhaVazia = desenharLinhaVazia(largura);
        desenho = linhaCheia;
            for(int i = 0 ; i < altura-2 ; i++){
                desenho += linhaVazia;
            }
        desenho += linhaCheia;
        return desenho;
    }
    
}
