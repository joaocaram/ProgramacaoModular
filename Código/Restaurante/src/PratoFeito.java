public class PratoFeito extends Comida{

    private static final double CARNE_EXTRA = 6.0d;
    private static final double VALOR_BASICO = 14.0;
    private static final String DESCRICAO = "Pê-efão do bão";

    private boolean carneExtra;

    public PratoFeito(){
        super(0, VALOR_BASICO, DESCRICAO);
    }

    @Override
    protected double valorAdicionais() {
       
        if(this.carneExtra)
            return CARNE_EXTRA;
        else
            return 0d;
    }
    

}
