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

 import java.text.NumberFormat;

 /** Classe Pizza para a Xulambs Pizza. Uma pizza tem um preço base e pode ter até 8 ingredientes adicionais. Cada ingrediente tem custo fixo.
   * A pizza deve emitir uma nota de compra com os seus detalhes.
   */
 public class Pizza {
 
     private static final int MAX_INGREDIENTES;
     private static final double PRECO_BASE;
     private static final double VALOR_ADICIONAL;
     private int quantidadeIngredientes;
	 private EBorda bordaRecheada;

	 static{
		MAX_INGREDIENTES = 8;
		PRECO_BASE = 29d;
		VALOR_ADICIONAL = 5d;
	}

	/**
	 * Inicializador privado. Configura os adicionais e a borda recheada desejada.
	 * @param adicionais Quantidade de adicionais (int >=0)
	 * @param borda Borda da pizza (EBorda)
	 */
	private void init(int adicionais, EBorda borda){
		adicionarIngredientes(adicionais);
		adicionarBorda(borda);
	}


     /**
      * Construtor padrão. Cria uma pizza sem adicionais e borda tradicional.
      */
     public Pizza() {
		init(0, EBorda.SEM_BORDA);
	}


     /**
      * Cria uma pizza com a quantidade de adicionais pré-definida e borda tradicional. 
	  * Em caso de valor inválido para adicionais, a pizza será criada sem adicionais.
      * @param adicionais Quantidade de adicionais (entre 0 e 8, limites inclusivos).
      */
	  public Pizza(int adicionais) {
		init(adicionais, EBorda.SEM_BORDA);
	}

	/**
	 * Cria uma pizza com a quantidade de adicionais pré-definida e borda escolhida
	 * pelo usuário. Em caso de valor inválido para adicionais, a pizza será criada 
	 * sem adicionais. Em caso de borda nula, será usada borda tradicional. 
	 * @param adicionais Quantidade de adicionais (entre 0 e 8, limites inclusivos).
	 * @param borda EBorda para a pizza. Se for nula, cria com borda tradicional.
	 */
	public Pizza(int adicionais, EBorda borda) {
		init(adicionais, borda);
	}

	/**
	 * Encapsula a lógica de cálculo para o valor dos adicionais da pizza. 
	 * @return Double (>=0) com o valor dos adicionais.
	 */
     private double valorAdicionais(){
         return quantidadeIngredientes*VALOR_ADICIONAL;
     }
    
	 /**
      * Retorna o valor final da pizza, incluindo seus adicionais.
      * @return Double com o valor final da pizza.
      */
     public double valorFinal() {
         return PRECO_BASE + valorAdicionais() + bordaRecheada.valor();
     }
 
     /**
      * Tenta adicionar ingredientes na pizza. Caso a adição seja inválida (ultrapassando limites ou com valores negativos), mantém 
      * a quantidade atual de ingredientes. Retorna a quantidade de ingredientes após a execução do método.
      * @param quantos Quantos ingredientes a serem adicionados (>0)
      * @return Quantos ingredientes a pizza tem após a execução
      */
	  public int adicionarIngredientes(int quantos) {
		if(podeAdicionar(quantos)){
            quantidadeIngredientes += quantos;
        }
        return quantidadeIngredientes;
	}

	/**
	 * Retira ingredientes da pizza. Caso o valor pedido ("quantos") seja maior que 
	 * a quantidade de ingredientes, ignora a operação.
	 * @param quantos Quantos ingredientes devem ser retirados. Deve ser um valor
	 * inteiro positivo.
	 * @return A quantidade de ingredientes da pizza após a execução.
	 */
	public int retirarIngredientes(int quantos) {
		if(quantos > 0)
			quantos *=-1;
        return adicionarIngredientes(quantos);
	}


     /**
      * Faz a verificação de limites para adicionar ingredientes na pizza. Retorna TRUE/FALSE conforme seja possível ou não adicionar
      * esta quantidade de ingredientes.
      * @param quantos Quantidade de ingredientes a adicionar.
      * @return TRUE/FALSE conforme seja possível ou não adicionar esta quantidade de ingredientes.
      */
     private boolean podeAdicionar(int quantos) {
         return (quantos>0 && quantos+quantidadeIngredientes<=MAX_INGREDIENTES);
     }
 
	 /**
	  * Muda a borda da pizza. Em caso de valor inválido/nulo, deixa a pizza com
	  * borda tradicional. Retorna o valor da borda atribuída à pizza.
	  * @param borda Borda da pizza (Enumerador EBorda)
	  * @return Valor da borda atribuída à pizza
	  */
	 public double adicionarBorda(EBorda borda){
		if(borda == null)
			borda = EBorda.SEM_BORDA;
		bordaRecheada = borda;
		return bordaRecheada.valor();

	}

     /**
      * Nota simplificada de compra: descrição da pizza, dos ingredientes, da borda
	  * e do preço a pagar.
      * @return String de uma única linha com as informações acima
      */
     public String notaDeCompra() {
         NumberFormat moeda = NumberFormat.getCurrencyInstance();
		 return String.format("Pizza (%s) com %d ingredientes (%s) e %s. Valor total: R$ %s",
		 						moeda.format(PRECO_BASE), quantidadeIngredientes, moeda.format(valorAdicionais()), 
								bordaRecheada.descricao(), moeda.format(valorFinal()));
}
 
 }
 