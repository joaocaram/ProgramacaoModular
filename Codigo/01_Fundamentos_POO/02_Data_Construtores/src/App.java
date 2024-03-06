public class App {
    public static void main(String[] args) throws Exception {
        Data data1 = new Data(4,23,2024);

        System.out.println(data1.dataFormatada());

        data1 = new Data(12, 10);
        System.out.println(data1.dataFormatada());
        
    }
}
