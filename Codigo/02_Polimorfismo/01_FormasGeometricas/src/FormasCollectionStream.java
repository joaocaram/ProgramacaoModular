import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/** 
 * MIT License
 *
 * Copyright(c) 2023-4 João Caram <caram@pucminas.br>
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

/**
 * Para demonstração simples do uso de coleções (JCF), mapas e fluxos de dados
 * (streams)
 */
public class FormasCollectionStream {
    static Collection<FormaGeometrica> listaFormas = new LinkedList<>();
    static Set<FormaGeometrica> conjunto = new HashSet<>();
    static Map<String, FormaGeometrica> hashFiguras = new HashMap<>();

    // #region util/menu
    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        IO.println("Tecle Enter para continuar.");
        IO.readln();
    }

    static int menuPrincipal() {
        limparTela();
        String linha = "=========================";
        IO.println("MUITAS FORMAS GEOMÉTRICAS");
        IO.println(linha);
        IO.println("CRIAR / LISTAR / LOCALIZAR");
        IO.println("1 - Criar novo conjunto");
        IO.println("2 - Adicionar forma fixa");
        IO.println("3 - Mostrar conjunto");
        IO.println("4 - Pegar elemento");
        IO.println(linha);
        IO.println("FILTROS");
        IO.println("5 - Filtrar formas por área");
        IO.println("6 - Quantidade com filtro por área");
        IO.println("7 - Filtro por tamanho e tipo da forma");
        IO.println("8 - Formas distintas por área mínima por tamanho e tipo da forma");
        IO.println(linha);
        IO.println("TOTALIZADORES");
        IO.println("9 - Maior forma em área");
        IO.println("10 - Formas em ordem pela descrição");
        IO.println("11 - Soma das áreas");
        IO.println("12 - Soma dos perímetros");
        IO.println("13 - Média dos perímetros de um tipo");
        IO.println(linha);
        IO.println("MAP / REDUCE ");
        IO.println("14 - Mostrar conjunto ordenado ");
        IO.println("15 - Mostrar subconjunto ordenado por área");
        IO.println(linha);

        IO.println("0 - Sair");
        System.out.print("\nOpção: ");
        return Integer.parseInt(IO.readln());
    }
    // #endregion

    // #region geração aleatoria
    /**
     * Cria um conjunto aleatório de formas geométricas. Para propósito de testes,
     * estamos usando um gerador aleatório de semente fixa.
     * 
     * @param tamanho Tamanho do conjunto a ser criado.
     * @return Uma Collection aleatório com a quantidade de figuras especificadas em
     *         "tamanho"
     */
    public static Collection<FormaGeometrica> criaFormasAleatorias(int tamanho,
            Collection<FormaGeometrica> colecao) {
        Random sorteio = new Random(42);

        for (int i = 0; i < tamanho; i++) {
            int tipo = 1 + sorteio.nextInt(4);
            double dimensao1 = sorteio.nextDouble(2, 10);
            double dimensao2 = sorteio.nextDouble(2, 10);
            FormaGeometrica nova = null;
            switch (tipo) {
                case 1 -> nova = new Quadrado(dimensao1);
                case 2 -> nova = new Circulo(dimensao1);
                case 3 -> nova = new Retangulo(dimensao1, dimensao2);
                case 4 -> nova = new TrianguloRetangulo(dimensao1, dimensao2);
            }
            colecao.add(nova);
        }
        return colecao;
    }

    public static Collection<FormaGeometrica> criarNovoConjunto(Collection<FormaGeometrica> colecao) {
        System.out.print("Qual o tamanho do conjunto a ser criado? ");
        int tamanho = Integer.parseInt(IO.readln());
        return criaFormasAleatorias(tamanho, colecao);

    }

    static void criarColecoes() {

        listaFormas = criarNovoConjunto(listaFormas);
        conjunto.addAll(listaFormas);
        for (FormaGeometrica forma : listaFormas) {
            hashFiguras.put(forma.getId(), forma);
        }
    }
    // #endregion

    // #region main
    public static void main(String[] args) {
        Comparator<FormaGeometrica> compArea = (f1, f2) -> f1.area() > f2.area() ? 1 : -1;
        Comparator<FormaGeometrica> compDesc = (f1, f2) -> f1.toString().compareTo(f2.toString());
        Function<FormaGeometrica, Double> getArea = (forma) -> forma.area();
        Function<FormaGeometrica, Double> getPerim = (forma) -> forma.perimetro();

        int opcao;
        criarColecoes();
        opcao = menuPrincipal();

        while (opcao != 0) {
            limparTela();
            IO.println();
            switch (opcao) {
                case 1 -> criarColecoes();
                case 2 -> adicionarFormasFixas();
                case 3 -> mostrarColecoes();
                case 4 -> acharElemento();
                case 5 -> filtroPorArea();
                case 6 -> quantidadeComFiltro();
                case 7 -> filtroPorAreaETipo();
                case 8 -> formasDistintas();
                case 9 -> maior(compArea);
                case 10 -> ordenado(compDesc);
                case 11 -> soma(getArea);
                case 12 -> soma(getPerim);
                case 13 -> perimetrosPorTipo();
                case 14 -> mapaOrdenado(compArea);
                case 15 -> mapaOrdenadoFiltrado(compArea);
                default -> opcao = 1;
            }
            pausa();
            opcao = menuPrincipal();
        }
    }

    // #region streams/colecoes
    private static void ordenado(Comparator<FormaGeometrica> comp) {
        listaFormas.stream()
                .sorted(comp)
                .forEach(f -> IO.println(f));
    }

    private static void mapaOrdenadoFiltrado(Comparator<FormaGeometrica> comparado) {
        String tipo = IO.readln("Qual tipo te interessa? ").toLowerCase();
        IO.println(
                listaFormas.stream()
                        .filter(f -> f.nome().toLowerCase().equals(tipo))
                        .sorted(comparado)
                        .map(f -> f.toString())
                        .reduce((s1, s2) -> s1.concat("\n"+s2))
                        .orElse("Conjunto vazio"));
    }

    private static void mapaOrdenado(Comparator<FormaGeometrica> comparador) {
        Collection<FormaGeometrica> valores = hashFiguras.values();
        valores.stream()
                .sorted(comparador)
                .forEach(f -> IO.println(f));
    }

    private static void perimetrosPorTipo() {

        String tipo = IO.readln("Qual tipo te interessa? ").toLowerCase();
        String str = String.format("Média dos perímetros dos %s: %.2f\n ",
                tipo,
                listaFormas.stream()
                        .filter(f -> f.nome().toLowerCase().equals(tipo))
                        .mapToDouble(f -> f.perimetro())
                        .average()
                        .orElse(0d));
        IO.println(str);
    }

    private static void soma(Function<FormaGeometrica, Double> metodo) {
        double soma = listaFormas.stream()
                .mapToDouble(f -> metodo.apply(f))
                .sum();
        IO.println(String.format("Soma: %.2f\n", soma));
    }

    private static void maior(Comparator<FormaGeometrica> comparador) {
        FormaGeometrica forma = listaFormas.stream()
                .max(comparador)
                .orElseThrow(() -> new IllegalStateException("Coleção vazia"));
        IO.println("Maior pela área:\n" + forma);

    }

    private static void formasDistintas() {
        {
            double minimo = Double.parseDouble(IO.readln("Digite a área mínima para filtrar: "));
            Predicate<FormaGeometrica> areaMinima = (forma -> forma.area() >= minimo);
            Stream<String> filtro = listaFormas.stream()
                    .filter(areaMinima)
                    .map(f -> f.nome())
                    .distinct();

            for (String forma : filtro.toList()) {
                IO.println(forma);
            }
        }
    }

    private static void filtroPorAreaETipo() {
        double minimo = Double.parseDouble(IO.readln("Digite a área mínima para filtrar: "));
        String tipo = IO.readln("Qual tipo te interessa? ").toLowerCase();
        Predicate<FormaGeometrica> areaMinima = (forma -> forma.area() >= minimo);
        
                listaFormas.stream()
                        .filter(areaMinima)
                        .filter(f -> f.nome().toLowerCase().equals(tipo))
                        .map(f -> f.toString())
                        .forEach(IO::println);
    }

    private static void quantidadeComFiltro() {
        double minimo = Double.parseDouble(IO.readln("Digite a área mínima para filtrar: "));
        Predicate<FormaGeometrica> areaMinima = (forma -> forma.area() >= minimo);
        long cont = listaFormas.stream()
                .filter(areaMinima)
                .count();

        IO.println(cont + " formas atendem ao filtro");
    }

    private static void filtroPorArea() {
        double minimo = Double.parseDouble(IO.readln("Digite a área mínima para filtrar: "));
        Predicate<FormaGeometrica> areaMinima = (forma -> forma.area() >= minimo);
        List<FormaGeometrica> filtro = listaFormas.stream()
                .filter(areaMinima)
                .toList();
        for (FormaGeometrica forma : filtro) {
            IO.println(forma);
        }
    }

    private static void acharElemento() {
        String id = IO.readln("ID da forma: ");
        FormaGeometrica procurando = hashFiguras.get(id);        
        Optional<FormaGeometrica> forma = Optional.ofNullable(procurando);

        //forma.ifPresent((f) -> IO.println(f));

        forma.ifPresentOrElse((f) -> IO.println(f),
                              ()  -> IO.println("Não tem figura com este id"));

    }

    private static void adicionarFormasFixas() {
        Quadrado quadradinho = new Quadrado(2);
        listaFormas.add(quadradinho);
        listaFormas.add(quadradinho);
        conjunto.add(quadradinho);
        conjunto.add(quadradinho);
        hashFiguras.put(quadradinho.getId(), quadradinho);
    }

    private static void mostrarColecoes() {
        limparTela();
        IO.println("LISTA");
        for (FormaGeometrica formaGeometrica : listaFormas) {
            IO.println(formaGeometrica);
        }
        pausa();
        limparTela();
        IO.println("CONJUNTO");
        for (FormaGeometrica formaGeometrica : conjunto) {
            IO.println(formaGeometrica);
        }
        pausa();
        limparTela();
        IO.println("MAPA");
        for (FormaGeometrica formaGeometrica : hashFiguras.values()) {
            IO.println(formaGeometrica);
        }
    }
    // #endregion
}
