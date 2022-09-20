

public class Sanduiche extends Comida {

        //constantes
        private static final int MAX_INGRED = 5;
        private static final double VALOR_BASICO = 12.0;
        private static final String DESCRICAO = "Sanduíche";
        
        

        public Sanduiche(){
            super(MAX_INGRED, VALOR_BASICO, DESCRICAO);
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
