import java.util.Collection;

public class PrecoFimDeSemana implements IPreco {

    @Override
    public double valorItens(Collection<IProduto> itens) {
        double valor = itens.stream()
                            .mapToDouble( prod -> prod.valorAPagar())
                            .sum();

        
        double desconto = 0;
        if(itens.size() >= 3)
            desconto = itens.stream()
                            .min((p1,p2) -> p1.valorAPagar() > p2.valorAPagar() ? 1 : -1)
                            .map(p -> p.valorAPagar())
                            .orElse(0d);                            
        return valor - desconto;     
    }
    
}
