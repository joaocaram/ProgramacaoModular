import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
    
    private LinkedList<String> opcoes;
    private LinkedList<String> valores;
    

    public Menu(String arquivo){
        try{
            Scanner arq = new Scanner(new File(arquivo), Charset.forName("UTF-8"));
            opcoes = new LinkedList<>();
            valores = new LinkedList<>();
            while ((arq.hasNextLine())) {
                String[] linha = arq.nextLine().split(";");
                opcoes.add(linha[0]);
                valores.add(linha[1]);
            }   
            arq.close();
        }catch(IOException ie){
            System.err.println("Erro no arquivo de configuração. Menu vazio.");
        }
    }

    @Override
    public String toString(){
        StringBuilder menuCompleto = new StringBuilder();
        int i=0;
        for (String string : opcoes) {
            menuCompleto.append(String.format("%d - %s\n", i+1, string));
            i++;
        }
        menuCompleto.append("0 - Sair\n");
        return menuCompleto.toString();
    }

    public String valor(int posicao){
        String resposta;
        try{ 
            resposta =  valores.get(posicao-1);
        }
        catch(IndexOutOfBoundsException ie){
            resposta = "";
        }
        return resposta;
    }

    
}
