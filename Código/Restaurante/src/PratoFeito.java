public class PratoFeito extends Comida{

    private static final double CARNE_EXTRA = 6.0d;
    private static final double VALOR_BASICO = 14.0;
    private static final String DESCRICAO = "Pê-efão do bão";

    private boolean carneExtra;

    public PratoFeito(){
        super(VALOR_BASICO, DESCRICAO);
    }

    @Override
    protected double valorAdicionais() {
       
        if(this.carneExtra)
            return CARNE_EXTRA;
        else
            return 0d;
    }
    
     /**
     * Cria a nota de venda. Em caso de pedido aberto, retorna somente o aviso. A nota tem o formato simplificado de
     * "Pizza simples com XX adicionais. Preço: R$XX.XX".
     * @return A nota simplificada ou mensagem de pedido em aberto (string em qualquer caso).
     */
    @Override
    public String toString(){
        StringBuilder nota = new StringBuilder(this.descricao);
        nota.append(". Preço: R$"+String.format("%.2f", this.valorBasico)+".\n");
        if(this.carneExtra)
            nota.append("\tCarne extra: R$ "+String.format("%.2f", CARNE_EXTRA)+".\n");
        nota.append("\tValor final: R$"+String.format("%.2f", this.calcularPreco()));
        return nota.toString();
    }

}
