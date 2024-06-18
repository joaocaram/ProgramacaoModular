public class FabricaSanduicheBacon implements IFabrica<Comida> {

    @Override
    public Comida criar() {
        return new Sanduiche(1);
    }
    
}
