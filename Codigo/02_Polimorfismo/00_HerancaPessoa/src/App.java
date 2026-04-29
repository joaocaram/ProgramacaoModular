import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
       List<Pessoa> todoMundo = new ArrayList<>(20);

       todoMundo.add(new Pessoa("Pessoa Joao", 
                LocalDate.of(2000, 1, 1), 
                "Pess1"));
                
       todoMundo.add(new Pessoa("Pessoa Joana", 
                LocalDate.of(2010, 1, 1), 
                "Pess2"));

        todoMundo.add(new Aluno("Aluna Maria",
                LocalDate.of(2006,10,1),
                "Alun1", 1000));
        todoMundo.getLast().setCargaHoraria(300);        
    

        todoMundo.add(new Professor("Prof Pardal",
                 LocalDate.of(1983,5,1),
                 "Prof1"));
        todoMundo.getLast().setCargaHoraria(32);
       
        todoMundo.add(new Administrativo("Secretária Geral",
                 LocalDate.of(1992,5,1),
               "Sec1", 1000));
        todoMundo.getLast().setCargaHoraria(40);
        
        for (Pessoa pessoa : todoMundo) {
             System.out.println(pessoa.relatorio());
        }
        int cod = Integer.parseInt(IO.readln("Código"));
      
        for (Pessoa pessoa : todoMundo) {
             if(pessoa.hashCode() == cod)
                //faça alguma coisa
        }
        Professor ultimo = (Professor)todoMundo.getLast();
        System.out.println(ultimo.salarioBruto());
                
    }
}
