public class FabricaComida {
    
    private static final IFlyweightComida PIZZA = new FlyweightPizza();
    private static final IFlyweightComida SANDUICHE = new FlyweightSanduiche();
    
    public static IFlyweightComida criar(String descricao){
        switch (descricao.toLowerCase()) {
            case "pizza": return PIZZA;
            case "sanduiche": return SANDUICHE;
            default: return null;
        }
    }
}
