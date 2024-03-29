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

public class Data {
        
    //#region atributos
    
    private int dia;
    private int mes;
    private int ano;
    //#endregion
    
    /**
     * Construtor detalhado. Tenta criar uma data com os valores passados
     * de dia, mês e ano. O ano deve ser de 1900 em diante. Qualquer dado inválido
     * leva a data para 01/01/2000
     * @param dia De 1 a 31, de acordo com o mês
     * @param mes De 1 a 12
     * @param ano De 1900 em diante
     */
    public Data(int dia, int mes, int ano){
        ajustar(dia, mes, ano);
    }

    /**
     * Construtor para o ano atual. Tenta criar uma data com os valores passados
     * de dia e mês e inclui o ano atual. Qualquer dado inválido
     * leva a data para 01/01/2000
     * @param dia De 1 a 31, de acordo com o mês
     * @param mes De 1 a 12
     */
    public Data(int dia, int mes){
        int anoAtual = 2024;
        ajustar(dia, mes, anoAtual);
    }
    /**
     * Tenta ajustar uma data com os valores passados. Em caso de qualquer valor inválido, retorna para a data padrão de 01/01/2000
     * @param dia O dia da data (1 a 31, de acordo com o mês). Os anos válidos são de 1900 em diante.
     * @param mes O mês da data (1 a 12)
     * @param ano O ano da data (de 1900 em diante)
     */
    public void ajustar(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        if(!ehDataValida()){
            this.dia = this.mes = 1;
            this.ano = 2000;
        }
    }

    /**
    * Verifica se a data armazenada é válida (método privado)
    * @return TRUE se é válida ; FALSE se não é válida
    */
    private boolean ehDataValida(){
        int[] DIASDOMES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        
        boolean resposta = true;        //resposta sobre a validade
        if(this.ano<1900)
            resposta = false;
        else{
           if (this.mes < 1 || this.mes > 12)                           //mês<1 ou mês>12 --> data inválida
               resposta = false;
           else { 
                   if (this.ehAnoBissexto()) //senão, caso de 29/02 em ano bissexto --> data válida
                          DIASDOMES[2] = 29;
                   if (this.dia > DIASDOMES[this.mes])                //senao, verifica validade de acordo com o mês atual
                           resposta = false;
                   DIASDOMES[2] = 28;
                }
        }                       
        return resposta;    //retorna a resposta obtida
    }
        
    /**  
    * Retorna se o ano da data armazenada é bissexto 
    * Para regras do ano bissexto:
    * http://educacao.uol.com.br/disciplinas/matematica/ano-bissexto-eles-se-repetem-a-cada-4-anos.htm
    * http://www.sogeografia.com.br/Curiosidades/?pg=4
    * @return Se o ano é bissexto (true) ou não (false)
    */
    private boolean ehAnoBissexto(){
        boolean resposta = false;
        if(this.ano%400==0) 
              resposta = true;
        else if(this.ano%4==0 && this.ano%100!=0)
              resposta = true;
        
        return resposta;
    }

    /**
     * Acrescenta alguns dias à data e retorna a nova data (sem modificar a atual)
     * @param quant Quantos dias
     * @return Nova data com os dias adicionados
     */
    public Data somarDias(int quantos){
        int[] DIASDOMES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        Data aux = new Data(this.dia, this.mes, this.ano);
        
        
        if(quantos<=0)        //não pode acrescentar dias negativos e, assim, retorna data atual.
            return aux;

        aux.dia += quantos;      //acrescenta a quantidade ao dia atual e, em seguida, devemos ajustar mês e ano

        if (aux.ehAnoBissexto()) DIASDOMES[2] = 29; //se o ano é bissexto, altera fevereiro para 29 dias

        while (aux.dia > DIASDOMES[aux.mes]){     //enquanto os dias ultrapassam o limite de dias do mês atual... ajustar

            aux.dia = aux.dia - DIASDOMES[aux.mes]; // desconta a quantidade de dias do mês       
            aux.mes++; //avança o mês
            
            if (aux.mes > 12){      //se passar de 12 meses...            
                aux.mes = aux.mes - 12;       //desconta-se 1 ano
                aux.ano++;                     //avança o ano.
                if (aux.ehAnoBissexto()) DIASDOMES[2] = 29; //verifica se o novo ano é bissexto para ajustar os dias de fevereiro.
                else DIASDOMES[2] = 28;
            }
        }
        return aux;
    }

    /**
     * Verifica se esta data está antes de outra, considerando o calendário. Retorna um booleano TRUE se esta for antes.
     * Comparação feita pela transformação da data em String AAAAMMDD
     * @param outra Outra data a ser comparada
     * @return TRUE se esta for futura, FALSE caso contrário
     */
    public boolean estahAntesDe(Data outra){
        boolean resposta = false;

        String esta = String.format("%4d", this.ano)+String.format("%2d", this.mes)+String.format("%2d", this.dia);
        String aOutra = String.format("%4d", outra.ano)+String.format("%2d", outra.mes)+String.format("%2d", outra.dia);

        if(esta.compareTo(aOutra)>0)
            resposta = true;

        return resposta;
    }		
    
    /**
    * Retorna a data formatada
    * @return String com a data no formato dd/mm/aaaa
    */
    public String dataFormatada(){
        return (String.format("%02d",this.dia)+ "/" 
               + String.format("%02d",this.mes)+ "/" 
               + String.format("%4d",this.ano));
    }
    


    
}

