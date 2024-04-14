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

/**
 * Classe pedido local: herança abstrata de Pedido
 */
public class PedidoLocal extends Pedido {

    //#region constantes
    private static final double TAXA_SERVICO = 0.1;
    //#endregion

    /**
     * Cria um pedido local sem itens.
     */
    public PedidoLocal(){
        super("Pedido local");
    }

    /**
     * Adiciona comida, desde que o pedido esteja aberto. Caso esteja fechado ou a comida seja nula, ignora a operação.
     * @param novaComida Comida a ser adicionada (não deve ser nula)
     * @return Quantidade de comidas no pedido após executar a operação.
     */
    @Override
    public int addComida(Comida novaComida) {
        if(aberto && novaComida != null){
            itens[quantComidas] = novaComida;
            quantComidas++;
        }
        return quantComidas;
    }

    /**
     * Calcula o valor da taxa de serviço do pedido (10% do valor dos itens)
     * @return Valor da taxa de serviço do pedido (double positivo)
     */
    @Override
    public double taxa() {
        return valorItens() * TAXA_SERVICO;
    }
    
}
