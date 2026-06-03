import java.text.Collator;

public class Sanduiche implements IProduto, IPersonalizavel {

    private static final int MAX_INGREDIENTES = 5;
    private static final double PRECO_BASE = 18d;
    private static final double VALOR_ADICIONAL = 3d;
    private static final double VALOR_COMBO = 7d;

    private String descricao;
    private int quantidadeIngredientes;
    private boolean combo;

    private void setUp(int adicionais){
        combo = false;
        adicionarIngredientes(adicionais);
    }

    /**
     * Construtor padrão. Cria uma pizza sem adicionais.
     */
    public Sanduiche() {
        setUp(0);
    }

    /**
     * Cria uma pizza já com ingredientes adicionais.
     * Caso o valor seja inválido (<1 ou >8), a pizza será criada
     * sem adicionais.
     * @param adicionais Inteiro com a quantidade de adicionais da pizza.
     */
    public Sanduiche(int adicionais) {
        setUp(adicionais);
    }

    /**
     * Retorna o valor final da pizza, incluindo seus adicionais.
     * 
     * @return Double com o valor final da pizza.
     */
    @Override
    public double valorAPagar() {
        double base = PRECO_BASE + valorAdicionais();
        return combo ? base + VALOR_COMBO : base; 
    }

    public boolean setCombo(boolean combo){
        this.combo = combo;
        return this.combo;
    }

    private double valorAdicionais(){
        return quantidadeIngredientes * VALOR_ADICIONAL;
    }

    private boolean podeAdicionar(int quantos){
        int novosIngredientes = quantidadeIngredientes + quantos;
        return (quantos >= 0 && novosIngredientes <=MAX_INGREDIENTES);
    }

    /**
     * Tenta adicionar ingredientes na pizza. Caso a adição seja inválida
     * (ultrapassando limites ou com valores negativos), mantém
     * a quantidade atual de ingredientes. Atualiza a descrição.
     * Retorna a quantidade de ingredientes após a execução do método.
     * 
     * @param quantidade Quantos ingredientes a serem adicionados (>0)
     * @return Quantos ingredientes a pizza tem após a execução
     */
    @Override
    public int adicionarIngredientes(int quantidade) {     
        if (podeAdicionar(quantidade)) {
            quantidadeIngredientes += quantidade;
            atualizarDescricao();
        }
        return quantidadeIngredientes;
    }
    private void atualizarDescricao(){

            descricao = String.format("Sanduíche com %d adicionais", quantidadeIngredientes);
            if(combo)
                descricao += " e combo com fritas";
            
    }
    /**
     * Nota simplificada de compra: descrição da pizza, dos ingredientes e do preço.
     * 
     * @return String no formato "<DESCRICAO>, no valor de <VALOR>"
     */
    @Override
    public String toString() {
        double valor = valorAPagar();
        double adicionais = valorAdicionais();
        String nota =  String.format("%s \n\tPreço inicial: R$ %.2f \n\tAdicionais: R$ %.2f \n",   
                                    descricao, PRECO_BASE, adicionais);
        if(combo)                                    
            nota += String.format("\tCombo fritas: R$%.2f\n",VALOR_COMBO);

        return String.format("%sVALOR A PAGAR: R$ %.2f", nota, valor);
    }

    @Override
    public int maximoIngredientes() {
       return MAX_INGREDIENTES;
    }

    @Override
	public int compareTo(IProduto outro){
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.SECONDARY);
		return comparador.compare(this.toString(), outro.toString());
    }
}
