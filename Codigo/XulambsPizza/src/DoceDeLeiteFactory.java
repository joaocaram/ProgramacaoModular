public class DoceDeLeiteFactory implements IFactory<IProduto> {

    @Override
    public Sobremesa criar() {
        return new Sobremesa(ESobremesa.DOCE_DE_LEITE);
    }
    
    @Override
    public int hashCode(){
        return "docedeleite".hashCode();
    }
}
