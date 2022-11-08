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

/** Classe simplificada: veículo para uso na demonstração de coleções */
public class Veiculo implements Comparable<Veiculo> {
    
    String placa;
    int anoFabricacao;
    int kmRodados;
    double valorDeVenda;

    /**
     * Construtor sem validação 
     * @param pl Placa
     * @param ano Ano de fabricação
     * @param km Km rodados
     * @param valor Valor de venda
     */
    public Veiculo(String pl, int ano, int km, double valor){
        placa = pl;
        anoFabricacao = ano;
        kmRodados = km;
        valorDeVenda = valor;
    }

    @Override
    public String toString(){
        return this.placa+" - ano "+this.anoFabricacao;
    }

    @Override
    /**
     * CompareTo para Comparable. Comparação por ano de fabricação
     */
    public int compareTo(Veiculo o) {
        return this.anoFabricacao - o.anoFabricacao;
        
    }

    @Override
    /**
     * Equals para polimorfismo na coleção. Igualdade por placa.
     * CUIDADO: em situação real, pense duas vezes para implementar equals e compareTo com critérios diferentes
     */
    public boolean equals(Object o){
        Veiculo v = (Veiculo)o;
        return(this.placa.equals(v.placa));
    }

}
