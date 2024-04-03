import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
       Pessoa[] todoMundo = new Pessoa[20];

       todoMundo[0] = new Pessoa("Pessoa Joao", 
                LocalDate.of(2000, 1, 1), 
                "Pess1");
                
       todoMundo[1] = new Pessoa("Pessoa Joana", 
                LocalDate.of(2010, 1, 1), 
                "Pess2");

        todoMundo[2] = new Aluno("Aluna Maria",
                LocalDate.of(2003,10,1),
                "Alun1", 1000);
        todoMundo[2].setCargaHoraria(300);

        todoMundo[3] = new Professor("Prof Pardal",
                LocalDate.of(1983,5,1),
                "Prof1");
        todoMundo[3].setCargaHoraria(32);
       
        todoMundo[4] = new Administrativo("Secretária Geral",
                LocalDate.of(1992,5,1),
                "Sec1", 1000);
        todoMundo[4].setCargaHoraria(32);
        
        for (Pessoa pessoa : todoMundo) {
            if(pessoa!=null)
                System.out.println(pessoa.dadosPessoa());
        }
                
    }
}
