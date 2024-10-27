public enum EComida {
    PIZZA(8,"Pizza", 29d, 5d),
    SANDUICHE(5,"Sanduíche", 15d, 3d);

    private int maxIngredientes;
	private String descricao;
	private  double precoBase;
    private  double valorAdicional;

    private EComida(int maxAdicionais, String desc, double base, double adicional){
        maxIngredientes = maxAdicionais;
        descricao = desc;
        precoBase = base;
        valorAdicional = adicional;
    }

    /**
     * @return the maxIngredientes
     */
    public int getMaxIngredientes() {
        return maxIngredientes;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the precoBase
     */
    public double getPrecoBase() {
        return precoBase;
    }

    /**
     * @return the valorAdicional
     */
    public double getValorAdicional() {
        return valorAdicional;
    }

    
}
