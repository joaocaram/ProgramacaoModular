/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * App com diversas demonstrações de streams (sem um sistema 'de verdade')
 */
public class AppStream {
    static Random aleat = new Random(42);
    static String nomeArq = "clientes.txt";
    static LocalDate today = LocalDate.now();
    static Data hoje = new Data(today.getDayOfMonth(), today.getMonthValue());

    /**
     * Carrega o arquivo de clientes em um hash map, criando ainda uma lista de pedidos aleatórios para cada
     * @param nomeArq Arquivo com os clientes
     * @return HashMap com o nome como chave e o cliente como valor
     * @throws IOException Arquivo não encontrado
     */
    private static HashMap<String, Cliente> carregarClientes(String nomeArq) throws IOException {
        Scanner leitor =  new Scanner(new File(nomeArq));
        HashMap<String,Cliente>  clientes = new HashMap<>();
        while(leitor.hasNextLine()){
            String nome = leitor.nextLine();
            Cliente novo =  new Cliente(nome);
            
            int quantosPed = aleat.nextInt(10)+1;
            for (int i = 0; i < quantosPed; i++) {
                int quantasComidas = aleat.nextInt(6)+1;
                novo.addPedido(criarPedido(quantasComidas));
            }
            
            clientes.put(nome, novo);
        }

        leitor.close();
        return clientes;
    }
    /**
     * Cria uma refeição aleatoriamente (pizza ou sanduíche) com ingredientes aleatórios
     * @return Comida com ingredientes
     */
    public static Comida criarRefeicao(){
        Ingrediente[] ingr= {new Ingrediente("Bacon", 2, 3.95),
                             new Ingrediente("Palmito", 1, 4.95),
                             new Ingrediente("Calabresa", 2, 2.95)};

        Comida nova = null;
        int tipo = aleat.nextInt(3);
        int quantos = aleat.nextInt(3);
        
        switch(tipo){
            case 0: nova = new Pizza();
            break;
            case 1: nova = new Pizza(true);
            break;
            case 2: nova= new Sanduiche();            
        }
        for (int i = 0; i < quantos; i++) {
            int pos = aleat.nextInt(3);
            nova.addIngrediente(ingr[pos]);
        }
        return nova;
    }
    
    /**
     * Cria um pedido com 'quantasComidas' aleatórias nos itens
     * @param quantasComidas Quantidade de itens do pedido
     * @return Pedido com diversas comidas aleatórias
     */
    public static Pedido criarPedido(int quantasComidas){
        Comida nova = criarRefeicao();
        Data qualquerData = new Data(aleat.nextInt(28)+1, aleat.nextInt(12)+1);
        Pedido novo = new Pedido(qualquerData, nova);
        for (int i = 1; i < quantasComidas; i++) {
            novo.addItem(criarRefeicao());
        }        
        return novo;
    }

