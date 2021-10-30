import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException {

        CadastroCarro cadastrocarrinho = new CadastroCarro();
        cadastrocarrinho.cadastro();
        cadastrocarrinho.lerarquivoCsv("carro.csv");
        cadastrocarrinho.serializarJson();
        cadastrocarrinho.serializar_xlm();

    }
}
