public class FabricaSanduiche implements IFabrica<Comida>{
 private static final String NOME = "sanduiche";
    @Override
    public Comida criar() {
        return new Sanduiche();    
    }
    
     @Override
    public int hashCode(){
        return NOME.hashCode();
    }
}
