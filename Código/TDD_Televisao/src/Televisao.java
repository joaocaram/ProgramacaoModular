public class Televisao {
    private static final int CANAL_MINIMO=1;
    private int canal;
    private int volume;
    private int volumeAnterior;
    private boolean ligada;

    public Televisao(){
        this.canal = CANAL_MINIMO;
        this.ligada = false;
    }

    public void mudarCanal(int canal) {
        if(this.ligada)
            if(canal>=CANAL_MINIMO && canal<=83)
                this.canal = canal;
    }

    public void subirCanal() {
        if(this.ligada){
            if(this.canal==83) this.canal = CANAL_MINIMO;
            else this.canal++;
        }
    }

    public int getCanal() {
        return this.canal;
    }

    public void baixarCanal() {
        if(this.ligada){
            if(this.canal == CANAL_MINIMO) this.canal = 83;
            else this.canal--;
        }
    }

    public void subirVolume() {
        if(this.ligada)
            if(this.volume<100){
                this.volume++;
                this.volumeAnterior = this.volume;
            }
    }

    public int getVolume() {
        return this.volume;
    }

    public void baixarVolume() {
        if(this.ligada)
            if(this.volume>0){
                this.volume--;
                this.volumeAnterior = volume;
            }
    }

    /**
     * Alterna o estado da TV entre ligada e desligada
     * @return O estado atual da TV (booleano)
     */
    public boolean botaoPower(){
        this.ligada = !this.ligada;
        
        return this.ligada;
    }

    public void aplicarMute() {
        if(this.ligada)
            this.volume=0;
    }

    public void tirarMute() {
        if(this.ligada)
            this.volume = this.volumeAnterior;

    }
    
    
}
