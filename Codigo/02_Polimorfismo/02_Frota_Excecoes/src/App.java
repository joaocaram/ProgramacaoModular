public class App {
 
    public static void main(String[] args) {
        Frota f = new Frota(); 
        f.addVeiculo(new Veiculo("ABC1234", 2026, 50_000));
        
        Veiculo v = f.localizar("ABC1234");
        int litros = Integer.parseInt(IO.readln("Litros? "));
        if(v != null && litros > 0)
            try {  
                v.abastecer(litros);
            }
            catch(IllegalStateException ise){
                IO.println(ise.getMessage());
            }
        }
    }