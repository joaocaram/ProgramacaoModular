import java.time.LocalDate;

/** 
 * MIT License
 *
 * Copyright(c) 2022-23  João Caram <caram@pucminas.br>
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
 * Classe Data simples, com lógica própria e sem bibliotecas, para demonstração 
 * dos conceitos básicos de POO.
 */
 public class Data {
        
    //#region atributos
    //constante: dias de cada mês
    private static int[] DIASDOMES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final int ANO_ATUAL = 2022;
    
    //atributos 
    private int dia;
    private int mes;
    private int ano;
    //#endregion
    
    //#region Construtores   
    
    private void init(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        if (!this.dataValida()){     //se a data não é válida... (método da própria classe)
            this.dia = this.mes = 1;
            this.ano = 1990;
        }

    }
    
    /**
     * Construtor para data padrão: captura data do Sistema Operacional.
     */
    public Data(){
        //obs: único método que utiliza classe/biblioteca Java para manipulação de data.
        LocalDate hoje = LocalDate.now();
        this.init(hoje.getDayOfMonth(),hoje.getMonthValue(),hoje.getYear());
    }
    
    /**
     * Construtor completo: recebe dia, mês e ano e valida a data. Datas inválidas vão para 01/01/1990
     * @param dia Dia
     * @param mes Mês
     * @param ano Ano
     */
    public Data(int dia, int mes, int ano){
        this.init(dia, mes, ano);
    }
    
    /**
     * Construtor para ano atual: recebe dia, mês e completa com ano atual (2022).
     * Datas inválidas vão para 01/01/1990
     * @param dia Dia
     * @param mes Mês
     * 
     */
    public Data(int dia, int mes){
        this.init(dia, mes, ANO_ATUAL);
    }

    /**
     * Cria uma data a partir de uma string DD/MM/AAAA. Em caso de problemas, 
     * a data padrão de 01/01/1990 será criada.
     * @param dataFormatada A data no formato DD/MM/AAAA, string.
     */
    public Data(String dataFormatada){
        String[] datas = dataFormatada.split("/");
        int[] valoresData = {1,1,1990};
        
        if(datas.length==3){
            try {
                for (int i = 0; i < valoresData.length; i++) {
                    valoresData[i] = Integer.parseInt(datas[i]);    
                }    
                this.init(valoresData[0], valoresData[1], valoresData[2]);
            } catch (NumberFormatException ne) {
                this.init(1, 1, 1990); 
            }
        }
    }

    //#endregion
    
    //#region Métodos de negócio

    /**  
    * Retorna se o ano da data armazenada é bissexto 
    * Para regras do ano bissexto:
    * http://educacao.uol.com.br/disciplinas/matematica/ano-bissexto-eles-se-repetem-a-cada-4-anos.htm
    * http://www.sogeografia.com.br/Curiosidades/?pg=4
    * @return Se o ano é bissexto (true) ou não (false)
    */
    public boolean anoBissexto(){
        boolean resposta = false;
        if(this.ano%400==0) 
              resposta = true;
        else if(this.ano%4==0 && this.ano%100!=0)
              resposta = true;
        
        return resposta;
    }

    /**
    * Verifica se a data armazenada é válida (método privado)
    * @return TRUE se é válida ; FALSE se não é válida
    */
    private Boolean dataValida(){
        Boolean resposta = true;        //resposta sobre a validade
        if(this.ano<1900)
            resposta = false;
        else{
           if (this.mes < 1 || this.mes > 12)                           //mês<1 ou mês>12 --> data inválida
               resposta = false;
           else { 
                   if (this.anoBissexto()) //senão, caso de 29/02 em ano bissexto --> data válida
                          DIASDOMES[2] = 29;
                   if (this.dia > DIASDOMES[this.mes])                //senao, verifica validade de acordo com o mês atual
                           resposta = false;
                   DIASDOMES[2] = 28;
                }
        }                       
        return resposta;    //retorna a resposta obtida
    }

    /**
     * Acrescenta alguns dias à data e retorna a nova data (sem modificar a atual)
     * @param quant Quantos dias
     * @return Nova data com os dias adicionados
     */
    public Data acrescentaDias(int quant){
        Data aux = new Data(this.dia, this.mes, this.ano);
        if(quant<=0) return aux;
        
        aux.dia += quant;      //acrescenta a quantidade ao dia atual e, em seguida, devemos ajustar mês e ano

        if (aux.anoBissexto()) DIASDOMES[2] = 29; //se o ano é bissexto, altera fevereiro para 29 dias

        while (aux.dia > DIASDOMES[aux.mes]){     //enquanto os dias ultrapassam o limite de dias do mês atual... ajustar

            aux.dia = aux.dia - DIASDOMES[aux.mes]; // desconta a quantidade de dias do mês       
            aux.mes++; //avança o mês
            
            if (aux.mes > 12){      //se passar de 12 meses...            
                aux.mes = aux.mes - 12;       //desconta-se 1 ano
                aux.ano++;                     //avança o ano.
                if (aux.anoBissexto()) DIASDOMES[2] = 29; //verifica se o novo ano é bissexto para ajustar os dias de fevereiro.
                else DIASDOMES[2] = 28;
            }
        }
        return aux;
    }

    /**
     * Verifica se esta data é futura em relação a outra, recebida por parâmetro.
     * Comparação feita pela transformação da data em String AAAAMMDD
     * @param outra Outra data a ser comparada
     * @return TRUE se esta for futura, FALSE caso contrário
     */
    public boolean ehNaFrenteDe(Data outra){
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
        
        return (String.format("%02d",this.dia)+ "/" + String.format("%02d",this.mes)+ "/" + String.format("%4d",this.ano));
    }
    
    //#endregion

    //#region métodos static

    /**
     * Verifica,a partir da classe, qual de duas datas já existentes se encontra mais no futuro. 
     * Utiliza internamente o método "ehNaFrenteDe"
     * @param data1 Primeira data para verificaçaão
     * @param data2 Segunda data para verificaçaão
     * @return A data que estiver mais no futuro entre as duas fornecidas como parâmetro.
     */
    public static Data dataFutura(Data data1, Data data2){
        boolean data1Futura = data1.ehNaFrenteDe(data2);
       
        if(data1Futura)
            return data1;
        else
            return data2;
    }
    //#endregion
}



