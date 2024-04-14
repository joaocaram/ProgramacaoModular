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
 * Classe pedido delivery: herança abstrata de Pedido
 */
public class PedidoDelivery extends Pedido{
    
    //#region constantes
    private static final int MAX_COMIDAS = 10;
    private static final double VALORES_TAXA[] = {0d, 5d, 8d};
    private static final double DISTANCIAS_ENTREGA[] = {4d, 8d, Double.MAX_VALUE};
    //#endregion

    //#region atributos
    private double distancia;
    //#endregion

    //#region construtores

    /**
     * Construtor de um pedido para entrega: recebe e registra a distância, que deve ser no mínimo 0.1km
     * @param dist Distância da entrega em km (mínimo 0.1km)
     */
    public PedidoDelivery(double dist){
        super("Pedido para entrega");
        distancia = 0.1;
        if(dist > 0.1)
            distancia = dist;
    }
    //#endregion

    //#region métodos de negócio

    /**
     * Adiciona uma comida ao pedido, caso seja possível, dado o limite de 10 comidas. Retorna a quantidade de comidas no pedido após a operação.
     * @param novaComida Comida a ser adicionada. Não deve ser nula.
     * @return A quantidade de comidas no pedido após a tentativa de inserção.
     */
    public int addComida(Comida novaComida) {
        if (novaComida != null && podeAdicionarComida()) {
            itens[quantComidas] = novaComida;
            quantComidas++;
        }
        return quantComidas;
    }

    /**
     * Verificação para adicionar um novo item no pedido: ele precisa estar aberto e
     * a quantidade de comidas não pode ter atingido o máximo
     * @return TRUE se é possível adicionar uma comida, FALSE caso contrário.
     */
    private boolean podeAdicionarComida() {
        return (aberto && quantComidas < MAX_COMIDAS);
    }

    /**
     * Calcula o valor da taxa de entrega do pedido. Esta taxa varia com a distância, conforme regras internas.
     * @return Valor da taxa de entrega (double não negativo)
     */
    @Override
    public double taxa(){
        int pos = 0;
        while ((distancia > DISTANCIAS_ENTREGA[pos])) {
            pos++;
        }
        return VALORES_TAXA[pos];
    }   

}
