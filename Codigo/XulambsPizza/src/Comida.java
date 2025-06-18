import java.text.NumberFormat;

import javax.naming.OperationNotSupportedException;

public abstract class Comida implements Comparable<Comida>{
    
    private int maxIngredientes;
    protected int quantidadeIngredientes;
    private double precoBase;
    private double valorAdicionais;
    private String descricao;

    protected Comida(String desc, int max, double base, double valorAdc){
        descricao = desc;
        maxIngredientes = max;
        precoBase = base;
        valorAdicionais = valorAdc;
    }

    @Override
    public int compareTo(Comida other){
        double precoEste = this.valorFinal();
        double precoOutro = other.valorFinal();
      
        int resposta = ( precoEste > precoOutro? 1 : -1);
        
        if(precoOutro == precoEste) 
            resposta = 0;
        
        return resposta;
    }

    private boolean podeAdicionar(int quantos) {
		int total = quantos + quantidadeIngredientes; 
		return ( total >= 0 && total <= maxIngredientes);
	}

    protected double valorAdicionais() {
		return quantidadeIngredientes * valorAdicionais;
	}

    public int adicionarIngredientes(int quantos){
		if(!podeAdicionar(quantos)){
           throw new IngredientesIncorretos(quantos+quantidadeIngredientes);
        }
        quantidadeIngredientes += quantos;
        return quantidadeIngredientes;
	}

	public int retirarIngredientes(int quantos){
		quantos *=-1;
        return adicionarIngredientes(quantos);
	}

    public abstract double valorFinal();

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
		String notinha = String.format("%s com %d ingredientes\n", descricao, quantidadeIngredientes);
		notinha+= (String.format("   Valor base : %s", moeda.format(precoBase)));
        return notinha;

    }
    
}
