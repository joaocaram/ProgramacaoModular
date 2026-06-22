import java.util.Collection;

public class PrecoCopa implements IPreco{

    @Override
    public double valorItens(Collection<IProduto> itens) {
        double fatorPreco = 1d;
        long quantasPizzas = itens.stream()
                                 .map( prod -> prod.toString().toLowerCase())
                                 .filter( s -> s.contains("pizza"))
                                 .count();
        if(quantasPizzas > 2)
            fatorPreco = 0.85;

        return itens.stream()
                    .mapToDouble(p -> p.valorAPagar())
                    .sum() * fatorPreco;
    }
    
}
