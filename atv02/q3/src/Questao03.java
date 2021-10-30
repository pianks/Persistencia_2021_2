import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

public class Questao03 {
    public static Properties getProperties(Properties prop)throws IOException{
        FileInputStream file = new FileInputStream("./resources/config.properties");
        prop.load(file);

            return prop;
    }
public static void main(String[] args)throws IOException {
    Scanner input = new Scanner(System.in);

        Properties props = new Properties();
        getProperties(props);

        int initialLine = Integer.parseInt(props.getProperty("linha_inicial"));
        int endLine = Integer.parseInt(props.getProperty("linha_final"));

        System.out.println("Digite o nome do arquivo");
        String file = input.next();

        FileInputStream open = new FileInputStream(file);
        InputStreamReader is = new InputStreamReader(open);
        BufferedReader br = new BufferedReader(is);

        String linha;

        for(int i = 1; i <= endLine; i++){
            if(i < initialLine){
              linha = br.readLine();
            }else if(i >= initialLine){
                linha = br.readLine();
                if(linha != null)
                    System.out.println(linha);
            }
        }
    }
}