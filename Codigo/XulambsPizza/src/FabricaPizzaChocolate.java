public class FabricaPizzaChocolate implements IFabrica<Comida> {

    static final String NOME = "pizzachoco";
    @Override
    public Comida criar() {
        Pizza p = new Pizza();
        p.adicionarBorda(EBorda.CHOCOLATE);
        return p;    
    }
    
    @Override
    public int hashCode(){
        return NOME.hashCode();
    }
}
