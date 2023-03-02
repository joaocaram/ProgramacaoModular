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
 * * Implementa a fidelidade do cliente com 20% de desconto
 */
public class FidelidadePreto implements IFidelizavel{
    static LocalDate x = LocalDate.now();
    static Data hoje = new Data(x.getDayOfMonth(), x.getMonthValue());
    static double DESCONTO = 0.20;
    static double VALOR_MUDANCA = 300;
    static int PEDIDOS_MUDANCA = 20;
    static int PERIODO_MUDANCA = 30;
   

    @Override
     /**
     * Aplica o desconto do cliente no valor recebido como parâmetro
     * @param valor Valor original do produto
     * @return double Valor a descontar no preço pago
     */
    public double desconto(double valor) {
        return valor*DESCONTO;
    }
   

    @Override
     /**
     * Verifica se o cliente permaneceu, subiu ou desceu de categoria
     * @param pedidos Lista de pedidos do cliente
     * @return IFidelizavel com a categoria atual do cliente
     */
    public IFidelizavel verificarCategoria(List<Pedido> pedidos) {

        double valorPedidos = pedidos.stream()
            .filter(p -> p.diasAtras(hoje)<=PERIODO_MUDANCA)
            .mapToDouble(Pedido::valorPago)
            .sum();

    long contPedidos = pedidos.stream()
            .filter(p -> p.diasAtras(hoje)<=PERIODO_MUDANCA)
            .count();


        if((valorPedidos>=VALOR_MUDANCA) || (contPedidos>=PEDIDOS_MUDANCA))
            return this;
        else
            return new FidelidadePrata().verificarCategoria(pedidos);

    }

    
}
