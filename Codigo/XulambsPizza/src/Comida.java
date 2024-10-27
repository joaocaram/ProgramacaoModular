public class Comida implements IComida{
	
    private IComida item;

	public Comida(EComida escolha) {
        switch (escolha) {
            case PIZZA -> item = new Pizza();
            case SANDUICHE -> item = new Sanduiche();
        }
	}

    public Comida(IComida escolha) {
        item = escolha;
	}

	@Override
	public int adicionarIngredientes(int quantos) {
		return item.adicionarIngredientes(quantos);
	}

	public String notaDeCompra() {
        return item.notaDeCompra();
		
	}

    @Override
    public double valorFinal() {
        return item.valorFinal();
    }

    @Override
    public double valorAdicionais() {
        return item.valorAdicionais();
    }
}


