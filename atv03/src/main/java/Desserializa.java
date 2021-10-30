import java.io.*;

public class Desserializa{
    public void desserealiza()throws IOException, ClassNotFoundException {
        FileInputStream ler = new FileInputStream("target/yourfile.txt");
        ObjectInputStream leitura = new ObjectInputStream(ler);
        System.out.println(leitura.readObject());
        ler.close();
        leitura.close();
    }
}
