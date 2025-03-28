import java.text.NumberFormat;

/**
 * MIT License
 *
 * Copyright(c) 2022-25 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
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

 /** Enumerador para bordas de pizza */
public enum EBorda {
    
    SEM_BORDA("Borda tradicional", 0),
    CHEDDAR("Borda de Cheddar", 10d),
    CHOCOLATE("Borda de Chocolate", 8d),
    REQUEIJAO("Borda de Requeijão", 7d);

    private String descricao;
    private double valor;

    EBorda(String desc, double valor){
        descricao = desc;
        this.valor = valor;
    }

    /**
     * Descrição contendo descrição da borda e seu valor. 
     * @return String de uma linha contendo a descrição da borda e seu valor. 
     */
    public String descricao(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s (%s)", descricao, moeda.format(valor));
    }

    /**
     * Retorna o valor da borda atual.
     * @return Double (não negativo)
     */
    public double valor(){
        return valor;
    }
}
