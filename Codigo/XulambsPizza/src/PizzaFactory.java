public class PizzaFactory implements IFactory<IProduto>{

    @Override
    public Pizza criar() {
        return new Pizza();
    }       
    
    @Override
    public int hashCode(){
        return "pizza".hashCode();
    }
}
