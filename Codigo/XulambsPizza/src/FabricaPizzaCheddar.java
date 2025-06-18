public class FabricaPizzaCheddar implements IFabrica<Comida>{
 private static final String NOME = "pizzacheddar";
    @Override
    public Comida criar() {
        Pizza pizza = new Pizza();
        pizza.adicionarBorda(EBorda.CHEDDAR);
        return pizza;    
    }
     @Override
    public int hashCode(){
        return NOME.hashCode();
    }
    
}
