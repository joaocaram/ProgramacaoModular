public class SanduicheFactory implements IFactory<IProduto>{

    @Override
    public Sanduiche criar() {
        return new Sanduiche();
    }   
    
    @Override
    public int hashCode(){
        return "sanduiche".hashCode();
    }
}