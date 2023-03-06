public class App2 {
    public static void main(String[] args) throws Exception {
        Pizza pizza = new Pizza();
        Pizza pizzaComIngredientes = new Pizza(6);

        pizzaComIngredientes.adicionarIngrediente(1);
        pizzaComIngredientes.adicionarIngrediente(10);
        pizza.retirarIngrediente(3);
        
        System.out.println(pizza.imprimirNotaDeCompra());
        System.out.println(pizzaComIngredientes.imprimirNotaDeCompra());
    }
}
