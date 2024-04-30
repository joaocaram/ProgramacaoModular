import java.util.HashMap;

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
 * Amplexa: assistente virtual que controla dispositivos com habilidades diversas.
 */
public class Amplexa {

	//#region atributos
	private String nome;
	private HashMap<String, Dispositivo> dispositivos;
	//#endregion

	/**
	 * Cria uma assistente virtual com o nome designado (não validado.)
	 * @param nome
	 */
	public Amplexa(String nome){
		if(nome.length()<=0) nome = "Amplexa";
		this.nome = nome;
		dispositivos = new HashMap<>();
	}
	
	/**
	 * Adiciona um dispositivo a ser controlado pela assistente.
	 * @param novo O dispositivo a ser controlado. Caso tenha o mesmo nome de outro, o anterior será substituído.
	 */
	public void addDispositivo(Dispositivo novo) {
		dispositivos.put(novo.getNome(), novo);
	}

	/**
	 * Liga um dispositivo por seu nome. Método ainda desprotegido, podendo causar exceção se um dispositivo não for desligável.
	 * @param nome Nome do dispositivo.
	 * @return TRUE/FALSE com o estado do dispositivo
	 */
	public boolean ligar(String nome) {
		IDesligavel disp = (IDesligavel)dispositivos.get(nome);
		boolean retorno = false;
		if(disp!=null)
			retorno = disp.ligar();
		return retorno;
	}

	/**
	 * Desliga um dispositivo por seu nome. Método ainda desprotegido, podendo causar exceção se um dispositivo não for desligável.
	 * @param nome Nome do dispositivo.
	 * @return TRUE/FALSE com o estado do dispositivo
	 */
	public boolean desligar(String nome) {
		IDesligavel disp = (IDesligavel)dispositivos.get(nome);
		boolean retorno = false;
		if(disp!=null)
			retorno = disp.desligar();
		return retorno;
	}

	/**
	 * Regula a potência de um dispositivo por seu nome. Método ainda desprotegido, podendo causar exceção se um dispositivo não for regulável.
	 * @param nome Nome do dispositivo.
	 */
	public void regular(String nome, int potencia) {
		IRegulavel disp = (IRegulavel)dispositivos.get(nome);
		
		if(disp!=null)
			disp.regular(potencia);
	}

	/**
	 * Relatório da assistente: lista todos os dispositivos e seus estados.
	 * @return String com os dispositivos, um por linha, e seus estados.
	 */
	@Override
	public String toString(){
		StringBuilder relat = new StringBuilder(nome+" controlando os dispositivos: \n");
		for (Dispositivo disp : dispositivos.values()) {
			relat.append(disp+"\n");
		}
		return relat.toString();
	}

}
