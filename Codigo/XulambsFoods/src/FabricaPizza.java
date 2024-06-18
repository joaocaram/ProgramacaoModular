public class FabricaPizza implements IFabrica<Comida> {

    @Override
    public Comida criar() {
        return new Pizza();
    }
    
}
