/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.time.LocalDate;
import java.util.List;

/**
 * Implementa a fidelidade do cliente com 10% de desconto
 */
public class FidelidadePrata implements IFidelizavel {
    static LocalDate dia = LocalDate.now();
    static Data hoje = new Data(dia.getDayOfMonth(), dia.getMonthValue(), dia.getYear());
    static double DESCONTO = 0.10;
    static double VALOR_MUDANCA = 150;
    static int PEDIDOS_MUDANCA = 10;
    static int PERIODO_MUDANCA = 30;
    private Data validade;
    private Pedido[] pedidos;

    public FidelidadePrata(Pedido[] listaPedidos){
        this.validade = hoje.acrescentaDias(PRAZO_FIDELIDADE);
        this.pedidos = listaPedidos;
    }
    
    @Override
    /**
     * Aplica o desconto do cliente no valor recebido como parâmetro
     * @param valor Valor original do produto
     * @return double Valor a descontar no preço pago
     */
    public double desconto(double valor) {
        return valor*DESCONTO;
    }

    /**
     * Verifica se a fidelidade ainda está vigente (30 dias após a atribuição)
     * @param hoje Data de hoje
     * @return Categoria de fidelidade atual
     */
    public boolean valido(Data hoje){
        return this.validade.depoisDe(hoje);
    }
    @Override
    /**
     * Verifica se o cliente permaneceu, subiu ou desceu de categoria
     * @param pedidos Lista de pedidos do cliente
     * @return IFidelizavel com a categoria atual do cliente
     */
    public IFidelizavel verificarCategoria() {
        Data validadePedido = 
        double valorPedidos=0d;
        int contPedidos = 0;
        for (Pedido pedido : pedidos) {
            if(pedido.maisRecente(hoje))
            valorPedidos+=pedido.valorPago();

        }
        
        
        long contPedidos = pedidos.stream()
                                .filter(p -> p.diasAtras(hoje)<=PERIODO_MUDANCA)
                                .count();


        if((valorPedidos>=VALOR_MUDANCA) || (contPedidos>=PEDIDOS_MUDANCA))
            return new FidelidadePreto();
        else
            return null;
    }
    
    
}
