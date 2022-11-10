public class Triangle extends Polygon{
    
    public Triangle(double cateto1, double cateto2){
        super(cateto1, cateto2);
        this.descricao = "Triângulo Retângulo"; 
    }

    @Override
    public double area(){
        return (this.altura*this.base)/2;
    }

    @Override
    public double perimetro(){
        return this.altura+this.base+this.hipotenusa();
    }

    private double hipotenusa() {
        return Math.sqrt(Math.pow(this.altura, 2.0)+
                         Math.pow(this.base, 2.0));
    }

    @Override
    public String toString(){
        return super.toString()+" ("+String.format("%.2f", this.hipotenusa())+")";
    }
}
