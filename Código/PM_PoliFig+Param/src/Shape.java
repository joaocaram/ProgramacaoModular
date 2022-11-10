public abstract class Shape implements Comparable<Shape>{
    protected String descricao;

    public Shape(String desc){
        this.descricao = desc;
    }

    public abstract double area();
    public abstract double perimetro();

    @Override
    public String toString(){
        return this.descricao + " com área de "+
               String.format("%.2f", this.area())+
               " e perímetro "+
               String.format("%.2f", this.perimetro());
    }

    @Override
    public boolean equals(Object o){
        Shape aux = (Shape)o;
        if(this.area() == aux.area())
            return true;
        else
            return false;

    }
    @Override
    public int compareTo(Shape o){
        if(this.area() > o.area())
            return 1;
        else if(this.area() < o.area())
            return -1;
        else 
            return 0;
    }
}
