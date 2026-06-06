
public class DistanciaInvalidaException extends IllegalArgumentException{
    
    private double distancia;

    public DistanciaInvalidaException(double dist){
        super("Distância do pedido deve ser maior que 0.");
        distancia = dist;
    }

    public double getDistancia(){
        return distancia;
    }
}