    /**
     * Utilizade para 'limpar' o console (terminal VT-100)
     */
    public static void limparTela(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        TreeSet<Cliente> todosOsClientes = new TreeSet<>(carregarClientes(nomeArq).values());
        todosOsClientes.first().addPedido(new Pedido(new Data(28,12), new Pizza())); //pedido com data fixa para verificação

        List<Pedido> todosOsPedidos;
        
        limparTela();

        //collect e flatMap: cria uma lista a partir da stream da árvore de clientes
        System.out.println("Juntando todos os pedidos de todos os clientes:");
        todosOsPedidos =  todosOsClientes.stream()                  //pego um stream de clientes da árvore
                          .flatMap(c-> c.getPedidos().stream())     //mapeia cada cliente para um stream dos seus pedidos
                          .collect(Collectors.toList());            //coleta o mapa e transforma numa lista.
    
        System.out.println(todosOsPedidos.size());
        teclado.nextLine();                               
        limparTela();
        
        //anyMatch: verificando existência(match) de clientes por nome
        System.out.println("Existem clientes João e Reinaldo?");

        boolean existe = todosOsClientes.stream()
                                        .anyMatch(c-> c.getNome().equals("João"));
        System.out.println(existe);
        
        existe = todosOsClientes.stream()
                                .anyMatch(c-> c.getNome().equals("Reinaldo"));
      
        System.out.println(existe);
        
        teclado.nextLine();                               
        limparTela();

        //allMatch: verificando se todos os pedidos atendem a uma condição
        //varie o valor para ver as mudanças
        System.out.println("Todos os pedidos são de mais do que R$100?");
        System.out.println(todosOsPedidos.stream()
                                         .allMatch(p-> p.valorTotal()>100.0));
        teclado.nextLine();                               
        limparTela();
        
        //average: média do total gasto por cliente.
        System.out.println("Valor médio do total de pedidos por cliente");
        double media = todosOsClientes.stream()
                                      .mapToDouble(Cliente::totalEmPedidos)
                                      .average()
                                      .getAsDouble();
        System.out.println("R$ "+String.format("%.2f", media));
        teclado.nextLine();                               
        limparTela();
        
        //filter: primeiro por quantidade de pedidos, depois pelo início do nome do cliente
        System.out.println("Quantos clientes com mais do que 3 pedidos?");
        int quantos = (int) todosOsClientes.stream()
                                           .filter(c->c.getPedidos().size()>3)
                                           .count();
        int todos = todosOsClientes.size();
        System.out.println(quantos +" de "+ todos);

        System.out.println("Clientes com nome iniciando em R:");
        todosOsClientes.stream()
                       .filter(c->c.getNome().startsWith("R"))
                       .forEach(c->System.out.println(c.getNome()));

        teclado.nextLine();                               
        limparTela();
        
        //findFirst: primeiro da stream de pedidos
        System.out.println("Primeiro pedido de todos");
        Pedido primeiro = todosOsPedidos.stream()
                                        .findFirst()
                                        .get();
        System.out.println(primeiro.relatorio());
        teclado.nextLine();                               
        limparTela();                          
              
        //forEach: após filtrar, exibe id e valor dos pedidos acima de R$100
        System.out.println("Pedidos com valor maior que R$100");
        todosOsPedidos.stream()
                      .filter(p->p.valorTotal()>100.0)
                      .forEach(p-> 
                       System.out.println(String.format("%02d",p.getId())
                      + " - R$ "+ String.format("%.2f",p.valorTotal())));
        teclado.nextLine();                               
        limparTela();

        //max: o maior valor total gasto por um cliente da loja
        System.out.println("Maior valor total em pedidos de um cliente");
        double total = todosOsClientes.stream()
                                        .mapToDouble(Cliente::totalEmPedidos)
                                        .max()
                                        .getAsDouble();
        System.out.println("R$ "+String.format("%.2f", total));
        teclado.nextLine();                               
        limparTela();

        //min: a menor quantidade de pedidos de um cliente
        //depois, o nome deste cliente               
        System.out.println("Menor quantidade de pedidos de um cliente");
        System.out.print(todosOsClientes.stream()
                            .map(c-> c.getPedidos().size())
                            .min( (i1,i2)-> (i1-i2 ) )
                            .get()
                           );
        
        System.out.println(" - "+todosOsClientes.stream()
                            .min((i1,i2)->(i1.getPedidos().size()-i2.getPedidos().size()))
                            .get()
                            .getNome()
                           );
        teclado.nextLine();                               
        limparTela();

        //reduce: usa a redução para verificar a data mais futura, simulando o pedido mais recente
        //reduce recebe dois valores e retorna um, do mesmo tipo, a partir da operação de redução
        System.out.println("Data do pedido mais recente?");      
        System.out.println(todosOsPedidos.stream()
                                         .map(Pedido::getData)
                                         .distinct()
                                         .reduce((x,y)-> x.maisFutura(y) ? x : y )
                                         .get()
                                         .dataFormatada()
                            );
        teclado.nextLine();                               
        limparTela();
        
        //sum: valor acumulado de todos os pedidos da loja
        System.out.println("Valor total vendido na loja:");
        double totalPedidos = todosOsPedidos.stream()
                                            .mapToDouble(Pedido::valorTotal)
                                            .sum();
        System.out.println("R$ "+String.format("%.2f", totalPedidos));
        teclado.nextLine();                               
        limparTela();

        //statistics: objeto que já detém valores de diversos agregadores
        System.out.println("Algumas estatísticas:");
        DoubleSummaryStatistics estatisticaPedidos =              
                todosOsPedidos.stream()
                              .mapToDouble(Pedido::valorTotal)
                              .summaryStatistics();
        System.out.println("Média do valor dos pedidos: R$ " + String.format("%.2f", estatisticaPedidos.getAverage()));
        System.out.println("Quantidade total de pedidos: " + String.format("%2d", estatisticaPedidos.getCount()));
        System.out.println("Pedido mais caro: R$ " + String.format("%.2f", estatisticaPedidos.getMax()));
        teclado.nextLine();                               
        limparTela();

        // //collect -> lá em cima na criação da lista
        
        //generate e limit: gera um conjunto aleatório, limitado a 10 pedidos (sem limite vai a 'infinito')
        System.out.println("Dez pedidos aleatórios");
        Stream<Pedido> dezPedidos = Stream.generate(()->new Pedido(hoje, criarRefeicao())).limit(10);
        dezPedidos.forEach(p->System.out.println(p.relatorio()));
        teclado.nextLine();                               
        limparTela();

        //concat: junta dois streams de pedidos
        System.out.println("Concatenando todos os pedidos");
        Stream<Pedido> iniciais = todosOsPedidos.stream();
        Stream<Pedido> dez = Stream.generate(()->new Pedido(hoje, criarRefeicao())).limit(10);
                                               

        Stream<Pedido> muitosPedidos = Stream.concat(iniciais, dez);
        System.out.println("Mas imprimindo só a quantidade: " + muitosPedidos.count());
        teclado.nextLine();                               
        limparTela();

        //distinct: retorna um stream contendo somente os valores distintos (um objeto de cada valor)
        System.out.println("Datas dos pedidos");
        Stream<Data> datasDiferentes = todosOsPedidos.stream()
                                                         .map(p-> p.getData())
                                                         .distinct();
        datasDiferentes.forEach(d-> System.out.println(d.dataFormatada()));
        teclado.nextLine();                               
        limparTela();
        
        //skip: salta N objetos, no caso, clientes
        System.out.println("Todos os clientes:");
        todosOsClientes.stream()
                       .forEach(c->System.out.print(c.getNome()+" "));

        Stream<Cliente> salto = todosOsClientes.stream()
                                               .skip(3);               
        
        System.out.println("\n\nPulando 3:");
        salto.forEach(c->System.out.print(c.getNome()+" "));
        
        teclado.nextLine();                               
        limparTela();
        
        //sorted: ordena um stream (no caso, pelo ordenador padrão da classe)
        System.out.println("Todos os pedidos, em ordem: ");
         todosOsPedidos.stream()
                       .sorted()
                       .forEach(p-> System.out.println( String.format("%02d",p.getId())+ " - R$ "+ String.format("%.2f",p.valorTotal())));
         teclado.nextLine();
        
         System.out.println("FIM");
         teclado.close();
    }
}
