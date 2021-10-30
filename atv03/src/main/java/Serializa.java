import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializa {
    List<Pessoa> pessoas = new ArrayList<>();
    Pessoa pessoa1 = new Pessoa(233,"Sung Bai Tinga", "sungtinga123@chines.com","88999698");
    Pessoa pessoa2 = new Pessoa(234,"Sung Hello Tinga", "sunghello123@chines.com","8833221340");
    Pessoa pessoa3 = new Pessoa(235,"Sung Good Tinga", "sunggood123@chines.com","8899999999");

    public void add(){
        pessoas.add(pessoa1);
        pessoas.add(pessoa2);
        pessoas.add(pessoa3);
    }

    public void serializar_java()throws IOException {
        add();
        FileOutputStream fileOutputStream = new FileOutputStream("target/yourfile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(pessoas);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void serializar_json()throws IOException{
        add();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/pessoas.json"), pessoas);
    }

    public void serializar_xlm()throws IOException{
        add();
        XmlMapper XmlMapper = new XmlMapper();
        XmlMapper.writeValue(new File("target/pessoas.xlm"), pessoas);
    }

}
