import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Cardapio {
    
    private static HashMap<Integer, IFactory<IProduto>> fabricas;
    private static HashMap<Integer, String> menu;

    private static StringBuilder cardapio;

    private static void configCardapio(){
        menu = new HashMap<>();

        cardapio = new StringBuilder();
        try{
            Scanner arq = new Scanner(new File("cardapio.txt"));
            while (arq.hasNextLine()) {
                String linha = arq.nextLine();
                cardapio.append(linha +"\n");
                String[] produto = linha.split("-");
                if(produto.length == 2){
                    int cod = Integer.parseInt(produto[0].trim());
                    String fabrica = produto[1].toLowerCase().replace(" ", "");
                    menu.put(cod, fabrica);
                }
            }
            arq.close();
        }catch(FileNotFoundException fex){
            cardapio.append("ERRO NO ARQUIVO DE CONFIGURAÇÃO. CHAMAR O SUPORTE");
        }
    }

    static{
        configCardapio();

        fabricas = new HashMap<>(20);
       
        //CRIANDO FÁBRICAS -- PODE SER FEITO DE MANEIRA MAIS ABSTRATA COM _REFLEXÃO_
        DoceDeLeiteFactory ddl = new DoceDeLeiteFactory();
        PizzaFactory pizzaFactory = new  PizzaFactory();
        SanduicheFactory sanduicheFactory = new SanduicheFactory();
        SanduicheComboFactory sanduicheComboFactory = new SanduicheComboFactory();
        
        IFactory<IProduto> chaComGas = ( () -> new Bebida(EBebida.CHÁ_COM_GAS));
        IFactory<IProduto> aguaComGas = ( () -> new Bebida(EBebida.ÁGUA_COM_GAS));
        IFactory<IProduto> brigadeiro = ( () -> new Sobremesa(ESobremesa.BRIGADEIRO_DE_COLHER));
        IFactory<IProduto> suco = ( () -> new Bebida(EBebida.SUCO));
        /////////////////////
        
        //ARMAZENANDO FÁBRICAS
        fabricas.put(ddl.hashCode(), ddl);
        fabricas.put(pizzaFactory.hashCode(), pizzaFactory);
        fabricas.put(sanduicheComboFactory.hashCode(), sanduicheComboFactory);
        fabricas.put(sanduicheFactory.hashCode(), sanduicheFactory);
        fabricas.put("chacomgas".hashCode(), chaComGas);
        fabricas.put("aguacomgas".hashCode(), aguaComGas);
        fabricas.put("brigadeiro".hashCode(), brigadeiro);
        fabricas.put("suco".hashCode(), suco);
        /////////////////////
   }

   public static String cardapio(){
        return cardapio.toString();
   }

    public static IProduto criar(int opcao){

        String produto = menu.get(opcao);
        int chave = 0;
        if(produto != null)
            chave = produto.hashCode();

        Optional<IFactory<IProduto>> fabrica = Optional.ofNullable(fabricas.get(chave));
        return fabrica.orElseThrow(() -> new IllegalArgumentException("Produto inválido"))
                      .criar();       
    }
}
