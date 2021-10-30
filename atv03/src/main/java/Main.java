import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException, ClassNotFoundException {
        Serializa serializa = new Serializa();

        serializa.serializar_java();
        serializa.serializar_json();
        serializa.serializar_xlm();

        Desserializa desserializa = new Desserializa();
        desserializa.desserealiza();
    }

}
