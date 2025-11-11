import java.util.LinkedList;
import java.util.List;
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

public class Cliente{
    private static int ultimoID = 0;
    private int id;
    private String nome;
    private List<Pedido> pedidos;
    private IFidelidade categoria;

    public Cliente(String nome){
        this.nome = nome;
        id = ++ultimoID;
        pedidos = new LinkedList<>();
        categoria = new XulambsJunior();
    }

    public void verificarCategoria(){
        categoria = IFidelidade.atualizarCategoria(pedidos);
    }

    public double registrarPedido(Pedido novo){
        categoria.descontoPedido(novo);
        pedidos.add(novo);
        return novo.precoAPagar();
    }

    public String resumoPedidos(){
        StringBuilder relatorio = new StringBuilder(toString()+"\n");
        for (Pedido pedido : pedidos) {
            relatorio.append(pedido+"\n");
        }
        return relatorio.toString();
    }

    public double totalEmPedidos(){
        double valor = 0d;
        for (Pedido pedido : pedidos) {
            valor += pedido.precoAPagar();
        }
        return valor;
    }

    @Override
    public String toString(){
        return String.format("Cliente nº %d: %s (%s)\nTotal gasto em %d pedidos: R$ %.2f", id, nome, categoria, pedidos.size(), totalEmPedidos());
    }

    @Override
    public int hashCode(){
        return id;
    }   
}