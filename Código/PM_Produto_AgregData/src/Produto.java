
public class Produto {
    private String descricao;
    private double preco;
    private int quantidadeNoEstoque;
    private int estoqueMinimo;
    private Data dataDeValidade;
    
        
    /**
     * Inicializador privado. Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param preco Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param estoqueMinimo Estoque mínimo (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    private void init(String desc, double preco, int quant, int estoqueMinimo, Data validade){
        descricao = "Produto sem descrição";
        this.preco = 0.01;
        quantidadeNoEstoque = 0;
        this.estoqueMinimo = 0;
        if(desc.length()>=3)
            descricao = desc;
        if(preco>0)
            this.preco = preco;
        if(quant>=0)
            quantidadeNoEstoque = quant;
        if(estoqueMinimo>0)
            this.estoqueMinimo = estoqueMinimo;
        this.dataDeValidade = validade;
    }

    
    /**
     * Construtor completo. Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param preco Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param estoqueMinimo Estoque mínimo (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    public Produto(String desc, double preco, int quant, int estoqueMinimo, Data validade ){
        init(desc, preco, quant, estoqueMinimo, validade);
    }

    /**
     * Construtor sem estoque mínimo - fica considerado como 0. 
     * Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param preco Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    public Produto(String desc, double preco, int quant, Data validade ){
        init(desc, preco, quant, 0, validade);
    }

    /**
     * Indica se o produto consta no estoque, independente de seu estoque mínimo.
     * @return TRUE se quantidade no estoque for maior que 0; FALSE caso contrário
     */
    public  boolean temEstoque(){
        return ( quantidadeNoEstoque > 0 );
    }
    
    /**
     * Indica se o produto está abaixo do estoque mínimo.
     * @return TRUE se estiver abaixo do estoque mínimo; FALSE caso contrário
     */
    public boolean abaixoDoEstoqueMinimo(){
        return ( quantidadeNoEstoque < estoqueMinimo );
    }
        
    

    /**
     * Descrição em string do produto.
     *  @return String com o formato:
     * [NOME]: R$ [PRECO]
     * Estoque: atual-> [ESTOQUE ATUAL] | mínimo-> [ESTOQUE MINIMO]
     * {Mensagem sobre fora da validade} Validade: [DD/MM/AAAA]
     */
    public String dadosProduto(){
        StringBuilder aux = new StringBuilder(this.descricao + ": R$ "+this.preco+"\n");
        aux.append("Estoque: atual-> "+this.quantidadeNoEstoque+" | mínimo-> "+this.estoqueMinimo+"\n");
        if(!this.estahNaValidade())
            aux.append("FORA DA VALIDADE!!! ");
        aux.append("Validade: "+dataDeValidade.dataFormatada());
        return aux.toString();
    }

    /**
     * Retorna true ou false conforme o produto está dentro da validade ou não.
     * @return TRUE para produtos dentro da validade; FALSE caso contrário.
     */
    public boolean estahNaValidade(){
        Data hoje = new Data();
        return this.dataDeValidade.ehNaFrenteDe(hoje);
    }

    
    
}
