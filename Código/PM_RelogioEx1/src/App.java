import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Relogio primeiroRelogio = new Relogio();        //chamada do construtor

        int hora=0;
        int minuto=0;
        int segundo=0;

        String[] horario;
        //App para exemplo de uso de classe, sem tratamento de erros. Cuidado ;-)
        System.out.print("Digite o horário atual, no formato HH:MM:SS --> ");
        horario = teclado.nextLine().split(":");
        if(horario.length==3){
            hora = Integer.parseInt(horario[0]);
            minuto = Integer.parseInt(horario[1]);
            segundo = Integer.parseInt(horario[2]);
        }
        primeiroRelogio.ajustarHora(hora, minuto, segundo);
        System.out.println("Hora atual: "+primeiroRelogio.horaFormatada());
        primeiroRelogio.passarTempo();
        System.out.println("Um segundo depois: "+primeiroRelogio.horaFormatada());
        for (int i = 0; i < 10; i++) {
            primeiroRelogio.passarTempo();    
        }
        System.out.println("Mais 10 segundos: "+primeiroRelogio.horaFormatada());
        
        teclado.close();
    }
}
