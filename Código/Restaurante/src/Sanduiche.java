
public class Sanduiche extends Comida implements IPersonalizavel{

    //constantes
    private static final int MAX_INGRED = 5;
    private static final double VALOR_BASICO = 12.0;
    private static final String DESCRICAO = "Sanduíche";
    protected int qtIngredientes;
    protected Ingrediente ingredientes[];

    public Sanduiche(){
        super(VALOR_BASICO, DESCRICAO);
        this.ingredientes = new Ingrediente[MAX_INGRED];
        this.qtIngredientes =0;
        
    }

/**
     * Validação do máximo de adicionais. Protegido para venda fechada e valores negativos. Não adiciona os adicionais, apenas valida a quantidade.
     * @param quantos Quantidade de adicionais para validar.
     * @return TRUE se válido, FALSE se inválido.
     */
    public boolean validarAdicionais(int quantos){
        if (!this.vendaFechada && quantos>0 && quantos<=MAX_INGRED)
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

     /**
     * Cria a nota de venda. Em caso de pedido aberto, retorna somente o aviso. A nota tem o formato simplificado de
     * "Pizza simples com XX adicionais. Preço: R$XX.XX".
     * @return A nota simplificada ou mensagem de pedido em aberto (string em qualquer caso).
     */
    @Override
    public String toString(){
        StringBuilder nota = new StringBuilder(this.descricao);
        nota.append(" com "+this.qtIngredientes+
        " adicionais. Preço: R$"+String.format("%.2f", this.valorBasico)+".\n");
        for (int i = 0; i < qtIngredientes; i++) {
            nota.append("\t"+this.ingredientes[i]+"\tR$"+String.format("%.2f", this.ingredientes[i].preco())+"\n");
        }
        nota.append("\tValor final: R$"+String.format("%.2f", this.calcularPreco()));
        return nota.toString();
    }
        
}
