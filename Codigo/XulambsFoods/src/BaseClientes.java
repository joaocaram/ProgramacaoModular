import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

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
public class BaseClientes {
    private HashMap<Integer, Cliente> clientes;
    private Cliente[] clientesOrdenados;
    
    public BaseClientes(int capacidade){
        if(capacidade<16)
            capacidade = 16;
        clientes = new HashMap<>(capacidade);

    }
    public void ordenar(Comparator<Cliente> comparador){
        clientesOrdenados =  new Cliente[clientes.size()]; 
        clientesOrdenados = clientes.values().toArray(clientesOrdenados);
        Arrays.sort(clientesOrdenados, comparador);
    }

    public String relatorio() {
        StringBuilder relat = new StringBuilder("Resumo dos clientes:\n");    
        for (Cliente cliente : clientesOrdenados) {
                relat.append("\n"+cliente+"\n");
        }
        return relat.toString();
    }

    public Cliente localizar(int idCli) {
            return clientes.get(idCli);
    }

    public void cadastrar(Cliente novoCliente) {
        clientes.put(novoCliente.hashCode(), novoCliente);
    }
}
