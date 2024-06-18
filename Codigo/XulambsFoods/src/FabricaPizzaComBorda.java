public class FabricaPizzaComBorda implements IFabrica<Comida> {

    @Override
    public Comida criar() {
        return new Pizza(0, true);
    }
    
}