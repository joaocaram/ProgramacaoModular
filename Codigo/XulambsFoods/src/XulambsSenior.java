import java.util.Collection;

/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
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

public class XulambsSenior implements IFidelidade{
    private static final double PCT_DESCONTO = 0.2;
    private static final int PEDIDOS_DESC_ADIC = 5;
    private static final double VALOR_DESC_ADIC = 15d;
    private static final int MIN_PEDIDOS = 20;
    private static final double MEDIA_MIN = 32d;
    private Collection<Pedido> pedidos;
    
    public XulambsSenior(Collection<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    @Override
    public double desconto(Pedido pedido) {
        double precoOriginal = pedido.precoFinal(); 
        double desconto =  precoOriginal * PCT_DESCONTO;
        int quantPedidos = pedidos.size();
        if(quantPedidos%PEDIDOS_DESC_ADIC==0)
            desconto += VALOR_DESC_ADIC;

        desconto = (desconto<=precoOriginal? desconto : precoOriginal);           
        return desconto;
    }

    @Override
    public IFidelidade atualizarCategoria() {
        int totalDePedidos = pedidos.size();
        double mediaPedidos = 0;
        for (Pedido pedido : pedidos) {
            mediaPedidos += pedido.precoFinal();
        }
        mediaPedidos /= totalDePedidos;
        
        if(totalDePedidos >= MIN_PEDIDOS || mediaPedidos >= MEDIA_MIN)
            return this;
        else
            return new XulambsPleno(pedidos);
    }
    
}
