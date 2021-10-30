import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroCarro {
    public void cadastro() throws IOException {
        Scanner entrada = new Scanner(System.in);
        String a, b, c, d;
        System.out.println("Digete o modelo do carro");
        a = entrada.nextLine();
        System.out.println("Digete o fabricante do carro");
        b = entrada.nextLine();
        System.out.println("Digete o ano de fabricação do carro");
        c = entrada.nextLine();
        System.out.println("Digete a potência do carro");
        d = entrada.nextLine();

        Carro carro = new Carro(a,b,c,d);
        escrever_csv(carro);
        System.out.println(carro);
    }

    public void escrever_csv(Carro carrinho)throws IOException {
        OutputStream a1 = new FileOutputStream("carro.csv", true);
        OutputStreamWriter a2 = new OutputStreamWriter(a1);
        BufferedWriter ff = new BufferedWriter(a2);
        String armazenar = (carrinho.getModelo()+","+carrinho.getFabricante()+","+carrinho.getAno()+","+carrinho.getPotencia());
        ff.write(armazenar);
        ff.newLine();
        ff.close();
    }

    public List<Carro> lerarquivoCsv(String carro)throws IOException{

        InputStream os = new FileInputStream(carro);
        InputStreamReader osw = new InputStreamReader(os);
        BufferedReader  fr= new BufferedReader(osw);
        String linha;
        List<Carro> carros = new ArrayList<>();

        while((linha = fr.readLine()) != null){
            String[] aux = (linha.split(","));
            Carro carrinho = new Carro (aux[0], aux[1], aux[2], aux[3]);
            carros.add(carrinho);
        }
        System.out.println(carros);
        return carros;
        }

        public void serializarJson()throws IOException{
           List<Carro> carros = lerarquivoCsv("carro.csv");
           ObjectMapper ob = new ObjectMapper();
           ob.writeValue(new File("carro.json"), carros);
        }

        public void serializar_xlm()throws IOException{
            List<Carro> carros = lerarquivoCsv("carro.csv");
            XmlMapper xm = new XmlMapper();
            xm.writeValue(new File("carro.xml"), carros);
    }
}
