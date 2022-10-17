
public class Sanduiche extends Comida implements IPersonalizavel{

        //constantes
        private static final int MAX_INGRED = 5;
        private static final double VALOR_BASICO = 12.0;
        private static final String DESCRICAO = "Sanduíche";
                
        public Sanduiche(){
            super(MAX_INGRED, VALOR_BASICO, DESCRICAO);
        }

/**
     * Validação do máximo de adicionais. Protegido para venda fechada e valores negativos. Não adiciona os adicionais, apenas valida a quantidade.
     * @param quantos Quantidade de adicionais para validar.
     * @return TRUE se válido, FALSE se inválido.
     */
    public boolean validarAdicionais(int quantos){
        if (!this.vendaFechada && quantos>0 && quantos<=this.maximoIngredientes)
            return true;
        else
            return false;
    }
     /**
     * Adiciona ingredientes extras na pizza, além dos já incluídos. Método validado para valores negativos ou acima do limite (8 adicionais). Caso inválido, ignora a operação.
     * @param quantos Quantos ingredientes a adicionar aos que já existem na pizza. 
     * @return TRUE se operação com sucesso, FALSE caso contrário.
     */
    public boolean addIngredientes(Ingrediente adicional){
        boolean resposta = false;
        if(validarAdicionais(this.qtIngredientes+1)){
            this.ingredientes[this.qtIngredientes] = adicional;
            this.qtIngredientes++;
            resposta = true;
        }
        return resposta;
    }

      /**
     * Adiciona ingredientes extras na pizza, além dos já incluídos. Método validado para valores negativos ou acima do limite (8 adicionais). Caso inválido, ignora a operação.
     * @param quantos Quantos ingredientes a adicionar aos que já existem na pizza. 
     * @return TRUE se operação com sucesso, FALSE caso contrário.
     */
    public boolean delIngredientes(String ingrediente){
        boolean resposta = false;
        for (int i = 0; i < this.qtIngredientes; i++) {
            if(this.ingredientes[i].toString().toLowerCase().equals(ingrediente.toLowerCase())){
                this.ingredientes[i] = this.ingredientes[this.qtIngredientes-1];
                resposta = true;
                this.qtIngredientes--;
                break;
            }
        }
        return resposta;
    }

        @Override
        protected double valorAdicionais() {
            double valor=0d;
            for (int i = 0; i < this.qtIngredientes; i++) {
                valor+= this.ingredientes[i].preco();
            }            
            return valor;
        }
        
}
