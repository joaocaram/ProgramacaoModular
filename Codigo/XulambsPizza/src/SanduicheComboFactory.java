public class SanduicheComboFactory implements IFactory<IProduto>{

    @Override
    public Sanduiche criar() {
        Sanduiche sanduiche = new Sanduiche();
        sanduiche.setCombo(true);
        return sanduiche;
    }   
    
    @Override
    public int hashCode(){
        return "sanduichecombo".hashCode();
    }
}
