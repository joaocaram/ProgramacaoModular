public class Rectangle extends Polygon{
    
    public Rectangle(double base, double altura){
        super(base, altura);
        this.descricao = "Retângulo";   
    }
    @Override
    public double area(){
        return this.altura*this.base;
    }

    @Override
    public double perimetro(){
        return 2*this.altura+2*this.base;
    }
    
}