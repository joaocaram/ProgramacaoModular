public class IngredientesIncorretos extends IllegalStateException{
    private int quantosIngredientes;

    public IngredientesIncorretos(int quantos){
        super("Quantidade de ingredientes inválida");
        quantosIngredientes = quantos;        
    }

    public int getQuantosIngredientes(){
        return quantosIngredientes;
    }
}
