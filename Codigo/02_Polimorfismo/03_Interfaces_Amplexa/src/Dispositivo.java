/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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
 * Classe abstrata Dispositivo: um dispositivo identificável para ser controlado pela assistente virtual.
 */
public abstract class Dispositivo {

	private String nome;
	private String estado;
	

	/**
	 * Construtor para uso das classes filhas. Se o nome for vazio, fica como "Dispositivo desconhecido".
	 * @param nome O nome do dispositivo.
	 */
	protected Dispositivo(String nome){
		if(nome.length()<1) nome = "Dispositivo desconhecido";
		this.nome = nome;	
		this.estado = this.nome;
	}

	/**
	 * Muda o estado do dispositvo. Operação só permitida para as classes filhas.
	 * @param estado Estado a ser armazenado no dispositivo. (string)
	 */
	protected void setEstado(String estado){
		this.estado = estado;
	}

	/**
	 * Retorna o nome do dispositivo, como seu identificador.
	 * @return String com o nome do dispositivo.
	 */
	public String getNome(){
		return nome;
	}

	/**
	 * Relatório do dispositivo: nome e estado
	 * @return String com nome e estado do dispositivo.
	 */
	@Override
	public String toString(){
		return nome+": "+estado;
	}
}
