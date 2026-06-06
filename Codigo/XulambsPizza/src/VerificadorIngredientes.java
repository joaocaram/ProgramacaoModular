public class VerificadorIngredientes implements IPersonalizavel{

    private final int maxIngredientes;
    private final String produto;
    private int quantidadeIngredientes;

    public VerificadorIngredientes(int maximo, String produto){
        if(maximo < 0)
            throw new IllegalArgumentException("Ingredientes não podem ser em quantidade negativa");
        if(produto == null || produto.length() == 0)
            throw new IllegalArgumentException("Obrigatório descrição do produto para verificador");
        maxIngredientes = maximo;
        this.produto = produto;
        quantidadeIngredientes = 0;
    }

    private boolean podeAdicionar(int quantos){
        if(quantos < 0 )
            throw new IllegalArgumentException("Não se pode adicionar quantidade negativa de ingredientes");
        int novosIngredientes = quantidadeIngredientes + quantos;
        return (novosIngredientes <= maxIngredientes);
    }



    @Override
    public int adicionarIngredientes(int quantos) {
        if(!podeAdicionar(quantos)){
            throw new IllegalStateException("Máximo de ingredientes excedido: "+maxIngredientes);
        }
        
        quantidadeIngredientes +=  quantos;
        return quantidadeIngredientes;
    }

    @Override
    public int maxIngredientes() {
        return maxIngredientes;
    }

    @Override
    public String getNome() {
        return produto;
    }

    public int quantidadeIngredientes(){
        return quantidadeIngredientes;
    }
    
}
