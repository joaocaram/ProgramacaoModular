import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeradorClientes {
    
    static final Random sorteio = new Random(42);
    static final List<String> nomes = new ArrayList<>(Arrays.asList("Ana", "Bia", "Carla", "Daniela", "Elisa", "Fabiana", "Gabriela",
                                    "Heloisa", "Ivana", "Joana", "Katia", "Luisa", "Mariana"));
    
    static final List<String> sobrenomes = new ArrayList<>(Arrays.asList("Andrade", "Bastos", "Coelho", "Diniz", "Esteves", "Frota", "Galvão",
                                         "Horta", "Ibanez", "Jobim", "Keller", "Lopes", "Matos"));
    
    public static List<Cliente> gerarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>(nomes.size() * sobrenomes.size());
        ArrayList<String> nomesCompletos = new ArrayList<>(nomes.size() * sobrenomes.size());
        Collections.shuffle(nomes);
        Collections.shuffle(sobrenomes);
        int id = 1;
        for (String n : nomes) {
            for (String s : sobrenomes) {
                nomesCompletos.add(n+" "+s);   
            }
        }
        Collections.shuffle(nomesCompletos);
        for (String n : nomesCompletos) {
                id += sorteio.nextInt(1, 7);
                clientes.add(new Cliente(id, n));   
        }
        

        return clientes;
    }
}
