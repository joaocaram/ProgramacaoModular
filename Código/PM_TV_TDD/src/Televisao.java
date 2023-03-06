public class Televisao {

    private int volume; //criado no 1º teste
    private int canal;  //criado no 4º teste
    private static final int VOLUME_MAXIMO = 100;   //criado na refatoração do 3º teste

    /**
     * Sobe o volume em uma unidade, sem passar do máximo definido. (criado no 1º teste, refatorado no 3º teste)
     */
    public void subirVolume() {
        if(this.volume<VOLUME_MAXIMO)
            this.volume++;
    }

   /**
    * Informa o volume atual da TV. (criado no 1º teste)
    * @return Um inteiro entre 0 e 100 contendo o volume atual da TV.
    */ 
    public int getVolume() {
        return this.volume;
    }


    /**
     * Baixa o volume em uma unidade. (criado no 2º teste)
     */
    public void baixarVolume() {
        this.volume--;
    }

    /**
     *  Muda o canal diretamente para o número desejado pelo usuário, fazendo a validação
     *  de faixa de canais. (criado no 4º teste e já adiantando o canal mínimo)
     */
    public void mudarCanal(int canal) {
        if(canal<=83&&canal>=1)
            this.canal = canal;
        else
            this.canal = 1;

    }

    /**
     * Retorna um valor entre 1 e 83 representando o canal atual (criado no 4º teste)
     * @return
     */
    public int canalAtual() {
        return this.canal;
    }
    
}
