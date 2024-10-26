import java.text.NumberFormat;

public abstract class Comida {

	private int maxIngredientes;
	private String descricao;
	private double precoBase;
	private double valorAdicional;
	protected int quantidadeIngredientes;

	protected Comida(String desc, int maxAdicionais, double valorBase, double valorAdicional) {
		maxIngredientes = maxAdicionais;
        descricao = desc;
        precoBase = valorBase;
        this.valorAdicional = valorAdicional;
        quantidadeIngredientes = 0;

	}

	/**
     * Faz a verificação de limites para adicionar ingredientes na pizza. Retorna TRUE/FALSE conforme seja possível ou não adicionar
     * esta quantidade de ingredientes.
     * @param quantos Quantidade de ingredientes a adicionar.
     * @return TRUE/FALSE conforme seja possível ou não adicionar esta quantidade de ingredientes.
     */
	protected boolean podeAdicionar(int quantos) {
		return (quantos>0 && quantos+quantidadeIngredientes<=maxIngredientes);
	}

	protected double valorAdicionais(){
        return quantidadeIngredientes*valorAdicional;
    }

	/**
     * Tenta adicionar ingredientes na pizza. Caso a adição seja inválida (ultrapassando limites ou com valores negativos), mantém 
     * a quantidade atual de ingredientes. Retorna a quantidade de ingredientes após a execução do método.
     * @param quantos Quantos ingredientes a serem adicionados (>0)
     * @return Quantos ingredientes a pizza tem após a execução
     */
	public int adicionarIngredientes(int quantos) {
		if(podeAdicionar(quantos)){
            quantidadeIngredientes += quantos;
        }
        return quantidadeIngredientes;
	}

	public String notaDeCompra() {
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s (%s) com %d ingredientes (%s)", 
                                    descricao, moeda.format(precoBase), 
                                    quantidadeIngredientes, moeda.format(valorAdicionais()));
	}

	public abstract double valorFinal();

}
