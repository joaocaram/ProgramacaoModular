public class Circle extends Shape{

    private double raio;

    public Circle(double raio){
        super("Círculo");
        this.raio = 0.01;
        if(raio>0)
            this.raio = raio;
    }

    @Override
    public double area(){
        return Math.PI * Math.pow(this.raio, 2.0);
    }
    
    @Override
    public double perimetro(){
        return 2*Math.PI*this.raio;
    }

    @Override
    public String toString(){
        return super.toString()+" ("+this.raio+")";
    }




}
