import java.util.Collection;

public class PrecoRegular implements IPreco {

    @Override
    public double valorItens(Collection<IProduto> itens) {
        return itens.stream()
                    .mapToDouble( p -> p.valorAPagar())
                    .sum();
    }
    
}
