/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
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
  * Classe-demonstração Relógio com POO básica. Versão 1 - Somente atributos e métodos.
  */
public class Relogio {
    
	    
	//#region ATRIBUTOS
    private int hora;
    private int minuto;
    private int segundo;
	//#endregion
    
    
	//#region REGRAS DO RELÓGIO
    
	
	/** Ajusta a hora, com validação simples.
	 * Em caso de qualquer parâmetro inválido, vai para 00h00m00.
	 * @param hora Hora para criação do relógio.
	 * @param minuto Minuto para criação do relógio.
	 * @param segundo Segundo para criação do relógio.
	 */
	public void ajustarHora(int hora, int minuto, int segundo){       
		if((hora>=0 && hora<=23) && 	
		   (minuto>=0 && minuto<=59) && 
		   (segundo>=0 && segundo<=59)){
				this.hora = hora;
				this.minuto = minuto;
				this.segundo = segundo;
		}
		else
			this.hora = this.minuto = this.segundo = 0;
	}  
	       
	/**
	 * Reinicia o relógio para 00h00m00
	 */
    public void reiniciar(){		
        this.ajustarHora(0,0,0);
    };
	
	/**
	 * Passa um segundo do relógio.
	 * Faz os ajustes necessários em minutos e horas.
	 */
    public void passarTempo(){
        this.segundo++;
		if (this.segundo == 60){
            this.segundo = 0 ;
			this.minuto++ ;
            			
			if(this.minuto == 60){   
				this.minuto = 0;
				this.hora++;
				
				if(this.hora == 24){
					this.hora = 0;
				}
			}
        }        
    };


	/**
	 * Retorna a hora no formato HH:MM:SS, usando o formato de 24h.
	 * @return Uma string no formato HH:MM:SS.
	 */
	public String horaFormatada(){
        String aux = String.format("%02d", this.hora)+":";
        aux += String.format("%02d", this.minuto)+":";
        aux += String.format("%02d", this.segundo);
        return aux;
    }
	//#endregion
}
