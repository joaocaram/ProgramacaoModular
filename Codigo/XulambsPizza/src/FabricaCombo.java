public class FabricaCombo implements IFabrica<Comida> {

     private static final String NOME = "combo";
    @Override
    public Comida criar() {
        return new Sanduiche(true);    
    }
    
     @Override
    public int hashCode(){
        return NOME.hashCode();
    }
}
