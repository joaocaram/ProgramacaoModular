public class FabricaSanduiche implements IFabrica<Comida> {

    @Override
    public Comida criar() {
        return new Sanduiche();
    }
    
}
