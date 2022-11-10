public abstract class Polygon extends Shape{
    protected double base;
    protected double altura;

    public Polygon(double base, double altura){
        super("Polígono");
        this.base = this.altura = 0.01;
        if(base > 0) this.base = base;
        if(altura > 0) this.altura = altura;
    }

    @Override
    public String toString(){
        return super.toString()+" ("+this.base+" e "+this.altura+")";
    }
}
