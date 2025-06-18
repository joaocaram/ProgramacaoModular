public class FabricaPizza implements IFabrica<Comida> {

    private static final String NOME = "pizza";
    @Override
    public Comida criar() {
        return new Pizza();
    }

    @Override
    public int hashCode(){
        return NOME.hashCode();
    }
    
}
