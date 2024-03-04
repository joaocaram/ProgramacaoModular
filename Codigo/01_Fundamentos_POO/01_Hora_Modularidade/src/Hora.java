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

 /**
  * Primeira versão da classe Hora para demonstração de conceitos básicos de classe e objetos: atributos, métodos, parâmetros, documentação.
  * Esta classe tem a responsabilidade de armazenar um horário do dia, considerando 24h.
  * Pode <b> e deve </b> ser melhorada com futuros conhecimentos.
  */
public class Hora {

    //#region atributos
    
    byte hora;
    byte minuto;
    byte segundo;
    
    //#endregion
    
    //#region métodos
    
    /**
     * Ajusta uma hora para o valor desejado de horas, minutos e segundos. Em caso de valores inválidos, a hora retorna para 00:00:00.
     * @param hora A hora desejada (0 a 23)
     * @param minuto O minuto desejado (0 a 59)
     * @param segundo O segundo desejado (0 a 59)
     */
    void ajustar(byte hora, byte minuto, byte segundo){
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
        if(!validar())
            this.hora = this.minuto = this.segundo = 0;
    }

    /**
     * Incrementa uma das partes da hora armazenada (hora, minuto ou segundo). O incremento só pode ser feito em valores de até 60 unidades para a parte escolhida.
     * Caso a quantidade for maior que 60, o incremento será ignorado. O componente da hora se ajusta automaticamente ao incremento. Por exemplo, na hora <i> 13:42:50 </i>, se forem incrementados
     * 25 segundos, a hora armazenada será <i> 13:43:15 </i>. O componente a ser incrementado deve ser indicado por uma letra minúscula (h: hora, m: minuto, s: segundo). Outras letras serão
     * ignoradas.
     * @param quant Unidades a serem incrementadas no compomente da hora. Valores menores que 0 ou maiores que 60 são ignorados.
     * @param posicao O componente do incremento: h: hora, m: minuto, s: segundo. Outras letras serão ignoradas.
     */
    void incrementar(byte quant, char posicao){
        if(quant<0 || quant >60)
            return;
        char digito = Character.toLowerCase(posicao);
        int acumulador = 0;
        switch(digito){
            case 'h': this.hora += quant;
                break;
            case 'm': this.minuto += quant;
                break;
            case 's': this.segundo += quant;
                break;
        }
        if(this.segundo>=60){       //ajuste caso os segundos ultrapassem 60
            acumulador = this.segundo/60;
            this.segundo = (byte) (this.segundo % 60);
            this.minuto += acumulador;
        }
        if(this.minuto>=60){        //ajuste caso os minutos ultrapassem 60
            acumulador = this.minuto/60;
            this.minuto = (byte) (this.minuto % 60);
            this.hora += acumulador;
        }
        if(this.hora >=24){         //ajuste caso 'vire o dia'
            this.hora = (byte) (this.hora % 24);
        }

    }

    /**
     * Valida a hora armazenada atualmente, de acordo com as regras habituais de hora, minuto e segundo. Retorna TRUE ou FALSE conforme validade da hora.
     * @return TRUE para hora válida, FALSE para hora inválida.
     */
    boolean validar() {
        if ((hora >= 0 && hora <= 23) && (minuto >= 0 && minuto <= 59)
                && (segundo >= 0 && segundo <= 59))
            return true;
        else {
            return false;
        }
    }

    /**
     * Verifica se esta hora encontra-se à frente de outra considerando um dia de 24h. Retorna TRUE ou FALSE conforme esta hora esteja na frente.
     * Perceba que a ação é feita a partir da conversão de todos os valores para segundos, seguida de uma comparação simples. Há outras maneiras de resolver ;-)
     * @param outra A outra hora a ser comparada com esta
     * @return TRUE se esta hora está à frente no relógio, FALSE caso contrário.
     */
    boolean estahNaFrenteDe(Hora outra){
		int esta;
		int aquela;
		esta = hora*3600 + minuto*60 + segundo;		
		aquela = outra.hora*3600 + outra.minuto*60 + outra.segundo;		
		return (esta > aquela);		
	}
    //#endregion
}
