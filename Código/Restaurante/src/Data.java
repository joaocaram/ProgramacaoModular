import java.io.Serializable;

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

public class Data implements Serializable{
    static final long serialVersionUID = 20222L;    

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

    /**
     * Inicializador privado. Reverte a data para 01/01/1990 em caso de falha.
     * @param dia Dia da data
     * @param mes Mês da data
     * @param ano Ano da data
     */
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
     * Construtor completo: recebe dia, mês e ano e valida a data. Datas inválidas vão para 01/01/1990. Anos válidos a partir de 1900
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
     * Construtor para data padrão: 01/01/1990
     */
    public Data(){
        this.init(1,1,1990);
    }
    
    /**
     * Construtor para interpretação a partir de string. O separador deve ser / e o construtor não tem proteção para datas inválidas, usando
     * a regra da classe para valores incorretos (01/01/1990). Anos válidos a partir de 1900
     * @param data A data no formato "DD/MM/AAAA"
     */
    public Data(String data){
        String[] dados = data.split("/");
        int dia = Integer.parseInt(dados[0]);
        int mes = Integer.parseInt(dados[1]);
        int ano = Integer.parseInt(dados[2]);

        this.init(dia,mes,ano);
    }
    //#endregion

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
    * Verifica se a data armazenada é válida (método privado). Anos válidos somente a partir de 1900.
    * @return TRUE se é válida ; FALSE se não é válida
    */
    private Boolean dataValida(){
        Boolean resposta;        //resposta sobre a validade
        if(this.ano<1900)
            resposta = false;
        else{
           if (this.mes < 1 || this.mes > 12)                           //mês<1 ou mês>12 --> data inválida
               resposta = false;
           else { 
                    if (this.dia<1) 
                        resposta = false;
                    else{
                        if (this.anoBissexto()&&this.mes==2) //senão, caso de 29/02 em ano bissexto --> data válida
                            resposta = this.dia <= 29;  
                        else 
                            resposta = (this.dia <= DIASDOMES[this.mes]);                //senao, verifica validade de acordo com o mês atual
                    }
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
     * Diferença entre esta e outra data em meses completos. Usado para o método de diferença de dias
     * @param outra A data para comparação
     * @return Quantidade de meses completos entre as datas (iniciando de 0)
     */
    private int diferencaMeses(Data outra){
        return Math.abs( (outra.ano*12+outra.mes)-(this.ano*12+this.mes));  
    }

    /**
     * Dias para terminar o mês nesta data. Usado para o método de diferença de dias
     * @return Quantidade de dias para o fim do mês. 
     */
    private int diasParaFimDoMes(){
        int dias = DIASDOMES[this.mes]-this.dia;
        if(this.anoBissexto()&&this.mes==2) dias++;
        return dias;
    }


    /**
     * Método (com lógica que pode ser MUITO melhorada) para retornar a diferença de dias entre esta e outra data.
     * A outra data precisa ser futura, caso contrário será retornado 0.
     * @param outra A outra data, para calcular a diferença em dias.
     * @return A diferença em dias entre as duas datas; 0 no caso da outra data ser mais antiga.
     */
    public int diffDias(Data outra){
        int cont=0;
        Data passado = this;
        Data futuro = outra;
        if(this.depoisDe(outra)){
            futuro = this;
            passado = outra;
        }
        
        int meses = passado.diferencaMeses(futuro);
        while(meses!=0){
            int fimDoMes = passado.diasParaFimDoMes();
            cont+=(fimDoMes+1);
            passado = passado.acrescentaDias(fimDoMes+1);
            meses = passado.diferencaMeses(futuro);
        }
        cont+= (futuro.dia-passado.dia);
        
        return cont;
    }


    /**
     * Verifica, entre duas datas, qual está no futuro em relação à outra.
     * Comparação feita pela transformação da data em String AAAAMMDD
     * @param outra Outra data a ser comparada
     * @return TRUE se esta for futura, FALSE caso contrário
     */
    public boolean depoisDe(Data outra){
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
    
    @Override
    public boolean equals(Object o){
        Data outra = (Data)o;
        return(this.dataFormatada().equals(outra.dataFormatada()));
    }

    
}

